## Система обработки документов

Веб-приложение-справочник документов, позволяет просматривать список документов, создавать новые, удалять и отправлять
документы на обработку

## Структура

- `backend` - Бэкенд на java.
- `ui` - Фронтенд на react + redux.

## Подготовка

Установите:

- [node](https://nodejs.org) - front
- [openjdk](https://openjdk.java.net) 15 - java бэк
- [docker](https://www.docker.com/products/docker-desktop/) - запуск приложения с помощью контейнеров
- [offset explorer](https://www.kafkatool.com/download.html) - взаимодействие с Kafka, ручная обработка присланных
сообщений
- [postgresql](https://www.postgresql.org/download/) - база данных

Прописать в [application.yml](backend/src/main/resources/application.yml) и [docker-compose.yml](docker-compose.yml) 
пути до БД

Для создания базы данных необходимо накатить скрипты с помощью liquibaseUpdate
## Запуск
### Локальный запуск через терминал:
Для локального запуска необходимо в [application.yml](backend/src/main/resources/application.yml) изменить
```
bootstrap-servers: kafka:9092
```
на
```
bootstrap-servers: localhost:29092
```

### Запуск фронта

```
./gradlew ui:npm_run_start
```
или
1) npm install
2) npm start

### Запуск бэка
```
./gradlew backend:bootrun
```

### Адрес страницы
```
http://localhost:9000/#/
```
### Запуск через Docker:
 - Необходимо выполнить build для Dockerfile в модулях backend и ui
 - Запустить docker-compose.yml в корневом каталоге - запустятся контейнеры бэкенда, фронта и базы данных

### Запуск Kafka
Запустить в docker-compose.yml сервисы zookeper и kafka

Топик "documents" предназначен для получения документов на обработку
Ответ на документ создается с помощью Offset Explorer в топике "documents_answer"

Формат ответа на полученный документ: {"id":id,"status":status_code}, где id - номер документа, status_code - новое 
значение статуса - "DECLINED" - отклонен или "ACCEPTED" - принят