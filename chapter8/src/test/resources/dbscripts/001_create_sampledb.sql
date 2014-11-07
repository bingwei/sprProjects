/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   user_id              int not null auto_increment comment '用户Id',
   user_name            varchar(30) not null comment '用户名',
   password             varchar(30) not null default '' comment '密码',
   last_visit      date not null comment '最后登录时间',
   last_ip        varchar(20) not null default '0' comment '最后登录IP',
   primary key (user_id),
   key AK_AK_USER_USER_NAME (user_name)
)
type = InnoDB;

CREATE TABLE  t_login_log (
  login_log_id int(11) NOT NULL auto_increment,
  user_id int(11) default NULL,
  ip varchar(23) default NULL,
  login_datetime datetime default NULL,
  PRIMARY KEY  (login_log_id)
) ENGINE=InnoDB;