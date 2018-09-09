
 create sequence seq_stu start with 1005;
 
 use test ;
 create table student(
     stuId int   primary key auto_increment ,
     stuName varchar(20)  not null ,
     stuSex varchar(4),
     stuBirthday date ,
     classId int 
 );
 
 create table class(
   classId int  primary key auto_increment ,
   clasName varchar(20)   ,
   teacherId int
 
 );
 
 create table teacher(
   teacherId int primary key auto_increment,
   teacherName varchar(20) not null ,
   workYear int,
   professional varchar(20)
 
 );
 
 insert into class values(3002,'计科1501',5002);
 insert into class values(3001,'计科1501',5001);

 
 insert into teacher values (5001,'Adeline',3,'计算机科学与技术');
 insert into teacher values (5002,'cindy',5,'会计学');
 
 insert into student(stuName,stuSex,stuBirthday,classId) values('Ade','女',date_format(now(),'%Y-%m-%d'),3001);
 insert into student(stuName,stuSex,stuBirthday,classId)  values('bob','男',date_format('1995-10-01','%Y-%m-%d'),3001);
 insert into student(stuName,stuSex,stuBirthday,classId)  values('betty','女',date_format('1998-10-01','%Y-%m-%d'),3002);
 insert into student(stuName,stuSex,stuBirthday,classId)  values('join','男',date_format('1999-10-01','%Y-%m-%d'),3002);
 
 commit ;
 
 select * from student ;