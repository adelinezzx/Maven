use test ;


create table city(
    cid int  primary key auto_increment,
    cname varchar(50),
    pid int 
);

create table province (
    pid int primary key auto_increment,
    pname varchar(50)
    
)

insert into province(pname) values('湖南省');
insert into province(pname) values('河南省');
insert into province(pname) values('湖北省');

insert into city(cname,pid) values ('长沙市',1);
insert into city(cname,pid) values ('衡阳市',1);
insert into city(cname,pid) values ('怀化市',1);

select * from city ;
select * from province;

--关联查询
select  cid , cname , province.pid as pid , pname from city 
inner join province 
on province.pid = city.pid 
where cid=1 ;
