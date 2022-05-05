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

select * from user;