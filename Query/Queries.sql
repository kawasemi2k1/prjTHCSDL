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