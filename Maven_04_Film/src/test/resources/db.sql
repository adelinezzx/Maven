use test ;

create table filmtype(
	typeid int primary key auto_increment,
	typename varchar(50)
);

create table filminfo(
	filmid int primary key auto_increment,
	typeid int ,
	filmname varchar(50),
	actor varchar(50),
	director varchar(50),
	ticketprice double
);

alter table filminfo
   add constraint fk_filminfo_typeid
      foreign key(typeid) references filmtype(typeid);
      
select * from filmtype;

select filmid,typename,filminfo.typeid as typeid,filmname,actor,director,ticketprice
from filminfo
left join filmtype
on filmtype.typeid=filminfo.typeid

select * from filminfo;

 select filmid,typename,filminfo.typeid as   typeid,filmname,actor,director,ticketprice   from filminfo   left join   filmtype   on filmtype.typeid=filminfo.typeid    WHERE typename =  偶像爱情片
