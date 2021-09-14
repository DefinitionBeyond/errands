package com.campus.dev.bean;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
public @interface TransactionalForNew {
}
