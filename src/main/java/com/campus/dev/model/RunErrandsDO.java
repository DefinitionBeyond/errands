package com.campus.dev.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RunErrandsDO {
    private long id;

    private String code;

    private int type;

    private long commentNum;

    private long report;

    private String desc;

    private String school;

    private String detailPosition;

    private BigDecimal taskPrice;

    private BigDecimal deliveryPrice;

    private long creator;

    private int status;

    private Date createdAt = new Date();

    private Date updatedAt;

}
