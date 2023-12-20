--users - roles = many-to-one (у пользователя есть роли)
create table users (
    id serial primary key,
    user_name varchar(255)
);

create table roles (
    id serial primary key,
    role_name varchar(255),
    user_id int references users(id)
);

--roles - rules = many-to-many (у ролей есть права)
create table rules (
    id serial primary key,
    rule_name varchar(255)
);

create table roles_rules (
    id serial primary key,
    roles_id int references roles(id),
    rules_id int references rules(id)
);

--items - users = many-to-one (у пользователя есть заявки)
create table items (
    id serial primary key,
    items_name varchar(255),
    users_id int references users(id)
);

--items - comments = one-to-many (у заявки есть комментарии)
create table comments (
    id serial primary key,
    comments varchar(255),
    items_id int references items(id)
);

--items - attachs = one-to-many (у заявки есть приложенные файлы)
create table attachs (
    id serial primary key,
    files varchar(255),
    items_id int references items(id)
);

--items - categories = many-to-one (у заявки есть категории)
create table categories (
    id serial primary key,
    cat_name varchar(255),
    items_id int references items(id)
);

--items - states = many-to-one (у заявки есть состояния)
create table states (
    id serial primary key,
    states varchar(255),
    items_id int references items(id)
);