package com.mayakplay.cscase.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

/**
 * Created by Константин on 10.01.2016.
 */
public class CasesListPacket implements IMessage {

    String text;

    public CasesListPacket() {

    }

    public CasesListPacket(String text) {
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

    public static class Handler implements IMessageHandler<CasesListPacket, IMessage> {

        @Override
        public IMessage onMessage(CasesListPacket message, MessageContext ctx) {

            if (Recieve.CASES_LIST.equalsIgnoreCase(""))
                Recieve.CASES_LIST = message.text; else
                Recieve.CASES_LIST = Recieve.CASES_LIST + "," + message.text;

            return null;
        }
    }

}
