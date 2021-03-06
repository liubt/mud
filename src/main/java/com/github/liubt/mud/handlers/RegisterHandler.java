package com.github.liubt.mud.handlers;

import com.github.liubt.mud.cmd.OriginalCmd;
import com.github.liubt.mud.cmd.RegisterCmd;
import com.github.liubt.mud.cmd.RegisterCmdResult;
import com.github.liubt.mud.model.UserPassword;
import com.github.liubt.mud.redis.RedisDAO;
import com.github.liubt.mud.utils.JsonUtils;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by lbt on 2017/8/2.
 */
public class RegisterHandler {

    public static void handle(ChannelHandlerContext ctx, OriginalCmd cmd) {
        RegisterCmd registerCmd = JsonUtils.map2Pojo(cmd.getData(), RegisterCmd.class);
        RegisterCmdResult cmdResult = new RegisterCmdResult();

        UserPassword userPassword = new UserPassword();
        userPassword.setUserId(registerCmd.getUserId());
        userPassword.setPassword(registerCmd.getPassword());
        
        if(RedisDAO.hasKey(userPassword.getKey())) {
            cmdResult.setSuccess(false);
            cmdResult.setMessage("用户ID已经存在");
            ctx.writeAndFlush(cmdResult);
            return;
        }

        if(registerCmd.getUserId().contains("_")) {
            cmdResult.setSuccess(false);
            cmdResult.setMessage("用户ID不能包括符号");
            ctx.writeAndFlush(cmdResult);
            return;
        }

        RedisDAO.set(userPassword.getKey(), userPassword.getData());
        ctx.writeAndFlush(new RegisterCmd());
    }
}
