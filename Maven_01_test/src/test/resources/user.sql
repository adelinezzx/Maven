

use test ;

drop table users;

create table users (
    userid int primary key auto_increment ,
    username varchar(50),
    password varchar(50),
    tel  varchar(50),
    email varchar(100)

)

insert into users(username,password,tel,email) values('cindy','123','1243547863','123@qq.com');

select * from  users ;