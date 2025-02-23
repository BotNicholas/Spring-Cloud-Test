create table users(
    id serial,
    username varchar(50) unique,
    role varchar(50),
    password varchar(100),
    primary key (id)
);