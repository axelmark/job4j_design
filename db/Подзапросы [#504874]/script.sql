CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

insert into customers(first_name, last_name, age, country) values  ('Петр', 'Петров', 22, 'Россия');
insert into customers(first_name, last_name, age, country) values  ('Вася', 'Васильев', 26, 'Россия');
insert into customers(first_name, last_name, age, country) values  ('Гриша', 'Григорьев', 33, 'Россия');

select min(age) from customers;


CREATE TABLE orders
(
    id serial primary key,
    amount int,
    customer_id int references customers (id)
);

insert into orders(amount, customer_id) values  (3, 1);
insert into orders(amount, customer_id) values  (1, 2);
insert into orders(amount, customer_id) values  (0, 3);

select first_name from customers
where id = (select customer_id from orders where amount=0);