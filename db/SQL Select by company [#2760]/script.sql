create TABLE company(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

insert into company(id, name) values  (1, 'Company_1');
insert into company(id, name) values  (2, 'Company_2');
insert into company(id, name) values  (3, 'Company_3');

create TABLE person(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into person(id, name, company_id) values  (1, 'Гриша', 1);
insert into person(id, name, company_id) values  (2, 'Сергей', 2);
insert into person(id, name, company_id) values  (3, 'Миша', 3);
insert into person(id, name) values  (4, 'Миша');
insert into person(id, name) values  (5, 'Миша');

select p.name, c.name
from person as p
join company as c
on p.company_id = c.id
and p.company_id!=5

select c.name, count(p.company_id)
from person as p
join company as c
on p.company_id = c.id
group by p.company_id, c.name
having count(p.company_id)>1

