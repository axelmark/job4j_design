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