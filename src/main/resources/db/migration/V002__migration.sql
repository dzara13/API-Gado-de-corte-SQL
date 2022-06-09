create table mae
(
    id bigint auto_increment
        primary key
);

create table animal
(
    id         bigint auto_increment
        primary key,
    numero     int  not null,
    sexo       char not null,
    nascimento date not null,
    constraint animal_numero_uindex
        unique (numero),
    constraint animal_mae_id_fk
        foreign key (id) references mae (id)
);