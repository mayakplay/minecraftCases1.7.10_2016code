package com.mayakplay.cscase.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

/**
 * Created by Константин on 10.01.2016.
 */
public class CasesViewPacket implements IMessage {

    String text;

    public CasesViewPacket() {

    }

    public CasesViewPacket(String text) {
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

    public static class Handler implements IMessageHandler<CasesViewPacket, IMessage> {

        @Override
        public IMessage onMessage(CasesViewPacket message, MessageContext ctx) {

            if (Recieve.CURRENT_CASE_ITEMS_LIST.equalsIgnoreCase(""))
                Recieve.CURRENT_CASE_ITEMS_LIST = message.text; else
                Recieve.CURRENT_CASE_ITEMS_LIST = Recieve.CURRENT_CASE_ITEMS_LIST + "," + message.text;

            return null;
        }
    }
}
