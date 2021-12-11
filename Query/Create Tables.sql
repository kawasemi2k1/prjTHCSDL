create database CSDL_Project;

use CSDL_Project;

create schema production; 
go

create schema sales; 
go

create table sales.customers (
	customer_id int identity(1,1),
	name nvarchar(255) not null,
	birthday date not null,
	address nvarchar(255) not null,
	phone varchar(25) unique not null,
	email varchar(255) unique not null,
	constraint PK0 primary key (customer_id),
);

drop table sales.customers;