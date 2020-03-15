package com.learn.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author
 * @description ObjectMapperHolder
 * @date 2019/9/5 15:28
 **/
public class ObjectMapperHolder {
    private static ObjectMapperHolder instance = new ObjectMapperHolder();
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ObjectMapper mapper;

    private ObjectMapperHolder() {
        mapper = createMapper();
    }

    public static ObjectMapperHolder getInstance() {
        return instance;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public ObjectMapper getNewMapper() {
        //对于Spring, 单例无法工作
        return createMapper();
    }

    private static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setDateFormat(dateFormat);
        //mapper.setSerializationInclusion(Include.ALWAYS);
        return mapper;
    }
}
