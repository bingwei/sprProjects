drop table if exists t_comment_log;

drop table if exists t_user;

drop table if exists t_view_point;

drop table if exists t_view_space;

/*==============================================================*/
/* Table: t_comment_log                                         */
/*==============================================================*/
DROP DATABASE IF EXISTS sampledb;
CREATE DATABASE sampledb DEFAULT CHARACTER SET utf8;
USE sampledb;


create table t_comment_log
(
   log_id               int not null auto_increment,
   space_id             int not null comment '风景区',
   ip               varchar(20) not null comment 'IP',
   comment_type         int not null comment '评论类型',
   primary key (log_id)
);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   user_id              int not null auto_increment comment '用户Id',
   user_name            varchar(30) not null comment '用户名',
   password             varchar(30) not null default '' comment '密码',
   last_login_time      date not null comment '最后登录时间',
   last_login_ip        varchar(20) not null default '0' comment '最后登录IP',
   primary key (user_id),
   key AK_AK_USER_USER_NAME (user_name)
)
type = InnoDB;

INSERT INTO t_user (user_name, password, last_login_time, last_login_ip) 
VALUES ('tom', '1234', '2007-12-23', '127.0.0.1');

/*==============================================================*/
/* Table: t_view_point                                          */
/*==============================================================*/
create table t_view_point
(
   point_id             int not null auto_increment,
   space_id             int not null comment '景区ID',
   point_name           varchar(100) comment '景点名',
   ticket_price         float(7,2) comment '票价',
   img_file             varchar(100) comment '图片文件',
   description          text comment '景点描述',
   primary key (point_id)
);

/*==============================================================*/
/* Table: t_view_space                                          */
/*==============================================================*/
create table t_view_space
(
   space_id             int not null auto_increment,
   space_name           varchar(50) not null comment '景区名称',
   description          text comment '描述',
   website              varchar(100) comment '对应的网站',
   address              varchar(150) comment '所在地址',
   user_id              int not null comment '创建者',
   want_num             int comment '想去的人数',
   been_num             int comment '去过的人数',
   notwant_num          int comment '不想去的人数',
   primary key (space_id)
);

