package com.campus.dev.bean;

import org.springframework.transaction.annotation.Transactional;


@Transactional(rollbackFor = Exception.class)
public @interface TransactionalForAll {
}
