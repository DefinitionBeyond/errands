package com.campus.dev.handle;

import com.fasterxml.jackson.databind.JavaType;

import java.util.List;

abstract public class BaseListTypeHandler<T> extends JsonTypeHandler<List<T>>{

    public BaseListTypeHandler(Class<List<T>> clazz) {
        super(clazz);
    }

    @Override
    public List<T> toObject(String content, Class<List<T>> clazz) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, (Class < ? >) clazz );
        return this.readyValueByJavaType(content, javaType);
    }
}
