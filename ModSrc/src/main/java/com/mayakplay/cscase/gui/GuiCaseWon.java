package com.mayakplay.cscase.gui;

import com.mayakplay.cscase.Refs;
import com.mayakplay.cscase.model.ModelGuiCase;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;


/**
 * Created by Константин on 03.01.2016.
 */
public class GuiCaseWon extends MPGui {

    float animation = 0;
    float mainAnimation = 25;
    float fenceAnim = 0;

    float numAnim = 0;

    ModelGuiCase model = new ModelGuiCase();
    RenderItem renderItem = new RenderItem();

    private int itemsCount;
    private ItemStack itemStack;
    private int quality;

    public GuiCaseWon(ItemStack itemStack, int quality) {
        this.quality = quality;
        this.itemsCount = itemStack.stackSize;
        this.itemStack = new ItemStack(itemStack.getItem(), 1);
    }

    @Override
    public void drawScreen(int x, int y, float ticks) {
        super.drawScreen(x,y,ticks);
        drawDefaultBackground();

        ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int factor = scaled.getScaleFactor();
        int panX = 205;
        int panY = 105;

        int guiX = width / 2 - panX / 2;
        int guiY = height / 2 - panY / 2;

        Color color = Color.white;
        switch (quality) {
            case 1:
                color = Color.WHITE;
            break;
            case 2:
                color = Color.GREEN;
                break;
            case 3:
                color = Color.BLUE;
                break;
            case 4:
                color = Color.MAGENTA;
                break;
            case 5:
                color = Color.ORANGE;
                break;
            case 6:
                color = Color.RED;
                break;
        }

        mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/OpcTexture.png"));
        drawTexturedModalRect(guiX - 7, guiY -16 ,0 ,0 ,219, 146);
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        //GL11.glColor4f(color.getRed() / 255F,color.getGreen() / 255F,color.getBlue() / 255F, 0.28F);
        //mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/OpcTextureU.png"));
        //drawTexturedModalRect(guiX - 7, guiY -16 ,0 ,0 ,219, 146);
        GL11.glPopMatrix();
        GL11.glColor3f(1,1,1);

        drawCenteredString(fontRendererObj, itemStack.getDisplayName() , width / 2, guiY - 12, 0xFFFFFF);

        mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/OpcTexture.png"));
        if (fenceAnim == - 0.9F) {
            if (!isHover(guiX - 7 + 67, guiY - 16 + 124, 85, 17))
                drawTexturedModalRect(guiX - 7 + 67, guiY - 16 + 124, 0, 146, 85, 17);
            else
                drawTexturedModalRect(guiX - 7 + 67, guiY - 16 + 124, 0, 164, 85, 17);
            if (isClicked(guiX - 7 + 67, guiY - 16 + 124, 85, 17))
                mc.displayGuiScreen(null);

            drawScaledString("Продолжить", guiX - 5 + 67 + 85 / 2, guiY - 11 + 124, 0.76F, TextPosition.CENTER);
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
        EntityItem entityItem = new EntityItem(mc.theWorld, 0D, 0D, 0D, itemStack);
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

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
