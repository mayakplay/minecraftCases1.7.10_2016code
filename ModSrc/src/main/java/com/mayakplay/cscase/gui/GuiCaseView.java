package com.mayakplay.cscase.gui;

import com.mayakplay.cscase.ColorHelper;
import com.mayakplay.cscase.Refs;
import com.mayakplay.cscase.Strings;
import com.mayakplay.cscase.model.ModelGuiCase;
import com.mayakplay.cscase.network.PacketsDecoder;
import com.mayakplay.cscase.network.Recieve;
import com.mayakplay.cscase.pojo.CaseItem;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import javax.jws.soap.SOAPBinding;
import java.awt.*;
import java.util.*;

/**
 * Created by Константин on 07.01.2016.
 */
public class GuiCaseView extends MPGui {

    private int caseid;
    private boolean isWon = false;
    float rollMove = 0;
    private java.util.List<CaseItem> ROLLING_ITEMS = null;//= PacketsDecoder.getRandomItemsForRoll();

    public GuiCaseView(int caseid) {
        this.caseid = caseid;
    }

    private boolean isCaseLoading = false;
    private float caseLoadingRotation = 160;
    @Override
    public void drawScreen(int x, int y, float ticks) {
        super.drawScreen(x, y, ticks);
        drawDefaultBackground();

        ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int factor = scaled.getScaleFactor();

        int guiX = width / 2 - 255 / 2;
        int guiY = height / 2 - 226 / 2;

        //drawScaledString((x - guiX) + "|" + (y - guiY), 2, 2, 1, TextPosition.LEFT); //==============

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/CaseShopTextureCW.png"));
        drawTexturedModalRect(guiX, guiY, 0, 0, 256, 72);
        GL11.glColor4f(1,1,1, 0.6F);
        drawTexturedModalRect(guiX, guiY, 0, 0, 256, 255);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();

        drawScaledString(Strings.itemsCanDrop, guiX + 12, guiY + 63, 0.85F, TextPosition.LEFT); //==============

        mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/CaseShopTextureL.png"));
        if (!Recieve.isRolling) {

            if (!isCaseLoading) {
                if (!isHover(guiX + 65, guiY + 39, 125, 14))
                    drawTexturedModalRect(guiX + 65, guiY + 39, 0, 214, 125, 14);
                else
                    drawTexturedModalRect(guiX + 65, guiY + 39, 0, 228, 125, 14);
                if (isClicked(guiX + 65, guiY + 39, 125, 14)) {
                    //isRolling = true;
                    isCaseLoading = true;
                    mc.thePlayer.sendChatMessage("/rollcase " + caseid);
                    randStop = PacketsDecoder.randInt(12, 21);
                    isClicked = false;
                }


                draw3DCase(guiX + 101, guiY + 13, "case" + caseid, 160);


                int price = 1000;
                for (int i = 0; i < PacketsDecoder.getCases().size(); i++) {
                    if (i == caseid) price = PacketsDecoder.getCases().get(i).getPrice();
                }

                drawScaledString(Strings.openPrice(price), guiX + 128, guiY + 42, 0.92F, TextPosition.CENTER); //==============
            } else {
                draw3DCase(guiX + 101, guiY + 13, "case" + caseid, caseLoadingRotation);
                caseLoadingRotation += delta * 2;
                mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/CaseShopTextureL.png"));
                drawTexturedModalRect(guiX + 65, guiY + 39, 0, 242, 125, 14);
                drawScaledString(Strings.opening, guiX + 81, guiY + 42, 0.92F, TextPosition.LEFT); //==============
            }
        } else {
            if (ROLLING_ITEMS == null)
                ROLLING_ITEMS = PacketsDecoder.getRandomItemsForRoll();

            mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/CaseShopTextureL.png"));
            drawTexturedModalRect(guiX + 65, guiY + 39, 0, 242, 125, 14);
            drawScaledString(Strings.opening, guiX + 81, guiY + 42, 0.92F, TextPosition.LEFT); //==============
            drawRollingItems();
        }

        drawItemsGrid(x, y, guiX + 8, guiY + 73);

        if (isWon) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0,0,400);
            drawWonScreen(x,y,ticks);
            GL11.glPopMatrix();
        }
    }

    private float littleWaiting = 0;
    //38 42
    private float slow = PacketsDecoder.randFloat(9.38F, 9.42F);// 9.42F; //((float) PacketsDecoder.getRandomItemsForRoll().size()) * (8.512F / 50F);
    private int randStop = 0;
    private boolean isRolling = false;

    private boolean useful1 = true;
    private int current = 0;
    private int lastInt = 0;

    @Override
    public void onGuiClosed() {
        Recieve.isRolling = false;
    }

    private void drawRollingItems() {
        ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int factor = scaled.getScaleFactor();

        int guiX = width / 2 - 255 / 2;
        int guiY = height / 2 - 226 / 2;

        //rollMove = 0;



        // Рисовка кейсов
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((guiX + 66) * factor, height * factor - (guiY + 7) * factor - 30 * factor, 123 * factor, 30 * factor);
        mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/CaseShopTextureL.png"));

        for (int i = 0; i < ROLLING_ITEMS.size(); i++) {
            Color cl = ColorHelper.getColorByRare(ROLLING_ITEMS.get(i).getRarity());
            GL11.glPushMatrix();
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glTranslatef(guiX + rollMove + 66.0F + 40.0F * i, guiY + 8, 0);
            drawTexturedModalRect(0, 0, 127, 0, 38, 20);
            //playSound("rolling");
            int poop = (int) (guiX + rollMove + 66.0F + 40.0F * i);
            int contact = guiX + 127;

            if (contact >= poop && contact < 40+poop)
                current = i;

            //if ((float) Math.abs(poop) > (float) contact && (float) Math.abs(poop) < (float) contact - 10) {
            //	current = i;
            //}
            
            
            if (lastInt != current) {
            	playSound("rolling");
            }
            
            lastInt = current;
            
            

                //playSound("rolling");

            //System.out.println((float)((int)( Math.abs(guiX + rollMove + 66.0F + 40.0F * i))) + 0.001F +" | "+ (float) (guiX + 127) + 0.001F);

            GL11.glColor4f(cl.getRed() / 255F, cl.getGreen()/ 255F, cl.getBlue() / 255F, 1F);
            drawTexturedModalRect(0, 20, 127, 20, 38, 8);

            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor3f(1,1,1);
            GL11.glPopMatrix();

            ItemStack is = ROLLING_ITEMS.get(i).getItemStack();

            draw3DGuiItem(is, (int) guiX + rollMove + 85.0F + 40.0F * i, guiY + 26, 30F);

            GL11.glColor3f(1,1,1);
            if (is.getDisplayName().length() > 12)
                drawScaledString(is.getDisplayName().substring(0, 11)+"...", guiX + rollMove + 67 + 40.0F * i, guiY + 29, 0.48F, TextPosition.LEFT); else
                drawScaledString(is.getDisplayName(), guiX + rollMove + 67 + 40.0F * i, guiY + 29, 0.48F, TextPosition.LEFT);

            mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/CaseShopTextureL.png"));
        }

        GL11.glDisable(GL11.GL_SCISSOR_TEST);

        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glTranslatef(0,0,400);
        mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/CaseShopTextureL.png"));
        drawTexturedModalRect(guiX + 102, guiY + 7, 165, 0, 51, 30);
        GL11.glPopMatrix();

        //rollMove = -1000;
        //rollMove =  -((ROLLING_ITEMS.size()-4) * 40); // -5 | -15
        if (rollMove >= -((ROLLING_ITEMS.size()-4) * 40) + randStop) {
            rollMove = rollMove - delta * slow;
            if (slow >= 0.05F)
            slow -= delta / 50;
        } else if (littleWaiting < 6) {
            littleWaiting += delta * slow;
        } else {
            this.itemStack = ROLLING_ITEMS.get(57).getItemStack().copy();//PacketsDecoder.getRandomItemsForRoll().get(57).getItemStack();
            this.itemsCount = this.itemStack.stackSize;
            this.quality = ROLLING_ITEMS.get(57).getRarity();
            isWon = true;
            //mc.displayGuiScreen(new GuiCaseWon(new ItemStack(PacketsDecoder.getRandomItemsForRoll().get(57).getItem()), PacketsDecoder.getRandomItemsForRoll().get(57).getRarity()));
        }

        //rollMove = - 1000;

        //drawScaledString(rollMove + "|" + ((-((ROLLING_ITEMS.size()-5) * 40) + randStop) + 10), 2, 2, 1, TextPosition.LEFT);

        //else if (littleWaiting < 10) {
        //    littleWaiting += delta * slow;
        //}
        //else
        //    mc.displayGuiScreen(new GuiCaseWon(new ItemStack(Items.bed), 1));

    }

    //range
    //opacity 0.1F - 1.0F
    //scale 0.1 - 45F
    int counter = 0;
    float gridAnim = 0;
    private void drawItemsGrid(int mouseX, int mouseY, int x, int y) {
        //drawScaledString((mouseX - x) + "|" + (mouseY - y), 2, 12, 1, TextPosition.LEFT);
        mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/CaseShopTextureL.png"));
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 5; j++) {
                if (counter < (int) gridAnim) {

                    mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/CaseShopTextureL.png"));
                    drawTexturedModalRect(x + 3 + 47 * j, y + 2 + 48 * i, 82, 0, 45, 33);
                    Color cl = ColorHelper.getColorByRare(PacketsDecoder.getCaseItemsList().get(counter).getRarity());

                    GL11.glPushMatrix();
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    drawTexturedModalRect(x + 3 + 47 * j, y + 2 + 48 * i + 33, 82, 33, 45, 12);

                    GL11.glColor4f(cl.getRed() / 255F, cl.getGreen()/ 255F, cl.getBlue() / 255F, 0.9F);

                    drawTexturedModalRect(x + 3 + 47 * j, y + 2 + 48 * i + 33, 82, 33, 45, 12);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glColor3f(1,1,1);
                    GL11.glPopMatrix();


                    ItemStack is = PacketsDecoder.getCaseItemsList().get(counter).getItemStack();
                    draw3DGuiItem(is, x + 25  + 47 * j, y + 29 + 48 * i, 38F);

                    String name = is.getDisplayName();
                    if (PacketsDecoder.getCaseItemsList().get(counter).getRarity() == 5) {
                        name = "\u2726 " + name + " \u2726";
                    }

                    if (name.length() > 14) {
                        drawScaledString(name.substring(0, 13) + "-", x + 5 + 47 * j, y + 36 + 48 * i, 0.5F, TextPosition.LEFT);
                        if (name.length() > 27)
                            drawScaledString(name.substring(13,26)+"...", x + 5 + 47 * j, y + 41 + 48 * i, 0.5F, TextPosition.LEFT); else
                            drawScaledString(name.substring(13), x + 5 + 47 * j, y + 41 + 48 * i, 0.5F, TextPosition.LEFT);
                    } else {
                        drawScaledString(name, x + 5 + 47 * j, y + 38 + 48 * i, 0.5F, TextPosition.LEFT);
                    }

                    counter++;

                }
            }
        }

        if (gridAnim < PacketsDecoder.getCaseItemsList().size()) {
            gridAnim += delta / 2;
        }

        if (gridAnim > PacketsDecoder.getCaseItemsList().size()) {
            gridAnim = PacketsDecoder.getCaseItemsList().size();
        }
        counter = 0;
    }

    //////////////////////////////// WON!!!!!!!////////////////////////////////////
    float animation = 0;
    float mainAnimation = 25;
    float fenceAnim = 0;

    float numAnim = 0;

    ModelGuiCase model = new ModelGuiCase();
    RenderItem renderItem = new RenderItem();

    private int itemsCount;
    private ItemStack itemStack;
    private int quality;
    private void drawWonScreen(int x, int y, float ticks) {
        drawDefaultBackground();

        ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int factor = scaled.getScaleFactor();
        int panX = 205;
        int panY = 105;

        int guiX = width / 2 - panX / 2;
        int guiY = height / 2 - panY / 2;

        Color color = ColorHelper.getColorByRare(quality);

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/OpcTexture.png"));
        drawTexturedModalRect(guiX - 7, guiY -16 ,0 ,0 ,219, 146);

        GL11.glColor4f(color.getRed() / 255F,color.getGreen() / 255F,color.getBlue() / 255F, 0.7F);
        drawTexturedModalRect(guiX - 7, guiY -16 ,0 ,0 ,219, 16);

        GL11.glColor3f(1,1,1);
        mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/OpcTextureU.png"));
        //drawTexturedModalRect(guiX - 7, guiY -16 ,0 ,0 ,219, 1);

        GL11.glPopMatrix();

        drawCenteredString(fontRendererObj, itemStack.getDisplayName() , width / 2, guiY - 12, 0xFFFFFF);

        mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/OpcTexture.png"));
        if (fenceAnim == - 0.9F) {
            if (!isHover(guiX - 7 + 67, guiY - 16 + 124, 85, 17))
                drawTexturedModalRect(guiX - 7 + 67, guiY - 16 + 124, 0, 146, 85, 17);
            else
                drawTexturedModalRect(guiX - 7 + 67, guiY - 16 + 124, 0, 164, 85, 17);
            if (isClicked(guiX - 7 + 67, guiY - 16 + 124, 85, 17)) {
                mc.displayGuiScreen(null);
                isWon = false;
                isClicked = false;
            }

            drawScaledString(Strings.continueOK, guiX - 5 + 67 + 85 / 2, guiY - 11 + 124, 0.76F, TextPosition.CENTER);
        }

        //For test
        if (animation >= 360) {
            animation = 0;
        }

        if (mainAnimation <= 8) {
            mainAnimation = 8;
        }

        if (mainAnimation <= 15) {
            fenceAnim = fenceAnim - delta / 90;
        }

        if (fenceAnim <= -0.9F) {
            fenceAnim = -0.9F;
        }

        if (fenceAnim <= -0.8) {
            numAnim = numAnim + delta / 60;
        }

        if (numAnim >= 0.3F) {
            numAnim = 0.3F;
        }

        //System.out.println(delta);

        GL11.glPushMatrix();
        ItemStack is = itemStack.copy();
        is.setItemDamage(itemStack.getItemDamage());
        is.stackSize = 1;
        EntityItem entityItem = new EntityItem(mc.theWorld, 0D, 0D, 0D, is);
        entityItem.hoverStart = 0.0F;
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor(guiX * factor, height * factor - guiY * factor - panY * factor, panX * factor, panY * factor);

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_CULL_FACE);
        mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/CasesOpened.png"));
        GL11.glTranslatef(width/2 + 7, height/2 - 113, 360);
        GL11.glScalef(118, 118, 1);
        GL11.glRotatef(270, 0F, 1F, 0F);
        GL11.glRotatef(mainAnimation, 0F, 1F, 1F);
        // -0.01F - -0.9F
        // 0.01F - 0.3F

        model.renderModel(0.0625F ,  fenceAnim, numAnim);
        RenderHelper.disableStandardItemLighting();
        GL11.glTranslatef(-0.5F, 1.35F, 0);
        GL11.glScalef(1.2F, 1.2F, 1.2F);
        GL11.glRotatef(8, 0, 0, 1);
        GL11.glRotatef(animation + 90, 0, 1, 0);
        GL11.glRotatef(180, 1, 0, 0);
        RenderManager.instance.renderEntityWithPosYaw(entityItem, 0, 0, 0, 0.2F, 0.2F);
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GL11.glPopMatrix();

        if (numAnim >= 0.22) {
            GL11.glPushMatrix();

            if (itemsCount >= 10)
                GL11.glTranslatef(width / 2 + 77, height / 2 - 37, 400); else
                GL11.glTranslatef(width / 2 + 77 + 4.6F, height / 2 - 37, 400);

            GL11.glScalef(1.5F, 1.5F, 1.5F);
            fontRendererObj.drawString(EnumChatFormatting.DARK_GRAY + "" + itemsCount, 0, 0, 0xCC000000);
            GL11.glPopMatrix();
        }

        if (mainAnimation <= 8) {
            animation = animation + delta;
        } else {
            mainAnimation += - delta / 6;
        }
    }
}
