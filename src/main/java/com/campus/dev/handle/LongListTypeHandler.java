package com.campus.dev.handle;

import java.util.List;

public class LongListTypeHandler<Long> extends BaseListTypeHandler<Long> {
    public LongListTypeHandler(Class<List<Long>> clazz) {
        super(clazz);
    }

}
