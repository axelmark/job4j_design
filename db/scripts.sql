create table contact (
    id serial primary key,
    user_name varchar(255),
    phone varchar(255),
    description text
);

insert into contact (user_name, phone, description) values ('Alexey', '89876543210', 'text');

select * from contact;

update contact set user_name = 'Alex';

select * from contact;

delete from contact;

select * from contact;

--many-to-one и one-to-many
--create table position(
--    id serial primary key,
--    name varchar(255)
--);
--
--create table employees(
--    id serial primary key,
--    name varchar(255),
--    position_id int references position(id)
--);
--
--insert into position(name) values ('programmer');
--insert into employees(name, position_id) VALUES ('Ivan', 1);
--
--select * from employees;
--
--select * from position where id in (select position_id from employees);

--many-to-many
--create table students(
-- create table students(
--     id serial primary key,
--     name varchar(255)
-- );
--
-- create table courses(
--     id serial primary key,
--     name varchar(255)
-- );
--
-- create table students_courses(
--     id serial primary key,
--     student_id int references students(id),
--     course_id int references courses(id)
-- );
--
--insert into students(name) values ('Ivan');
--insert into students(name) values ('Kirill');
--insert into students(name) values ('Roman');
--
--insert into courses(name) values ('Java SE');
--insert into courses(name) values ('Spring');
--insert into courses(name) values ('Hibernate');
--
--insert into students_courses(student_id, course_id) values (1, 1);
--insert into students_courses(student_id, course_id) values (1, 2);
--insert into students_courses(student_id, course_id) values (1, 3);
--insert into students_courses(student_id, course_id) values (2, 1);
--insert into students_courses(student_id, course_id) values (2, 2);
--insert into students_courses(student_id, course_id) values (3, 3);

--one-to-one

--паспорт по человеку

--create table passport(
--    id serial primary key,
--    seria int,
--    number int
--);
--
--create table people(
--    id serial primary key,
--    name varchar(255),
--    passport_id int references passport(id) unique
--);

--(человека по паспорту и паспорт по человеку)
--create table passport(
--    id serial primary key,
--    seria int,
--    number int
--);
--
--create table people(
--    id serial primary key,
--    name varchar(255)
--);
--
--create table passport_people(
--    id serial primary key,
--    passport_id int references passport(id) unique,
--    people_id int references people(id) unique
--);
