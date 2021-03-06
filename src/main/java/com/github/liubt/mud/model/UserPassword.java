package com.github.liubt.mud.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 保存用户密码。key形如：password_{userId}
 */
@Getter
@Setter
public class UserPassword implements RedisModel {

    private static final String KEY_PREFIX = "password_";

    public String userId;
    public String password;


    @Override
    public String getKey() {
        return KEY_PREFIX + userId;
    }

    @Override
    public String getData() {
        return password;
    }
}
