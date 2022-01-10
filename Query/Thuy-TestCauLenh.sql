-- Thuy test
use CSDL_Project

select product_name, created_at, good_till, S.price, discount, quantity  from vCurrentProduct S
inner join production.products P on S.product_id = P.product_id
 where store_id = 1

select * from sales.goods
where store_id = 1
select * from vCurrentProduct
where store_id = 1
Update vCurrentProduct set price =  5 , discount = 5, quantity =   5

insert into vCurrentProduct values(9, '2021-02-02', '2022-03-03', 1, 20000, 2, 200)

delete from vCurrentProduct
where product_id =  1  and created_at = '2021-01-01'  and good_till = '2022-02-02' and store_id = 1   

select ss.staff_id as StaffID, sum(soi.profit) as DoanhThu from sales.staffs ss
left join sales.orders so on so.staff_id = ss.staff_id
left join sales.order_items soi on soi.order_id = so.order_id 
where ss.store_id = 2 and (so.created_date between '2018-01-01' and '2022-01-31' or ss.staff_id not in (select staff_id from sales.orders))
group by ss.staff_id

select top(5) sc.customer_id as MaKH, sum(soi.profit) as DoanhThu from vRealCustomer sc
left join sales.orders so on so.customer_id = sc.customer_id
left join sales.order_items soi on soi.order_id = so.order_id 
where created_date between '2018-01-01' and '2022-01-31'
group by sc.customer_id
order by DoanhThu desc
----

select datename(m,created_date)+' '+cast(datepart(yyyy,created_date) as varchar) as MonthYear from sales.orders
select top (12) CONCAT(MONTH(created_date),'-',YEAR(created_date)) as MonthYear from sales.orders

select top(12) CONCAT(MONTH(created_date),'-',YEAR(created_date)) as MonthYear, sum(soi.profit) as DoanhThu from sales.stores ss
left join sales.order_items soi on soi.store_id = ss.store_id
left join sales.orders so on so.order_id = soi.order_id
where ss.store_id = 2
group by created_date
order by created_date desc

-- nhờ Phong sửa
select * from
(select top(12) CONCAT(MONTH(created_date),'-',YEAR(created_date)) as MonthYear, sum(soi.profit) as DoanhThu from sales.stores ss
left join sales.order_items soi on soi.store_id = ss.store_id
left join sales.orders so on so.order_id = soi.order_id
where ss.store_id = 2
group by created_date
order by created_date desc) some123
order by MonthYear asc
--order by MonthYear asc

select top CONCAT(MONTH(created_date),'-',YEAR(created_date)) as MonthYear, DoanhThu from
(
	select created_date, sum(soi.profit) as DoanhThu from sales.stores ss
	left join sales.order_items soi on soi.store_id = ss.store_id
	left join sales.orders so on so.order_id = soi.order_id
	where ss.store_id = 2
	group by created_date
	order by created_date desc ) bangTam
order by creted_date

-- nhờ Phong sửa

select created_date from sales.orders
group by created_date
order by created_date asc

select * from sales.order_items
select * from sales.customers


---
select * from sales.staffs
where store_id = 2