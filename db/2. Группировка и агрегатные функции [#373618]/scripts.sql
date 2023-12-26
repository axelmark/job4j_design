create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into people(name) values ('Alexey');
insert into people(name) values ('Kate');

insert into devices(name, price) values ('Apple MacBook Pro', '685990');
insert into devices(name, price) values ('Apple MacBook Pro', '12990');
insert into devices(name, price) values ('Apple MacBook Pro', '990');
insert into devices(name, price) values ('Apple iPad Pro', '116990');
insert into devices(name, price) values ('Apple iPad Pro', '1990');
insert into devices(name, price) values ('Apple iPad Pro', '15990');
insert into devices(name, price) values ('Apple iPhone 15', '149990');
insert into devices(name, price) values ('Apple iPhone 15', '100990');
insert into devices(name, price) values ('Apple iPhone 15', '99990');
insert into devices(name, price) values ('Apple MacBook Air 13', '234990');
insert into devices(name, price) values ('Apple MacBook Air 13', '90500');
insert into devices(name, price) values ('Apple MacBook Air 13', '19900');
insert into devices(name, price) values ('Apple Watch Series 9', '56990');
insert into devices(name, price) values ('Apple Watch Series 9', '42590');
insert into devices(name, price) values ('Apple Watch Series 9', '25900');

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (2, 1);
insert into devices_people(device_id, people_id) values (3, 1);
insert into devices_people(device_id, people_id) values (4, 1);
insert into devices_people(device_id, people_id) values (5, 1);
insert into devices_people(device_id, people_id) values (6, 1);
insert into devices_people(device_id, people_id) values (7, 1);
insert into devices_people(device_id, people_id) values (8, 1);
insert into devices_people(device_id, people_id) values (9, 1);

insert into devices_people(device_id, people_id) values (10, 2);
insert into devices_people(device_id, people_id) values (11, 2);
insert into devices_people(device_id, people_id) values (12, 2);
insert into devices_people(device_id, people_id) values (13, 2);
insert into devices_people(device_id, people_id) values (14, 2);

select name, round(avg(price)) from devices group by name;


select p.name, d.name, avg(d.price)
from devices as d
join people as p
--join devices_people as dp
on p.id = 1
group by d.name, p.name

select p.name, d.name, d.price
from devices_people as dp
join people as p
on p.id = dp.people_id
join devices as d
on d.id = dp.device_id



-- для каждого человека среднюю цену его устройств.
--Дополнить запрос п.4. условием, что средняя стоимость устройств должна быть больше 5000.
