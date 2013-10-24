-- 建表SQL
-- By Junxue Zhang 

-- 创建数据库
--CREATE DATABASE seucloud;
-- 切换数据库
--USE seucloud;

-- 创建商品表
CREATE TABLE users
(
	id varchar(20) NOT NULL,
	username varchar(20) NOT NULL,
	password varchar(20) NOT NULL,
	PRIMARY KEY (id)
);

-- 创建用户表
CREATE TABLE products
(
	id varchar(20) NOT NULL,
	product_name varchar(200) NOT NULL,
	product_index1 varchar(50) NOT NULL,
	product_index2 varchar(50) NOT NULL,
	PRIMARY KEY (id)
);

-- 创建商品推荐表
CREATE TABLE product_recommends
(
	 product_id varchar(20) NOT NULL,
	 recommends varchar(1000),
	 PRIMARY KEY (product_id)
);

-- 创建相似用户表
CREATE TABLE user_similar_users
(
	user_id varchar(20) NOT NULL, 
	similar_users varchar(1000),
	PRIMARY KEY (user_id)
);

-- 创建用户推荐表
CREATE TABLE user_recommends 
(
	user_id varchar(20) NOT NULL,
	recommends varchar(1000),
	PRIMARY KEY (user_id)
);

-- 创建用户日志表
CREATE TABLE user_logs
(
	id int NOT NULL auto_increment,
	user_id varchar(20) NOT NULL,
	product_id varchar(20) NOT NULL,
	add_time datetime NOT NULL,
	PRIMARY KEY (id)
);

-- 创建商品热门度表
CREATE TABLE product_hots
(
	product_id varchar(20) NOT NULL,
	hot_degree int NOT NULL,
	PRIMARY KEY (product_id)
);

-- 创建搜索结果表
CREATE TABLE searches
(
	id int NOT NULL auto_increment,
	keyword varchar(50) NOT NULL,
	results varchar(1000) NOT NULL,
	PRIMARY KEY (id)
);

-- 创建购物车表
CREATE TABLE shopping_carts
(
	id int NOT NULL auto_increment,
	user_id varchar(20) NOT NULL,
	product_id varchar(20) NOT NULL,
	product_num int NOT NULL,
	add_time datetime NOT NULL,
	PRIMARY KEY (id)
);

-- 创建询盘规则表
CREATE TABLE shopping_cart_rules
(
	id int NOT NULL auto_increment,
	`key` varchar(1000) NOT NULL,
	`value` varchar(1000) NOT NULL,
	PRIMARY KEY (id)
);