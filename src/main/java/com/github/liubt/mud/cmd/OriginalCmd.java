package com.github.liubt.mud.cmd;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class OriginalCmd {

    private String cmd;
    private Map<String, Object> data;

}
