package com.mayakplay.cscase.network;

import com.mayakplay.cscase.gui.GuiCaseView;
import com.mayakplay.cscase.gui.GuiCasesShop;
import com.mayakplay.cscase.gui.GuiMotd;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

/**
 * Created by Константин on 10.01.2016.
 */
public class CasesMainPacket implements IMessage {

    String text;

    public CasesMainPacket() {

    }

    public CasesMainPacket(String text) {
        this.text = text;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        text = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, text);
    }

    public static class Handler implements IMessageHandler<CasesMainPacket, IMessage> {

        @Override
        public IMessage onMessage(CasesMainPacket message, MessageContext ctx) {

            if (message.text.equals("Clear")) {
                Recieve.CASES_LIST = "";
                Recieve.CURRENT_CASE_ITEMS_LIST = "";
                Recieve.WON_ITEM = "";
            }

            if (message.text.equals("ClearLast")) {
                Recieve.CURRENT_CASE_ITEMS_LIST = "";
                //Recieve.WON_ITEM = "";
            }

            if (message.text.equals("Open")) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiCasesShop());
            }

            String[] args = message.text.split(",");
            if (args[0].equals("Viev")) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiCaseView(Integer.parseInt(args[1])));
            }

            if (args[0].equals("SetWon")) {
                Recieve.WON_ITEM = args[1]+","+args[2]+","+args[3]+","+args[4];
            }

            if (args[0].equals("SetMotd")) {
                Recieve.MOTD_IMG  = args[1];
                Minecraft.getMinecraft().displayGuiScreen(new GuiMotd(Recieve.MOTD_IMG));
            }

            if (message.text.equals("RollCase")) {
                Recieve.isRolling = true;
            }

            return null;
        }
    }


}
