create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('Синий кит', '36500', null);
insert into fauna(name, avg_age, discovery_date) values ('Собака', '11315', '1992-05-02');
insert into fauna(name, avg_age, discovery_date) values ('Альбатросы', '14600', '1949-12-05');
insert into fauna(name, avg_age, discovery_date) values ('Baikal oilfish', '14600', '1933-12-05');
insert into fauna(name, avg_age, discovery_date) values ('Banjo catfish', '7300', '1988-12-05');
insert into fauna(name, avg_age, discovery_date) values ('Black scalyfish', '14600', '1910-12-05');

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01'::timestamp;