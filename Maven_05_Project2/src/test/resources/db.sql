

use test ;

create  table sysuser(
    id int   primary key  auto_increment ,
    username varchar(20)  not null,
    password varchar(32) not null 
)

insert into sysuser(username ,password) values('adeline','a');
insert into sysuser(username ,password) values('a','a');
insert into sysuser(username ,password) values('c','a');

select * from sysuser ;

delete  from  sysuser where id =  3