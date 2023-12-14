--many-to-one
create table school (
    id serial primary key,
    s_name varchar(255)
);

create table student(
    id serial primary key,
    s_name varchar(255),
    school_id int references school(id)
);

--one-to-one
create table student(
    id serial primary key,
    seria int,
    number int
);

create table student_card(
    id serial primary key,
    s_name varchar(255),
    student_card_id int references student(id)
);

--many-to-many
create table students(
     id serial primary key,
     s_name varchar(255)
 );

 create table lessons(
     id serial primary key,
     l_name varchar(255)
 );

 create table students_lessons(
     id serial primary key,
     student_id int references students(id),
     lessons_id int references lessons(id)
 );