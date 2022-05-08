create database dbnetflix;
use dbnetflix;
create table user(
	id bigint primary key not null auto_increment,
    full_name nvarchar(100),
    user_name nvarchar(50),
    user_password nvarchar(50),
    email nvarchar(100),
    phone nvarchar(12)
);

select * from user_manhkm;
INSERT INTO `dbnetflix`.`user_manhkm`
(`id`,
`full_name`,
`user_name`,
`user_password`,
`email`,
`phone`)
VALUES
(1,
'Khong Minh Manh',
'manhkm',
'12345678aA',
'minhmanh.evn@gmail.com',
'0373553609');
