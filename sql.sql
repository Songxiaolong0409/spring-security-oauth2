-- 身份认证系统 Identity authentication system

create databases ids;

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(48) NOT null comment '客户端ID',
  `resource_ids` varchar(256) DEFAULT NULL comment '资源ID集合,多个资源时用逗号(,)分隔',
  `client_secret` varchar(256) DEFAULT NULL comment '客户端密匙',
  `scope` varchar(256) DEFAULT NULL comment '客户受限的范围。如果范围未定义或为空（默认值），客户端不受范围限制。read write all',
  `authorized_grant_types` varchar(256) DEFAULT NULL comment '授予客户端使用授权的类型,pwd:手机号密码模式，sms：短信模式，refresh_token：刷新token，authorization_code：验证码模式，password：用户密码模式',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL comment '重定向URI',
  `authorities` varchar(256) DEFAULT NULL comment '客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔',
  `access_token_validity` int(11) DEFAULT NULL comment '访问令牌有效时间值(单位:秒) ',
  `refresh_token_validity` int(11) DEFAULT NULL comment '更新令牌有效时间值(单位:秒)',
  `additional_information` varchar(4096) DEFAULT NULL comment '预留字段',
  `autoapprove` varchar(256) DEFAULT NULL comment '用户是否自动Approval操作',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '认证客户端信息';


create table oauth_user(
user_id bigint auto_increment primary key not null comment '用户id，自增主键',
`client_id` varchar(48) NOT null comment '客户端ID',
area_code varchar(5) comment '手机区号',
mobile varchar(20) comment '手机号',
username varchar(30) comment '用户名',
password varchar(80) comment '密码',
email varchar(100) comment '邮箱',
status tinyint default 1 comment '0:无效,1:有效',
create_at datetime not null comment '创建时间',
update_at datetime comment '修改时间',
UNIQUE KEY `Index_1` (`client_id`,`area_code`,`mobile`),
UNIQUE KEY `Index_2` (`client_id`,`username`,`password`),
UNIQUE KEY `Index_3` (`client_id`,`email`,`password`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '用户表';

create table oauth_user_details(
user_detail_id bigint auto_increment primary key not null comment '用户详情id，自增主键',
user_id bigint not null comment '用户id',
display_name varchar(30) comment '昵称/姓名',
`head_icon` varchar(100) DEFAULT NULL COMMENT '头像照片',
`birthday` date DEFAULT NULL COMMENT '生日',
`gender` tinyint(4) DEFAULT NULL COMMENT '性别，0：女，1：男：2：其它',
`security_question_id1` bigint(20) DEFAULT NULL COMMENT '密保问题id1',
`answer1` varchar(50) DEFAULT NULL COMMENT '答案1',
`security_question_id2` bigint(20) DEFAULT NULL COMMENT '密保问题id2',
`answer2` varchar(50) DEFAULT NULL COMMENT '答案2',
`security_question_id3` bigint(20) DEFAULT NULL COMMENT '密保问题id3',
`answer3` varchar(50) DEFAULT NULL COMMENT '答案3',
`remarks` varchar(500) DEFAULT NULL COMMENT '备注',
create_at datetime not null comment '创建时间',
update_at datetime comment '修改时间',
UNIQUE KEY `Index_1` (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '用户表详情';

create table oauth_user_type(
user_type_id bigint auto_increment primary key not null comment '用户类型id，自增主键',
`client_id` varchar(48) NOT null comment '客户端ID',
user_type varchar(30) not null comment '用户类型'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '用户类型表';

CREATE TABLE `oauth_user_security_question` (
  `question_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `security_question` varchar(80) NOT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='密保问题'

CREATE TABLE oauth_user_modification_record (
record_id bigint auto_increment primary key not null comment '用户详情id，自增主键',
user_id bigint not null comment '用户id',
`client_id` varchar(48) NOT null comment '客户端ID',
area_code varchar(5) comment '手机区号',
mobile varchar(20) comment '手机号',
username varchar(30) comment '用户名',
email varchar(100) comment '邮箱',
create_at datetime not null comment '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账号修改记录'