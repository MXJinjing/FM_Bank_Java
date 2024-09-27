create database if not exists fmbank;
use fmbank;

drop table if exists bankusers cascade ;
create table bankusers(
    bid varchar(20) primary key,
    username varchar(10) unique not null ,
    password varchar(10) not null ,
    id varchar(20) unique not null ,
    tel varchar(20),
    sex varchar(5),
    birth date default '1999-01-01',
    balance double not null default 0
);

insert bankusers(bid, username, password, id, tel, sex, birth,balance) VALUES
('10000','admin','admin','admin','','','1999-01-01',0);

insert bankusers(bid, username, password, id, tel, sex, birth,balance) VALUES
('10001','user','user','1001000001','18899991111','男','1999-01-01',0);

insert bankusers(bid, username, password, id, tel, sex, birth,balance) VALUES
('10002','zhangsan','12345','1001000002','18899992222','男','1999-01-01',0);



select  * from bankusers where bid = '10000' and password = 'admin';
