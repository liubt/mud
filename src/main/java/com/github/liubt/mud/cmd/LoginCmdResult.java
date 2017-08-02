package com.github.liubt.mud.cmd;

import com.github.liubt.mud.consts.CmdConsts;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCmdResult extends BaseCmdResult {

    private String welcomeMsg;

    public LoginCmdResult() {
        super(CmdConsts.CMD_LOGIN);
    }
}
