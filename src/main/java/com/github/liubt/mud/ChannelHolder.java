package com.github.liubt.mud;

import io.netty.channel.Channel;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ChannelHolder {

    @Getter
    private static Map<String, Channel> channelMap = new HashMap<>();
    @Getter
    private static Map<String, String> userIdMap = new HashMap<>();
    @Getter
    private static Map<String, String> channelIdMap = new HashMap<>();

    public static void bind(String userId, Channel channel) {
        channelMap.put(channel.id().asLongText(), channel);
        userIdMap.put(channel.id().asLongText(), userId);
        channelIdMap.put(userId, channel.id().asLongText());
    }

    public static void unbind(String channelId) {
        channelMap.remove(channelId);
        String userId = userIdMap.get(channelId);
        userIdMap.remove(channelId);
        channelIdMap.remove(userId);
    }

    public static Channel getByUserId(String userId) {
        String channelId = channelIdMap.get(userId);
        return channelId == null?null:channelMap.get(channelId);
    }

}
