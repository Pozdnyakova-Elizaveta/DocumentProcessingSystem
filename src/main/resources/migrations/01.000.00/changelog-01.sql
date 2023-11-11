-- liquibase formatted sql

-- changeset pozdnyakova-ea:1-1

create table documents
(
    id           serial primary key,
    documentType varchar(100),
    organization varchar(50),
    date         varchar(30),
    description  varchar(100),
    patient      varchar(60),
    status       varchar(15)
);
-- rollback drop table documents