-- liquibase formatted sql

-- changeset pozdnyakova-ea:2-1
INSERT INTO documents (type, organization, date, description, patient, status)
VALUES ('Талон', 'Поликлиника №10', '2023-10-25 13:18:06.536000', 'Талон к терапевту', 'Петров', 'Новый');
INSERT INTO documents (type, organization, date, description, patient, status)
VALUES ('Справка', 'Поликлиника №2', '2023-11-05 16:45:22.789000', 'Справка в бассейн', 'Смирнов', 'Новый');
INSERT INTO documents (type, organization, date, description, patient, status)
VALUES ('Больничный', 'Городская больница', '2023-11-12 10:18:45.921000', 'Больничный лист', 'Иванов', 'Новый');
-- rollback DELETE FROM documents;