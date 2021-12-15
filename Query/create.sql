CREATE SCHEMA production;
go

CREATE SCHEMA sales;
go

CREATE TABLE production.categories (
	category_id INT IDENTITY (1, 1) PRIMARY KEY,
	category_name NVARCHAR (255) NOT NULL
);

CREATE TABLE production.brands (
	brand_id INT IDENTITY (1, 1) PRIMARY KEY,
	brand_name NVARCHAR (255) NOT NULL,
	country VARCHAR(255) NOT NULL
);

CREATE TABLE production.products (
	product_id INT IDENTITY (1, 1) PRIMARY KEY,
	product_name NVARCHAR (255) NOT NULL,
	brand_id INT NOT NULL,
	category_id INT NOT NULL,
	price DECIMAL (10, 2) NOT NULL,
	FOREIGN KEY (category_id) REFERENCES production.categories (category_id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (brand_id) REFERENCES production.brands (brand_id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table sales.customers (
	customer_id int identity(1,1),
	name nvarchar(255) not null,
	birthday date not null,
	address nvarchar(255) not null,
	phone varchar(25) not null,
	email varchar(255) not null,
	constraint PK0 primary key (customer_id)
);

CREATE TABLE sales.stores (
	store_id INT IDENTITY (1, 1) PRIMARY KEY,
	name NVARCHAR (255) NOT NULL,
	phone VARCHAR (25),
	email VARCHAR (255),
	address VARCHAR (255),
	state VARCHAR (10)
);

CREATE TABLE sales.staffs (
	staff_id INT IDENTITY (1, 1) PRIMARY KEY,
	name NVARCHAR (50) NOT NULL,
	email VARCHAR (255) NOT NULL UNIQUE,
	phone VARCHAR (25),
	active tinyint NOT NULL,
	store_id INT NOT NULL,
	manager_state INT Not null,
	gender nvarchar (10) not null,
	password varchar (20) not null,
	FOREIGN KEY (store_id) REFERENCES sales.stores (store_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE sales.orders (
	order_id INT IDENTITY (1, 1) PRIMARY KEY,
	customer_id INT,
	created_date DATE NOT NULL,
	store_id INT NOT NULL,
	staff_id INT NOT NULL,
	price decimal(10,2) not null,
	FOREIGN KEY (customer_id) REFERENCES sales.customers (customer_id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (store_id) REFERENCES sales.stores (store_id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (staff_id) REFERENCES sales.staffs (staff_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE sales.order_items (
	order_id INT,
	product_id INT NOT NULL,
	quantity INT NOT NULL,
	price DECIMAL (10, 2) NOT NULL,
	discount DECIMAL (4, 2) NOT NULL DEFAULT 0,
	FOREIGN KEY (order_id) REFERENCES sales.orders (order_id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (product_id) REFERENCES production.products (product_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE sales.products (
	store_id INT,
	product_id INT,
	created_at_date Date not null,
	good_till_date Date not null,
	price DECIMAL (10, 2) NOT NULL,
	discount DECIMAL (4, 2) NOT NULL DEFAULT 0,
	PRIMARY KEY (product_id, created_at_date, good_till_date)
);

CREATE TABLE sales.stocks (
	store_id INT,
	product_id INT,
	created_at_date Date not null,
	good_till_date Date not null,
	quantity INT,
	PRIMARY KEY (product_id, created_at_date, good_till_date),
	FOREIGN KEY (store_id) REFERENCES sales.stores (store_id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (product_id, created_at_date, good_till_date) REFERENCES sales.products (product_id, created_at_date, good_till_date) ON DELETE CASCADE ON UPDATE CASCADE
);
