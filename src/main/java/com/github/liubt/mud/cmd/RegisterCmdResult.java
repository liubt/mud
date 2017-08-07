package com.github.liubt.mud.cmd;

import com.github.liubt.mud.consts.CmdConsts;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCmdResult extends BaseCmdResult {



    public RegisterCmdResult() {
        super(CmdConsts.CMD_REGISTER);
    }
}
