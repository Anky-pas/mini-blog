create database mini_blog;
use mini_blog;

create table user(
user_id int primary key auto_increment,
user_name varchar(10) not null,
sex varchar(1) not null,
age int not null,
profession varchar(10),
personal_profile varchar(50),
phone varchar(11) not null,
email varchar(20) not null,
password varchar(20) not null);

create table blog(
blog_id int primary key auto_increment,
title varchar(20) not null,
text varchar(10000) not null,
user_id int not null,
create_time datetime default current_timestamp,
star int not null,
collect int not null,
browse int not null,
foreign key(user_id) references user(user_id)
on delete cascade
);

create table comment(
comment_id int primary key auto_increment,
blog_id int not null,
parent_comment_id int not null,
user_id int not null,
text varchar(200) not null,
create_time datetime default current_timestamp,
foreign key(user_id) references user(user_id)
on delete cascade,
foreign key(blog_id) references blog(blog_id)
on delete cascade,
foreign key(parent_comment_id) references comment(comment_id)
on delete cascade
);