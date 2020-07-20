create database studentManage;
use studentManage;

create table `student`(
    `name` char(10) not null ,
    `age` char(10) not null ,
    `sex` char(10) not null ,
    `studentNo` char(10) primary key ,
    `major` char(10) not null
);
insert into `student`
values
    ('李长武','20','男','2018210378','信息工程'),
    ('张艺兴','20','男','2018210379','通信工程'),
    ('孙红雷','20','男','2018210380','信息工程'),
    ('AngelBaby','20','女','2018210381','信息工程'),
    ('迪丽热巴','20','女','2018210382','通信工程'),
    ('张一山','20','男','2018210383','电子信息工程'),
    ('杨紫','20','女','2018210384','信息工程'),
    ('肖战','20','男','2018210385','通信工程'),
    ('易烊千玺','20','男','2018210386','广播电视工程');

create table `user`
(
    `userName` char(20) primary key,
    `passWd`   char(20) not null
);

insert into `user`
values
('root','123456');
