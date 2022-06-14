create table animal
(
    id         bigint auto_increment primary key,
    numero     int  not null,
    sexo       char not null,
    nascimento date not null
);