/*
Navicat MySQL Result Transfer
Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : genhub
Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001
Date: 2020-01-18 22:17:57
*/
--
--
---- ----------------------------
---- Table structure for mto_options
---- ----------------------------
DROP TABLE IF EXISTS usdly_options;
CREATE SEQUENCE usdly_options_id_seq;
--
CREATE TABLE usdly_options (
  id bigint NOT NULL DEFAULT NEXTVAL ('usdly_options_id_seq'),
  key_ varchar(32) DEFAULT NULL,
  type int DEFAULT 0,
  value varchar(300) DEFAULT NULL,
  PRIMARY KEY (id)
)  ;

ALTER SEQUENCE usdly_options_id_seq RESTART WITH 17;

---- ----------------------------
---- Records of mto_options
---- ----------------------------
INSERT INTO usdly_options VALUES ('1', 'site_name', '0', 'GenHub');
INSERT INTO usdly_options VALUES ('2', 'site_domain', '0', 'genhub.online');
INSERT INTO usdly_options VALUES ('3', 'site_keywords', '0', 'Genhub,Blog, community');
INSERT INTO usdly_options VALUES ('4', 'site_description', '0', 'Genhub, Be a meaningful technical community');
INSERT INTO usdly_options VALUES ('5', 'site_metas', '0', '');
INSERT INTO usdly_options VALUES ('6', 'site_copyright', '0', 'Copyright © Genhub');
INSERT INTO usdly_options VALUES ('7', 'site_icp', '0', '');
INSERT INTO usdly_options VALUES ('8', 'qq_callback', '0', '');
INSERT INTO usdly_options VALUES ('9', 'qq_app_id', '0', '');
INSERT INTO usdly_options VALUES ('10', 'qq_app_key', '0', '');
INSERT INTO usdly_options VALUES ('11', 'weibo_callback', '0', '');
INSERT INTO usdly_options VALUES ('12', 'weibo_client_id', '0', '');
INSERT INTO usdly_options VALUES ('13', 'weibo_client_sercret', '0', '');
INSERT INTO usdly_options VALUES ('14', 'github_callback', '0', '');
INSERT INTO usdly_options VALUES ('15', 'github_client_id', '0', '');
INSERT INTO usdly_options VALUES ('16', 'github_secret_key', '0', '');

---- ----------------------------
---- Table structure for mto_user
---- ----------------------------
--DROP TABLE IF EXISTS mto_user;
--CREATE SEQUENCE mto_user_id_seq;
--
--CREATE TABLE mto_user (
--  id bigint NOT NULL DEFAULT NEXTVAL ('mto_user_id_seq'),
--  username varchar(32) DEFAULT NULL,
--  name varchar(32) DEFAULT NULL,
--  avatar varchar(128) DEFAULT 'https://s.gravatar.com/avatar/30a3742efd884b026c73eea0e1afe7f6?s=80',
--  bio varchar(250) DEFAULT NULL,
--  email varchar(64) DEFAULT NULL,
--  password varchar(64) DEFAULT NULL,
--  status int NOT NULL,
--  created_at timestamp(0) DEFAULT NULL,
--  updated_at timestamp(0) DEFAULT NULL,
--  last_login timestamp(0) DEFAULT NULL,
--  gender int NOT NULL,
--  comments int NOT NULL,
--  posts int NOT NULL,
--  signature varchar(140) DEFAULT NULL,
--  PRIMARY KEY (id),
--  CONSTRAINT UK_USERNAME UNIQUE (username)
--)  ;
--
--ALTER SEQUENCE mto_user_id_seq RESTART WITH 2;
--
---- ----------------------------
---- Records of mto_user
---- ----------------------------
--INSERT INTO mto_user VALUES ('1', 'admin', 'Salah atwa', 'https://s.gravatar.com/avatar/30a3742efd884b026c73eea0e1afe7f6?s=80','CEO', 'satwa@alinma.com', '$2a$10$Ihg6rvc3yxk0uq/.KAKiMu.jdqyQC3ES5R7geQLSdOVeehU9D4CqK', '0', '2017-08-06 17:52:41', '2017-07-26 11:08:36', '2017-10-17 13:24:13', '0', '0', '0', '');

-- ----------------------------
-- Table structure for usdly_role
-- ----------------------------
DROP TABLE IF EXISTS usdly_role;
CREATE SEQUENCE usdly_role_id_seq;

CREATE TABLE usdly_role (
  id bigint NOT NULL DEFAULT NEXTVAL ('usdly_role_id_seq'),
  description varchar(140) DEFAULT NULL,
  name varchar(32) NOT NULL,
  status int NOT NULL,
  PRIMARY KEY (id)
)  ;

ALTER SEQUENCE usdly_role_id_seq RESTART WITH 2;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO usdly_role VALUES ('1', null, 'admin', '0');
INSERT INTO usdly_role VALUES ('2', null, 'user', '0');



-- ----------------------------
-- Table structure for usdly_user_role
-- ----------------------------
DROP TABLE IF EXISTS usdly_user_role;
CREATE SEQUENCE usdly_user_role_id_seq;

CREATE TABLE usdly_user_role (
  id bigint NOT NULL DEFAULT NEXTVAL ('usdly_user_role_id_seq'),
  role_id bigint DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  PRIMARY KEY (id)
)  ;

ALTER SEQUENCE usdly_user_role_id_seq RESTART WITH 2;

-- ----------------------------
-- Records of user_role
-- ----------------------------
--INSERT INTO usdly_user_role VALUES ('1', '1', '1');