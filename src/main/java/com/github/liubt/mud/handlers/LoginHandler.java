package com.github.liubt.mud.handlers;

import com.github.liubt.mud.ChannelHolder;
import com.github.liubt.mud.cmd.LoginCmd;
import com.github.liubt.mud.cmd.LoginCmdResult;
import com.github.liubt.mud.cmd.NoticeCmdResult;
import com.github.liubt.mud.cmd.OriginalCmd;
import com.github.liubt.mud.utils.JsonUtils;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by lbt on 2017/8/2.
 */
public class LoginHandler {
    public static void handle(ChannelHandlerContext ctx, OriginalCmd cmd) {
        LoginCmd loginCmd = JsonUtils.map2Pojo(cmd.getData(), LoginCmd.class);
        ChannelHolder.bind(loginCmd.getUserId(), ctx.channel());

        // 关闭连接之后的处理
        ctx.channel().closeFuture().addListener((ChannelFuture channelFuture) -> {
            ChannelHolder.unbind(channelFuture.channel().id().asLongText());
            // 通知所有人，有走了
            NoticeCmdResult noticeCmdResult = new NoticeCmdResult();
            noticeCmdResult.setMessage(loginCmd.getUserId() + "离开了！\n");
            ChannelHolder.getChannelMap().forEach((k,v)-> v.writeAndFlush(noticeCmdResult));
        });

        // 登录成功
        LoginCmdResult cmdResult = new LoginCmdResult();
        cmdResult.setWelcomeMsg("欢迎你，" + loginCmd.getUserId() + "\n");
        ctx.writeAndFlush(cmdResult);


        // 通知所有人，有用户登录了
        NoticeCmdResult noticeCmdResult = new NoticeCmdResult();
        noticeCmdResult.setMessage(loginCmd.getUserId() + "来了！\n");
        ChannelHolder.getChannelIdMap().forEach((k,v)-> {
            if(!k.equals(loginCmd.getUserId())) {
                ChannelHolder.getByUserId(k).writeAndFlush(noticeCmdResult);
            }
        });
    }
}
