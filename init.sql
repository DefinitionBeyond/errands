CREATE DATABASE errands CHARACTER SET utf8 COLLATE utf8_general_ci;

use errands;

create table user(
                     id bigint not null AUTO_INCREMENT,
                     name varchar(128) not null,
                     username varchar(128) not null,
                     open_id varchar(128) not null ,
                     age int(2) default 0,
                     sex tinyint default 0,
                     phone varchar(32),
                     school varchar(255) not null,
                     avatar_url bigint not null default 0,
                     id_card_pos_url bigint not null default 0,
                     id_card_neg_url bigint not null default 0,
                     stu_card_url bigint not null default 0,
                     active tinyint NOT NULL DEFAULT '1' ,
                     status tinyint not null default '0',
                     level tinyint not null default 0,
                     `desc` varchar(255)default '' ,
                     created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
                     updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                     primary key (id),
                     unique key(open_id),
                     unique key(phone)
);

create table dynamic(
                        id bigint not null AUTO_INCREMENT,
                        uid bigint not null ,
                        status tinyint not null default 0 comment '0: 未审核，1：审核中，2：审核完成',
                        publish_status tinyint not null default 0 comment '0: 未发布，1：发布',
                        labels json default null ,
                        content varchar(255) not null ,
                        comment_num bigint not null default 0,
                        like_num bigint not null default 0,
                        position varchar(64) not null default '',
                        created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
                        updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        primary key (id)
);

create table dynamic_info(
                             id bigint not null AUTO_INCREMENT,
                             dynamic_id bigint not null,
                             info_picture bigint comment '详情图片',
                             primary key (id)
);

create table dynamic_comment(
                                id bigint not null AUTO_INCREMENT,
                                uid bigint not null,
                                dynamic_id bigint not null,
                                content varchar(255) comment '评论内容',
                                reply_id bigint not null default 0,
                                primary key (id)
);

create table dynamic_like(
                             id bigint not null AUTO_INCREMENT,
                             dynamic_id bigint not null,
                             uid bigint not null,
                             primary key (id)
);

create table file(
                     id bigint not null AUTO_INCREMENT,
                     name varchar(128) not null,
                     creator bigint not null default 0,
                     type tinyint(1) default 0,
                     created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
                     updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                     primary key (id)
);


create table label(
                      id bigint not null AUTO_INCREMENT,
                      type tinyint not null default 1 comment '0:系统标签，1:用户标签,2:动态标签,3:活动标签',
                      label_name varchar(128) not null comment '标签名',
                      created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
                      updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                      primary key (id)
);

create table merchant(
    id bigint not null AUTO_INCREMENT,
    name varchar not null comment '商家名',
    position varchar not null comment '位置',
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

create table item(
    id bigint not null AUTO_INCREMENT,
    status tinyint not null default 0 comment "0: 未审核，1：审核中，2：审核完成"
    desc varchar(255),
    show_picture_url bigint comment '展示图片',
    price int not null default 0, comment '价格',
    merchant bigint not null comment '所属商家',
    comment_num bigint not null default 0 comment '评论数',
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
)

create table item_info(
    id bigint not null AUTO_INCREMENT,
    item_id bigint not null ,
    info_picture bigint comment '详情图片'
)