package com.campus.dev.rest.user.request;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdentifyDTO{
    private long uid;

    private long idCardPosUrl;

    private long idCardNegUrl;

    private long stuCardUrl;

    private int status;
}
