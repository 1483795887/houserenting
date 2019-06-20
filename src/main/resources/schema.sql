drop table costumer;
drop table admin;

CREATE TABLE costumer
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


