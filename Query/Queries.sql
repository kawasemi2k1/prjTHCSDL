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

delete from sales.orders where order_id = 2;
update sales.goods set quantity = 30 where product_id = 1;

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