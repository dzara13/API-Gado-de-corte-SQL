create table animal
(
    id         bigint not null auto_increment primary key,
    numero     int  not null,
    sexo       char not null,
    nascimento date not null,
    marca varchar(5) not null
);