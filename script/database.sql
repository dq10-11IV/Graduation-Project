-- 建表SQL
-- By Junxue Zhang 

-- 创建数据库
CREATE DATABASE seucloud;
-- 切换数据库
USE seucloud;

-- 创建商品表
CREATE TABLE users
(
	id int NOT NULL COMMENT '主键',
	username varchar(20) NOT NULL COMMENT '用户名',
	password varchar(20) NOT NULL COMMENT '密码',
	PRIMARY KEY (id),
	UNIQUE INDEX UX_username (username),
	INDEX IX_username_password (username, password)
) COMMENT '用户表';

-- 创建用户表
CREATE TABLE products
(
	id int NOT NULL COMMENT '主键',
	product_name varchar(200) NOT NULL COMMENT '商品名称',
	product_index1 varchar(50) NOT NULL COMMENT '商品目录1',
	product_index2 varchar(50) NOT NULL COMMENT '商品目录2',
	PRIMARY KEY (id),
	INDEX IX_product_index1 (product_index1),
	INDEX IX_product_index2 (product_index2),
	INDEX IX_product_index1_product_index2 (product_index1, product_index2)
) COMMENT '商品表';

-- 创建商品推荐表
CREATE TABLE product_recommends
(
	 product_id int NOT NULL COMMENT '商品id',
	 recommends varchar(1000) COMMENT '推荐商品id列表',
	 PRIMARY KEY (product_id)
) COMMENT '商品推荐表';

-- 创建相似用户表
CREATE TABLE user_similar_users
(
	user_id int NOT NULL COMMENT '用户id',
	similar_users varchar(1000) COMMENT '相似用户id列表',
	PRIMARY KEY (user_id)
) COMMENT '相似用户表';

-- 创建用户推荐表
CREATE TABLE user_recommends 
(
	user_id int NOT NULL COMMENT '用户id',
	recommends varchar(1000) COMMENT '推荐商品id列表',
	PRIMARY KEY (user_id)
) COMMENT '用户推荐表';

-- 创建用户日志表
CREATE TABLE user_logs
(
	id int NOT NULL auto_increment COMMENT '主键',
	user_id int NOT NULL COMMENT '用户id',
	product_id int NOT NULL COMMENT '访问的商品id',
	add_time datetime NOT NULL COMMENT '访问时间',
	PRIMARY KEY (id),
	INDEX IX_user_id (user_id),
	INDEX IX_user_id_add_time (user_id, add_time DESC)
) COMMENT '用户日志表';

-- 创建商品热门度表
CREATE TABLE product_hots
(
	product_id int NOT NULL COMMENT '商品id',
	hot_degree int NOT NULL COMMENT '热度',
	PRIMARY KEY (product_id)
) COMMENT '商品热度表';

-- 创建搜索结果表
CREATE TABLE searches
(
	id int NOT NULL auto_increment COMMENT '主键',
	keyword varchar(50) NOT NULL COMMENT '关键词',
	results varchar(1000) NOT NULL COMMENT '推荐结果',
	PRIMARY KEY (id),
	INDEX IX_keyword (keyword)
) COMMENT '搜索表';
