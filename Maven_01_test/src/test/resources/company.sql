
drop table project ;
create table project (
    pid int primary key auto_increment ,
    pname varchar(50)
);


create table engneer(
   eid int primary key auto_increment,
   ename varchar(50)
)

drop table connect ;
create table connect(
   pid int ,
   eid int  ,
   primary key (pid,eid)
)

delete from  project ;

insert into project (pname) values('oop');
insert into project (pname) values('Maven');
insert into project (pname) values('mybatis');


insert into engneer (ename) values('adeline');
insert into engneer (ename) values('cindy');
insert into engneer (ename) values('bob');

insert into connect (pid,eid) values(4,1);
insert into connect (pid,eid) values(4,2);
insert into connect (pid,eid) values(4,3);
insert into connect (pid,eid) values(5,1);
insert into connect (pid,eid) values(5,2);

select * from connect ;
select * from project ;
select * from engneer ;

 select  project.pid as pid , pname, engneer.eid , ename 
             from project 
             left join connect on connect.pid = project.pid
             left join engneer on engneer.eid = connect.eid 
             where project.pid = 4
             
             
             
             
             
             
             
             
             
             
             
             