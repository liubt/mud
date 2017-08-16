package com.github.liubt.mud.model;

/**
 * Created by lbt on 2017/8/9.
 */
public interface RedisModel {

    /**
     * 获取redis的Key
     * @return
     */
    String getKey();

    /**
     * 获取存储到redis中的内容
     * @return
     */
    String getData();
}
