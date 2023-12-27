create table type (
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price float
);

insert into type (name) values ('Сыр');
insert into type (name) values ('Молоко');
insert into type (name) values ('Мясо');
insert into type (name) values ('Рыба');
insert into type (name) values ('Кондитерские изделия');
insert into type (name) values ('Овощи и фрукты');
insert into type (name) values ('Хлеб и хлебобулочные продукты');

insert into product (name, type_id, expired_date, price) values ('Сыр плавленный', 1, '2023-12-25 00:00:00', 500);
insert into product (name, type_id, expired_date, price) values ('Сыр моцарелла', 1, '2023-12-25 00:00:00', 550 );
insert into product (name, type_id, expired_date, price) values ('Творог', 2, '2023-12-25 00:00:00', 400 );
insert into product (name, type_id, expired_date, price) values ('Сметана', 2, '2023-12-25 00:00:00', 350 );
insert into product (name, type_id, expired_date, price) values ('Мороженое', 2, '2023-12-25 00:00:00', 350 );
insert into product (name, type_id, expired_date, price) values ('Говядина', 3, '2023-12-25 00:00:00', 750 );
insert into product (name, type_id, expired_date, price) values ('Баранина', 3, '2023-12-25 00:00:00', 670 );
insert into product (name, type_id, expired_date, price) values ('Горбуша', 4, '2023-12-25 00:00:00', 1500 );
insert into product (name, type_id, expired_date, price) values ('Сельдь', 4, '2023-12-25 00:00:00', 800 );
insert into product (name, type_id, expired_date, price) values ('Торты, ', 5, '2023-12-25 00:00:00', 1200 );
insert into product (name, type_id, expired_date, price) values ('Пирожные', 5, '2023-12-25 00:00:00', 250 );
insert into product (name, type_id, expired_date, price) values ('Огурец', 6, '2023-12-25 00:00:00', 300 );
insert into product (name, type_id, expired_date, price) values ('Клубника', 6, '2023-12-25 00:00:00', 400 );
insert into product (name, type_id, expired_date, price) values ('Пшеничный хлеб', 7, '2023-12-25 00:00:00', 120 );
insert into product (name, type_id, expired_date, price) values ('Булочка с маком', 7, '2023-12-25 00:00:00', 80 );

select p.name, t.name
from product as p
join type as t
on t.id = p.type_id
group by p.name, t.name
having t.name='Сыр'

select name from product
where name like '%Мороженое%'

select name, expired_date from product where expired_date - current_date<=0;

select max(price) from product;

select type.name as имя_типа, count(product.name) as количество  from type join product
on product.type_id = type.id
group by type.name;

select p.name, t.name
from product as p
join type as t
on t.id = p.type_id
group by p.name, t.name
having t.name='Молоко' or t.name='Сыр'

select type.name as имя_типа, count(product.name) as количество  from type join product
on product.type_id = type.id
group by type.name
having count(product.name)<3

select product.name, type.name as типы  from product join type
on product.type_id = type.id;

