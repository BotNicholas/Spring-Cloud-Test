create table questions (
    id serial,
    question varchar(100),
    answer varchar(100),
    primary key (id)
);

insert into questions(question, answer) values ('Question A is?', 'A'),
                                               ('Question B is?', 'B');