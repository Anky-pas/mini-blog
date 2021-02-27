create database mini_blog;
use mini_blog;

create table user(
user_id int primary key auto_increment,
user_name varchar(10),
phone varchar(11),
email varchar(20),
password varchar(20));

create table blog(
blog_id int primary key auto_increment,
title varchar(20),
text varchar(10000),
user_id int,
create_time datetime default current_timestamp,
star int,
collect int,
browse int);

create table comment(
comment_id int primary key auto_increment,
blog_id int,
parent_comment_id int,
user_id int,
text varchar(200),
create_time datetime default current_timestamp);