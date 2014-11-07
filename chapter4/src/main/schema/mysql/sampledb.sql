/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2011-10-01 23:39:22                            */
/*==============================================================*/


drop table if exists t_comment_log;

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

