create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    departments_id int references departments (id)
);

insert into departments(name) values ('Sales');
insert into departments(name) values ('Finance Department');
insert into departments(name) values ('HR');
insert into departments(name) values ('Marketing');
insert into departments(name) values ('IT');
insert into departments(name) values ('Learning and development');

insert into employees(name, departments_id) values ('Donald', 1);
insert into employees(name, departments_id) values ('Douglas', 2);
insert into employees(name, departments_id) values ('Jennifer', 3);
insert into employees(name, departments_id) values ('Michael', 4);
insert into employees(name, departments_id) values ('Susan', 5);
insert into employees(name, departments_id) values ('Shelley', 6);
insert into employees(name, departments_id) values ('Steven', 1);
insert into employees(name, departments_id) values ('Bruce', 2);
insert into employees(name, departments_id) values ('Alexander', 3);
insert into employees(name, departments_id) values ('Neena', 4);

select * from employees
            left join departments on employees.departments_id = employees.id;

select * from employees
            right join departments on employees.departments_id=employees.id;

--Error executing SELECT statement.
--ERROR: FULL JOIN is only supported with merge-joinable or hash-joinable join conditions - Connection: mydatabase: 12ms
select * from employees
            full join departments on employees.departments_id=employees.id;

select * from departments cross join employees;

select * from employees
            left join departments on employees.departments_id=employees.id
            where employees.departments_id is null;

select * from employees
            left join departments on employees.departments_id = employees.id
union
select * from employees
            right join departments on employees.departments_id=employees.id;


create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(30)
);

insert into teens(name, gender) values ('Игорь', 'муж.');
insert into teens(name, gender) values ('Настя', 'жен.');
insert into teens(name, gender) values ('Петр', 'муж.');
insert into teens(name, gender) values ('Марина', 'жен.');
insert into teens(name, gender) values ('Владимир', 'муж.');
insert into teens(name, gender) values ('Лена', 'жен.');

select * from teens;




