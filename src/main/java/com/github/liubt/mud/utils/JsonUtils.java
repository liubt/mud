package com.github.liubt.mud.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T json2Pojo(String json, Class<T> classType) {
        try {
            return objectMapper.readerFor(classType).readValue(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String pojo2Json(Object object) {
        try {
            return objectMapper.writer().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T map2Pojo(Map<String, Object> map, Class<T> tClass) {
        return objectMapper.convertValue(map, tClass);
    }

}
