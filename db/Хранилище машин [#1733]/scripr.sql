--Создание таблиц
create table car_bodies(
    id serial primary key,
     name varchar(255)
);

create table car_engines(
    id serial primary key,
     name varchar(255)
);

create table car_transmissions(
    id serial primary key,
     name varchar(255)
);

create table car(
    id serial primary key,
     name varchar(255),
     body int references car_bodies(id),
     engine int references car_engines(id),
     transmission int references car_transmissions(id)
);

--Заполние таблицы данными.
insert into car_bodies (name) values ('Hatchback');
insert into car_bodies (name) values ('Coupe');
insert into car_bodies (name) values ('Sedan');
insert into car_bodies (name) values ('Pickup');
insert into car_bodies (name) values ('Crossover');

insert into car_engines (name) values ('V engine');
insert into car_engines (name) values ('Diesel');
insert into car_engines (name) values ('Twin cylinder');
insert into car_engines (name) values ('Electric motor');
insert into car_engines (name) values ('Petrol engine');

insert into car_transmissions (name) values ('Manual transmission');
insert into car_transmissions (name) values ('Automatic transmission');
insert into car_transmissions (name) values ('Tiptronic Transmission');

insert into car(name, body, engine, transmission) values('Audi', 2, 1, 1);
insert into car(name, body, engine, transmission) values('Ford', 1, 2, 2);
insert into car(name, body, engine, transmission) values('Bmw', 3, 3, null);
insert into car(name, body, engine, transmission) values('Alfa Romeo', null , null, 1);
insert into car(name, body, engine, transmission) values('Chrysler', 5, 5, 2);
insert into car(name, body, engine, transmission) values('Mazda', 1, 1, null);
insert into car(name, body, engine, transmission) values('Subaru', 2, 2, 1);
insert into car(name, body, engine, transmission) values('Jeep', 3, null, 2);
insert into car(name, body, engine, transmission) values('Toyota', null, null, null);
insert into car(name, body, engine, transmission) values('Jeep', 5, 5, null);

--Вывести список всех машин и все привязанные к ним детали.
select * from car_bodies as cb
left join car as c on cb.id=c.body
full join car_engines as ce on ce.id=c.engine
right join car_transmissions as ct on ct.id=c.transmission;

--Вывести кузова, которые не используются НИ в одной машине.
select cb.name from car_bodies as cb
            left join car as c
            on c.body=cb.id
            where c.body is null;

--Вывести двигатели, которые не используются НИ в одной машине
select ce.name from car_engines as ce
            left join car as c
            on c.engine=ce.id
            where c.engine is null;

--Вывести коробки передач, которые не используются НИ в одной машине
select ct.name from car_transmissions as ct
            left join car as c
            on c.transmission = ct.id
            where c.transmission is null;





