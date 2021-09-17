package com.campus.dev.model;


import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FileDO{
    private long id;

    private String name;

    private long creator;

    private String orgFilename;

    private String uri;

    private int type;

    private Date createdAt  = new Date();

    private Date updatedAt;
}