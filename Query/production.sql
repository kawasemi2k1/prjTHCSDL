CREATE SCHEMA production;
go

CREATE TABLE production.categories (
	category_id INT IDENTITY (1, 1) PRIMARY KEY,
	category_name VARCHAR (255) NOT NULL
);

CREATE TABLE production.brands (
	brand_id INT IDENTITY (1, 1) PRIMARY KEY,
	brand_name VARCHAR (255) NOT NULL,
	country VARCHAR(255) NOT NULL
);

CREATE TABLE production.products (
	product_id INT IDENTITY (1, 1) PRIMARY KEY,
	product_name VARCHAR (255) NOT NULL,
	brand_id INT NOT NULL,
	category_id INT NOT NULL,
	price DECIMAL (10, 2) NOT NULL,
	FOREIGN KEY (category_id) REFERENCES production.categories (category_id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (brand_id) REFERENCES production.brands (brand_id) ON DELETE CASCADE ON UPDATE CASCADE
);

SET IDENTITY_INSERT production.brands ON;

INSERT INTO production.brands(brand_id,brand_name, country) VALUES(1,'Electra', 'Viet Nam')
INSERT INTO production.brands(brand_id,brand_name, country) VALUES(2,'Haro', 'Viet Nam')
INSERT INTO production.brands(brand_id,brand_name, country) VALUES(3,'Heller', 'France')
INSERT INTO production.brands(brand_id,brand_name, country) VALUES(4,'Pure Cycles', 'USA')
INSERT INTO production.brands(brand_id,brand_name, country) VALUES(5,'Ritchey', 'Thailand')
INSERT INTO production.brands(brand_id,brand_name, country) VALUES(6,'Strider', 'Japan')
INSERT INTO production.brands(brand_id,brand_name, country) VALUES(7,'Sun Bicycles', 'Japan')
INSERT INTO production.brands(brand_id,brand_name, country) VALUES(8,'Surly', 'China')
INSERT INTO production.brands(brand_id,brand_name, country) VALUES(9,'Trek', 'HongKong')

SET IDENTITY_INSERT production.brands OFF;

SET IDENTITY_INSERT production.categories ON;  
INSERT INTO production.categories(category_id,category_name) VALUES(1,'Children Bicycles')
INSERT INTO production.categories(category_id,category_name) VALUES(2,'Comfort Bicycles')
INSERT INTO production.categories(category_id,category_name) VALUES(3,'Cruisers Bicycles')
INSERT INTO production.categories(category_id,category_name) VALUES(4,'Cyclocross Bicycles')
INSERT INTO production.categories(category_id,category_name) VALUES(5,'Electric Bikes')
INSERT INTO production.categories(category_id,category_name) VALUES(6,'Mountain Bikes')
INSERT INTO production.categories(category_id,category_name) VALUES(7,'Road Bikes')

SET IDENTITY_INSERT production.categories OFF;  

SET IDENTITY_INSERT production.products ON;
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(1,'Trek 820 - 2016',9,6,379.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(2,'Ritchey Timberwolf Frameset - 2016',5,6,749.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(3,'Surly Wednesday Frameset - 2016',8,6,999.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(4,'Trek Fuel EX 8 29 - 2016',9,6,2899.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(5,'Heller Shagamaw Frame - 2016',3,6,1320.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(6,'Surly Ice Cream Truck Frameset - 2016',8,6,469.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(7,'Trek Slash 8 27.5 - 2016',9,6,3999.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(8,'Trek Remedy 29 Carbon Frameset - 2016',9,6,1799.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(9,'Trek Conduit+ - 2016',9,5,2999.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(10,'Surly Straggler - 2016',8,4,1549)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(11,'Surly Straggler 650b - 2016',8,4,1680.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(12,'Electra Townie Original 21D - 2016',1,3,549.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(13,'Electra Cruiser 1 (24-Inch) - 2016',1,3,269.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(14,'Electra Girl''s Hawaii 1 (16-inch) - 2015/2016',1,3,269.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(15,'Electra Moto 1 - 2016',1,3,529.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(16,'Electra Townie Original 7D EQ - 2016',1,3,599.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(17,'Pure Cycles Vine 8-Speed - 2016',4,3,429)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(18,'Pure Cycles Western 3-Speed - Women''s - 2015/2016',4,3,449)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(19,'Pure Cycles William 3-Speed - 2016',4,3,449)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(20,'Electra Townie Original 7D EQ - Women''s - 2016',1,3,599.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(21,'Electra Cruiser 1 (24-Inch) - 2016',1,1,269.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(22,'Electra Girl''s Hawaii 1 (16-inch) - 2015/2016',1,1,269.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(23,'Electra Girl''s Hawaii 1 (20-inch) - 2015/2016',1,1,299.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(24,'Electra Townie Original 21D - 2016',1,2,549.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(25,'Electra Townie Original 7D - 2015/2016',1,2,499.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(26,'Electra Townie Original 7D EQ - 2016',1,2,599.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(27,'Surly Big Dummy Frameset - 2017',8,6,999.99)
INSERT INTO production.products(product_id, product_name, brand_id, category_id, price) VALUES(28,'Surly Karate Monkey 27.5+ Frameset - 2017',8,6,2499.99)

SET IDENTITY_INSERT production.products OFF;


-- just for test
select product_id, product_name, price, brand_name, category_name, country   from production.products
inner join production.brands on production.products.brand_id = production.brands.brand_id
inner join production.categories on production.categories.category_id = production.products.category_id

insert into production.products(product_name,brand_id, category_id,price) Values('SuperBike', 'a','a','m')
insert into production.categories(category_name) Values('SuperBike')
insert into production.brands(brand_name,country) Values('SuperBike','a')
DELETE FROM production.products
where product_id = 6
select * from production.products where brand_id = 31
select * from production.brands
select * from production.categories

delete from production.products
delete from production.brands
delete from production.categories

--Update production.products set product_name = ?, brand_id = ?, category_id = ?, price = ? where 
            

select brand_id from production.brands
where brand_name like 'b'

select category_id from production.categories
where category_name like 'a'

DELETE FROM production.brands
where brand_id between 10 and 12

DELETE FROM production.categories
where category_id between 9 and 19

