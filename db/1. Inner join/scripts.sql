create table pupils (
    id serial primary key,
    p_name text
);

create table lessons(
    id serial primary key,
    l_name text,
    pupils_id int references pupils(id)
);

insert into pupils(p_name) values ('Alexey');
insert into pupils(p_name) values ('Kate');

insert into lessons(l_name, pupils_id) values ('Math', '1');
insert into lessons(l_name, pupils_id) values ('English', '1');
insert into lessons(l_name, pupils_id) values ('English', '2');

select * from pupils inner
join lessons on lessons.pupils_id=pupils.id;

select alias_pupils.p_name, alias_lesson.l_name
from pupils as alias_pupils inner join lessons as alias_lesson
on alias_lesson.pupils_id=alias_pupils."id"

select p.p_name as Имя, l.l_name as Предмет
from pupils as p inner join lessons as l on p.id = l.pupils_id;

select p.p_name as "Имя ученика", l.l_name as "Предметы"
from pupils as p inner join lessons as l on p.id = l.pupils_id;