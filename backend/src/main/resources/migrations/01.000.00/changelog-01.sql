-- liquibase formatted sql

-- changeset pozdnyakova-ea:1-1

create table documents
(
    id           serial primary key,
    type         varchar(100) not null,
    organization varchar(50)  not null,
    date         timestamp    not null,
    description  varchar(100) not null,
    patient      varchar(60)  not null,
    status       varchar(15)  not null
);
-- rollback drop table documents