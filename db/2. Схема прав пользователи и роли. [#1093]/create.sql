create table users (
    id serial primary key,
    user_name varchar(255)
);

create table roles (
    id serial primary key,
    role_name varchar(255),
    user_id int references users(id)
);

create table rules (
    id serial primary key,
    rule_name varchar(255)
);

create table roles_rules (
    id serial primary key,
    roles_id int references roles(id),
    rules_id int references rules(id)
);

create table items (
    id serial primary key,
    items_name varchar(255),
    users_id int references users(id)
);

create table comments (
    id serial primary key,
    comments varchar(255),
    items_id int references items(id)
);

create table attachs (
    id serial primary key,
    files varchar(255),
    items_id int references items(id)
);

create table categories (
    id serial primary key,
    cat_name varchar(255),
    items_id int references items(id)
);

create table states (
    id serial primary key,
    states varchar(255),
    items_id int references items(id)
);