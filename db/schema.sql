create table type (
    id      serial primary key,
    name    text not null,
    created timestamp default now(),
    updated timestamp default now()
);

insert into type (name) values ('Две машины');
insert into type (name) values ('Машина и человек');
insert into type (name) values ('Машина и велосипед');

create table accident (
    id          serial primary key,
    name        text not null,
    description text,
    address     text,
    type_id     int references type (id),
    created     timestamp default now(),
    updated     timestamp default now()
);

create table rule (
    id      serial primary key,
    name    text not null,
    created timestamp default now(),
    updated timestamp default now()
);

insert into rule (name) values ('Статья 1');
insert into rule (name) values ('Статья 2');
insert into rule (name) values ('Статья 3');

create table accident_rule (
    accident_id int references accident (id),
    rule_id     int references rule (id),
    primary key (accident_id, rule_id)
);