# test app

## Данные для авторпизации: логин admin пароль admin

## Требуется для запуска
- указать в файле "application.properties"
       spring.datasource.url=адрес БД
       spring.datasource.username=логин
       spring.datasource.password=пароль 

- чтобы не менять логин-пароль-базу можно создать базу и пользователя локально
  открываем SQL Shell (заходим в базу postgres под логином postgres)

       CREATE DATABASE test_app;
       CREATE USER test_user WITH PASSWORD 'password';
       GRANT ALL PRIVILEGES ON DATABASE test_app to test_user;
       \c test_app;
       CREATE SCHEMA test_app;
       GRANT ALL PRIVILEGES ON SCHEMA test_app to test_user;

  теперь база и пользователь созданы

- создание таблиц и первоначальное их заполнение происходит посредством liquibase 
  (миграции можно посмотреть тут - src/main/resources/db/changelog)





