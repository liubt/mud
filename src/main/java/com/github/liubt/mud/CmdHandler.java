package com.github.liubt.mud;

import com.github.liubt.mud.cmd.OriginalCmd;
import com.github.liubt.mud.consts.CmdConsts;
import com.github.liubt.mud.handlers.LoginHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CmdHandler extends SimpleChannelInboundHandler<OriginalCmd> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OriginalCmd cmd) throws Exception {

        switch (cmd.getCmd()) {
            case CmdConsts.CMD_LOGIN:
                LoginHandler.handle(ctx,cmd);
                break;
            default:
                // Do nothing
                break;
        }
    }
}
