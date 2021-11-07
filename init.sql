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



create table small_meal_card(
    id bigint not null AUTO_INCREMENT,
    title varchar(128) not null ,
    people_num int default 0,
    high_light varchar(128) not null default '' comment '亮点',
    detail_introduce varchar(255) not null default '' comment '详细介绍',
    explain varchar(255) not null default '' comment '活动声明',
    deadline datetime default '',
    plan_start_time datetime default '',
    plan_end_time datetime default '',
    sign_deadline datetime default '',
    labels json default null ,
    status tinyint not null default 0,
    creator bigint not null default 0 comment '创建人',
    detail_location varchar(255) default '',
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

create table small_meal_card_join_detail(
    id bigint not null AUTO_INCREMENT,
    small_meal_card_id bigint not null ,
    participant bigint not null ,
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);


create table file(
    id bigint not null AUTO_INCREMENT,
    name varchar(128) not null,
    creator bigint not null default 0,
    `org_filename` varchar(128) default '',
    type tinyint(1) default 0,
    `uri` varchar(255) default '',
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


create table run_errands(
    id bigint not null AUTO_INCREMENT,
    `code` varchar(255) not null  default '' comment '订单编号，全局唯一',
    `type` tinyint not null default 0 comment '0:帮我送，1:帮我取,2:取快递,3:其它',
    comment_num bigint not null default 0 comment '评论数',
    report bigint not null default 0 comment '举报数',
    `desc` varchar(255) not null comment '任务描述',
    school  varchar(255) not null comment '学校',
    detail_position varchar(255) not null comment '详细位置',
    task_price int default 0 comment '任务费用',
    delivery_price int default 0 comment '配送费用',
    creator bigint not null default 0,
    `status` tinyint not null default 0 comment '0：未接单，1：已接单，2：配送中，3：完成',
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    primary key (id)
);


create table merchant(
    id bigint not null AUTO_INCREMENT,
    name varchar(255) not null comment '商家名',
    creator bigint not null comment '商家管理账号',
    position varchar(255) not null comment '位置',
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    primary key (id)
);

create table merchant_staff(
    id bigint not null AUTO_INCREMENT,
    merchant_id bigint not null,
    staff_id bigint not null,
    `status` tinyint not null default 1 comment '0: 未启用，1：启用',
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    primary key (id)
);

create table item(
    id bigint not null AUTO_INCREMENT,
    status tinyint not null default 0 comment '0: 未审核，1：审核中，2：审核完成',
    `desc` varchar(255),
    show_picture_url bigint comment '展示图片',
    price bigint not null default 0 comment '价格，最小单位存储，保留到分',
    merchant_id bigint not null comment '所属商家',
    labels json default null ,
    in_stock int not null default 0 comment '库存',
    comment_num bigint not null default 0 comment '评论数',
    like_num bigint not null default 0 comment '想要数',
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    primary key (id)
);

create table item_info(
    id bigint not null AUTO_INCREMENT,
    item_id bigint not null ,
    info_picture bigint comment '详情图片',
    primary key (id)
);


create table item_comment(
    id bigint not null AUTO_INCREMENT,
    uid bigint not null,
    item_id bigint not null,
    content varchar(255) comment '评论内容',
    reply_id bigint not null default 0,
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    primary key (id)
);

create table item_like(
    id bigint not null AUTO_INCREMENT,
    item_id bigint not null,
    uid bigint not null,
    primary key (id)
);


alter errands.user add column is_merchant default 0 comment '0:不是商家，1:是商家';