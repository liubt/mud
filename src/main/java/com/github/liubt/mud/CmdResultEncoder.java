package com.github.liubt.mud;

import com.github.liubt.mud.cmd.BaseCmdResult;
import com.github.liubt.mud.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class CmdResultEncoder extends MessageToByteEncoder<BaseCmdResult> {

    @Override
    protected void encode(ChannelHandlerContext ctx, BaseCmdResult cmdResult, ByteBuf byteBuf) throws Exception {
        ctx.writeAndFlush(new TextWebSocketFrame(JsonUtils.pojo2Json(cmdResult)));
    }
}
