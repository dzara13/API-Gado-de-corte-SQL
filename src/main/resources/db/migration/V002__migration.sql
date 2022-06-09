CREATE TABLE animal
(
    id         bigint      not null auto_increment primary key,
    numero     varchar(50) unique,
    nascimento date        not null,
    sexo       char(1)     not null

);