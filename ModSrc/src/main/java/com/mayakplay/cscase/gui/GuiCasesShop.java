package com.mayakplay.cscase.gui;

import com.mayakplay.cscase.Refs;
import com.mayakplay.cscase.Strings;
import com.mayakplay.cscase.model.ModelItemCase;
import com.mayakplay.cscase.network.PacketsDecoder;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;


/**
 * Created by Константин on 07.01.2016.
 */
public class GuiCasesShop extends MPGui {

    int move = 0;
    int maxMove = 0;
    @Override
    public void drawScreen(int x, int y, float ticks) {
        super.drawScreen(x, y, ticks);

        ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int factor = scaled.getScaleFactor();

        int guiX = width / 2 - 255 / 2;
        int guiY = height / 2 - 226 / 2;

        mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/CaseShopTexture.png"));
        drawTexturedModalRect(guiX, guiY, 0, 0, 256, 255);



        int itemsCount = PacketsDecoder.getCases().size();
        int colsCount = 3;
        int rowsCount = Math.round(((float)  itemsCount / colsCount) + 0.2F);

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((guiX) * factor, height * factor - (guiY - 2) * factor - 220 * factor, 247 * factor, 209 * factor);
        maxMove = rowsCount * 80 - 208;

        if (move >= 0) {
            move = 0;
        }

        if (rowsCount > 3) {
            if (move <= -maxMove) {
                move = -maxMove;
            }
        } else {
            move = 0;
        }

        int counter = 0;
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < colsCount; j++) {
                if (counter < itemsCount) {

                    //ТУТ РЕНДЕР, ЭТО НЕ ВАЖНО
                    mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/CaseShopTextureL.png"));
                    drawTexturedModalRect(guiX + 10 + j * 78, guiY + 10 + 80 * i + move, 0, 0, 76, 78);

                    if (!isHover(guiX + 11 + j * 78, guiY + 73 + 80 * i + move, 74, 14))
                        drawTexturedModalRect(guiX + 11 + j * 78, guiY + 73 + 80 * i + move, 0, 78, 74, 14);
                    else
                        drawTexturedModalRect(guiX + 11 + j * 78, guiY + 73 + 80 * i + move, 0, 92, 74, 14);

                    if (isClicked(guiX + 11 + j * 78, guiY + 73 + 80 * i + move, 74, 14)) {
                        mc.thePlayer.sendChatMessage("/mpcaseview "+counter);
                        isClicked = false;
                    }

                    drawScaledString(PacketsDecoder.getCases().get(counter).getName(), guiX + 12 + j * 78, guiY + 14 + 80 * i + move, 0.85F, TextPosition.LEFT);
                    drawScaledString(Strings.price + PacketsDecoder.getCases().get(counter).getPrice(), guiX + 12 + j * 78, guiY + 63 + 80 * i + move, 0.85F, TextPosition.LEFT);
                    drawScaledString(Strings.view, guiX + 47 + j * 78, guiY + 76 + 80 * i + move, 0.8F, TextPosition.CENTER);

                    draw3DCase(guiX + 21 + j * 78, guiY + 32 + 80 * i + move, "case"+counter, 160);

                    counter++;
                }
            }
        }

        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GL11.glPopMatrix();

        ///slider
        if (rowsCount > 3) {
            mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/CaseShopTextureL.png"));
            drawTexturedModalRect(357, 27, 79, 0, 3, 210);
            drawTexturedModalRect(357, (int) (27 - move * (190.0F / maxMove)), 76, 0, 3, 20);

            //y - 27
            //

            float gnomik = 190 / maxMove;

            if (isClicked(357, 27, 3, 210)) {
                isClicked = false;
                move = -((int) ((y - 27) / gnomik));
            }
        }
        //debug
    }

    @Override
    protected void mouseClickMove(int x, int y, int b, long l) {
    }

    @Override
    protected void mouseMovedOrUp(int x, int y, int b) {
    }

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        move += Mouse.getEventDWheel() / 120 * 4;
    }

    @Override
    public void initGui() {
        for (int i = 0; i < PacketsDecoder.getCases().size(); i++) {
            String t = PacketsDecoder.getCases().get(i).getTexture();
            addTex("case"+i, t);
        }
    }
}
