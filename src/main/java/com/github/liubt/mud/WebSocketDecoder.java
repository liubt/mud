package com.github.liubt.mud;

import com.github.liubt.mud.cmd.OriginalCmd;
import com.github.liubt.mud.utils.JsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

public class WebSocketDecoder extends MessageToMessageDecoder<TextWebSocketFrame> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame msg, List<Object> list) throws Exception {
        OriginalCmd cmd = JsonUtils.json2Pojo(msg.text(), OriginalCmd.class);
        list.add(cmd);
    }
}
