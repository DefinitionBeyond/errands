package com.campus.dev.model;


import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MerchantStaffDO {

    private long id;

    private long merchantId;

    private long staffId;

    private int status;

    private Date createdAt = new Date();

    private Date updatedAt;
}
