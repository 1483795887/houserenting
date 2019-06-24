drop table messages;
drop table rent_info;
drop table customer;
drop table admin;


CREATE TABLE customer
(
  cid      int PRIMARY KEY AUTO_INCREMENT,
  username varchar(20) unique not null,
  password varchar(20)        not null,
  realname varchar(20)        not null,
  sex      int                not null,
  age      int                not null,
  address  varchar(40)        not null,
  tel      varchar(20)        not null
);

CREATE TABLE admin
(
  aid      int PRIMARY KEY AUTO_INCREMENT,
  username varchar(20) unique not null,
  password varchar(20)        not null,
  tel      varchar(20)        not null
);

CREATE TABLE rent_info
(
  rid       int PRIMARY KEY AUTO_INCREMENT,
  cid       int          not null,
  huxing    varchar(20)  NOT NULL,
  mianji    float        not null,
  time      varchar(20)  NOT NULL,
  zhuangxiu varchar(20)  NOT NULL,
  price     float        NOT NULL,
  address   varchar(20)  NOT NULL,
  pic       varchar(200) NOT NULL,
  examined  int          not null,
  CONSTRAINT rent_info_customer_cid_fk FOREIGN KEY (cid) REFERENCES customer (cid)
);

create table messages
(
  mid     INT(10) auto_increment
    primary key,
  title   VARCHAR(100) default '' not null,
  content VARCHAR(200) default '' not null,
  time    VARCHAR(20) default ''  not null,
  cid     INT(10)                 null,
  rid     INT(10)                 null,
  constraint Messages_customer_cid_fk
  foreign key (cid) references customer (cid),
  constraint Messages_rent_info_rid_fk
  foreign key (rid) references rent_info (rid)
);

