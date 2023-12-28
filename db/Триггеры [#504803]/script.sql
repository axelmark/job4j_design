create table products(
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--row уровень.
create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();

create
or replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where count <= 5
        AND id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);

--statement уровне.
--__________________________________________--
create trigger tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax();

create
or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2
        where id = (select id from inserted)
        and count <= 5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

--************************
--1)  Триггер должен срабатывать после вставки данных, для любого товара и просто насчитывать налог на товар (нужно прибавить налог к цене товара).
--Действовать он должен не на каждый ряд, а на запрос (statement уровень)

create trigger tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax();

create
or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2 where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

--2) Триггер должен срабатывать до вставки данных и насчитывать налог на товар (нужно прибавить налог к цене товара).
--Здесь используем row уровень.

create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();

create
or replace function discount()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

--  3) Создайте таблицу: -------
--написать триггер на row уровне, который сразу после вставки продукта в таблицу products,
--будет заносить имя, цену и текущую дату в таблицу history_of_price.

create table history_of_price(
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create trigger discount_trigger
    after insert
    on products
    for each row
    execute procedure discount();

create
or replace function discount()
    returns trigger as
$$
    BEGIN
        update history_of_price
        set price = price + price * 0.2, name=new.name, date = CURRENT_DATE()
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';