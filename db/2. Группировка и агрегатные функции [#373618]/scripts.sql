--create table devices(
--    id serial primary key,
--    name varchar(255),
--    price float
--);

--create table people(
--    id serial primary key,
--    name varchar(255)
--);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into people(name) values ('Alexey');
insert into people(name) values ('Kate');

insert into devices(name, price) values ('Apple MacBook Pro 16', '685990');
insert into devices(name, price) values ('Apple MacBook Pro', '990');
insert into devices(name, price) values ('Apple iPad Pro', '116990');
insert into devices(name, price) values ('Apple iPad Pro', '15990');
insert into devices(name, price) values ('Apple iPhone 15 Pro', '149990');
insert into devices(name, price) values ('Apple MacBook Air 13', '234990');
insert into devices(name, price) values ('Apple MacBook Air 13', '90');
insert into devices(name, price) values ('Apple iPhone 15 Pro Max', '169990');
insert into devices(name, price) values ('Apple iPhone 15 Pro Max', '129990');
insert into devices(name, price) values ('Apple Watch Series 9', '56990');
insert into devices(name, price) values ('Apple Watch Series 9', '5990');

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (2, 1);
insert into devices_people(device_id, people_id) values (3, 1);
insert into devices_people(device_id, people_id) values (4, 2);
insert into devices_people(device_id, people_id) values (5, 2);
insert into devices_people(device_id, people_id) values (6, 2);

select avg(price) from devices;

select name, avg(price) from devices group by name;

select p.name, avg(dp.price)
from devices_people as dp
join people as p
on dp.device_id = p.id
group by p.name;
