package com.github.liubt.mud.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 保存用户基本信息。key形如：{userId}
 */
@Getter
@Setter
public class User implements RedisModel {
    public String userId;
    public String password;


    @Override
    public String getKey() {
        return userId;
    }

    @Override
    public String getData() {
        return null;
    }
}
