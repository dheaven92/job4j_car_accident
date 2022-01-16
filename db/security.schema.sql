create table authorities (
    id serial primary key,
    authority varchar(50) not null unique
);

create table users (
   id serial primary key,
   username varchar(50) not null unique,
   password varchar(100) not null,
   enabled boolean default true,
   authority_id int not null references authorities(id)
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$xos9O5X/nb5LW/W7oA2J6uy8qteHgZF.RywbF/.bt78/pofGwIT7e',
        (select id from authorities where authority = 'ROLE_ADMIN'));