package com.github.liubt.mud.cmd;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseCmdResult {

    private boolean success = true;
    private String cmd;

    public BaseCmdResult(String cmd) {
        this.cmd = cmd;
    }

}
