drop table customer;
drop table admin;
drop table rent_info;

CREATE TABLE customer
(
  cid int PRIMARY KEY AUTO_INCREMENT,
  username varchar(20) unique not null ,
  password varchar(20) not null,
  realname varchar(20) not null,
  sex int not null,
  age int not null,
  address varchar(40) not null,
  tel varchar(20) not null
);

CREATE TABLE admin
(
  aid int PRIMARY KEY AUTO_INCREMENT,
  username varchar(20) unique not null ,
  password varchar(20) not null,
  tel varchar(20) not null
);

CREATE TABLE rent_info
(
  rid int PRIMARY KEY AUTO_INCREMENT,
  cid int not null ,
  huxing varchar(20) NOT NULL,
  mianji float not null ,
  time varchar(20) NOT NULL,
  zhuangxiu varchar(20) NOT NULL,
  price float NOT NULL,
  address varchar(20) NOT NULL,
  pic varchar(20) NOT NULL,
  CONSTRAINT rent_info_customer_cid_fk FOREIGN KEY (cid) REFERENCES customer (cid)
);
