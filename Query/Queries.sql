select * from production.brands
select * from production.categories
select * from production.products
select * from sales.customers
select * from sales.staffs
select * from sales.stores
select * from sales.goods
select * from sales.orders
select * from sales.order_items
select * from vRealCustomer
select * from vCurrentProduct
select * from vOutdatedProduct

delete from sales.orders where order_id = 0;
update sales.goods set quantity = 30 where product_id = 1;s
delete from sales.customers where customer_id = 7;

--AutoDeleteOrder
delete from sales.orders where year(getdate()) - year(created_date) = 50

--QuanLyKhachHang
Select * from vRealCustomer;

Select sales.customers.customer_id from sales.customers
left join sales.orders on sales.orders.customer_id = sales.customers.customer_id
where order_id is null;

Select customer_id from sales.customers order by customer_id;

--BanHang
Select name from sales.staffs --where staff_id = ?;

Select * from vRealCustomer

select vCurrentProduct.product_id,
production.products.product_name,
vCurrentProduct.created_at,
vCurrentProduct.good_till,
production.categories.category_name,
production.brands.brand_name,
production.brands.country,
vCurrentProduct.quantity,
vCurrentProduct.price,
vCurrentProduct.discount from vCurrentProduct
inner join production.products on vCurrentProduct.product_id = production.products.product_id
inner join production.categories on production.categories.category_id = production.products.category_id
inner join production.brands on production.brands.brand_id = production.products.brand_id
--where store_id = ?

Select order_id from sales.orders order by order_id

-- Thuy test
select ss.staff_id as StaffID, sum(soi.profit) as DoanhThu from sales.staffs ss
left join sales.orders so on so.staff_id = ss.staff_id
left join sales.order_items soi on soi.order_id = so.order_id 
where ss.store_id = 2 and (so.created_date between '2018-01-01' and '2022-01-01' or ss.staff_id not in (select staff_id from sales.orders))
group by ss.staff_id

select sc.customer_id MaKH, sum(soi.profit) as DoanhThu from sales.customers sc
left join sales.orders so on so.customer_id = sc.customer_id
left join sales.order_items soi on soi.order_id = so.order_id 
where sc.customer_id != 1 and so.created_date between '2018-01-01' and '2022-01-01'
group by sc.customer_id

select distinct
sales.order_items.product_id,
production.products.product_name,
sales.order_items.created_at,
sales.order_items.good_till,
cast(sales.order_items.price / (1.00 - sales.order_items.discount/100.00) as decimal(10,2)),
sales.order_items.quantity,
sales.order_items.discount,
sales.order_items.price
from sales.orders
inner join sales.order_items on sales.orders.order_id = sales.order_items.order_id
inner join vCurrentProduct on sales.order_items.product_id = vCurrentProduct.product_id
and sales.order_items.created_at = vCurrentProduct.created_at
and sales.order_items.good_till = vCurrentProduct.good_till
inner join production.products on production.products.product_id = vCurrentProduct.product_id
where sales.order_items.store_id = 1 and sales.orders.order_id = 1;

select sum(price) from sales.order_items where order_id = 1;

Select order_id from sales.orders order by order_id

select staff_id, 
sales.staffs.name, 
sales.staffs.email, 
sales.staffs.phone, 
active, 
sales.stores.name, 
manager_state, 
gender, 
password
from sales.staffs 
inner join sales.stores on sales.staffs.store_id = sales.stores.store_id
where staff_id = 1