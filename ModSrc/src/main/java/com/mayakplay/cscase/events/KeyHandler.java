package com.mayakplay.cscase.events;

import com.mayakplay.cscase.CasesMain;
import com.mayakplay.cscase.gui.GuiCaseView;
import com.mayakplay.cscase.gui.GuiCaseWon;
import com.mayakplay.cscase.gui.GuiCasesShop;
import com.mayakplay.cscase.gui.GuiMotd;
import com.mayakplay.cscase.network.Recieve;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by Константин on 06.01.2016.
 */
public class KeyHandler {

    @SubscribeEvent
    public void KeyPress(InputEvent.KeyInputEvent event) {

        if (CasesMain.KeyTest.isPressed()) {
            //Minecraft.getMinecraft().displayGuiScreen(new GuiCaseWon(new ItemStack(Blocks.dirt, 9), 2));
            //Minecraft.getMinecraft().displayGuiScreen(new GuiCasesShop());
            //Minecraft.getMinecraft().displayGuiScreen(new GuiCaseView());
            //System.out.println(Recieve.CURRENT_CASE_ITEMS_LIST);
            //Minecraft.getMinecraft().thePlayer.sendChatMessage("/mpcaseshop");
            //Minecraft.getMinecraft().displayGuiScreen(new GuiMotd(Recieve.MOTD_IMG));
        }
    }
}