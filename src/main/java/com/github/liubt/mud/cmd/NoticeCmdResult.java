package com.github.liubt.mud.cmd;

import com.github.liubt.mud.consts.CmdConsts;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeCmdResult extends BaseCmdResult {

    private String message;

    public NoticeCmdResult() {
        super(CmdConsts.CMD_NOTICE);
    }
}
