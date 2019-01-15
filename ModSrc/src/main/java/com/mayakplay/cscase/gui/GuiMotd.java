package com.mayakplay.cscase.gui;

import com.mayakplay.cscase.Refs;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Константин on 17.01.2016.
 */
public class GuiMotd extends MPGui {

    public GuiMotd(String texture) {
        addTex("motd", texture);
    }

    @Override
    public void drawScreen(int x, int y, float ticks) {
        super.drawScreen(x, y, ticks);
        drawDefaultBackground();

        int guiX = width / 2 - 255 / 2;
        int guiY = height / 2 - 226 / 2;

        drawScaledString("ESC - \u0417\u0430\u043A\u0440\u044B\u0442\u044C", width / 2, 2, 1.6F, TextPosition.CENTER);

        mc.renderEngine.bindTexture(new ResourceLocation(Refs.MOD_ID, "textures/gui/CaseShopTexture.png"));
        drawTexturedModalRect(guiX, guiY, 0, 0, 256, 255);

        bindTexture("motd");
        drawCompleteImage(guiX + 8,guiY + 8,239,210);
    }

    @Override
    public void initGui() {

    }
}
