# test app

## Данные для авторизации: логин admin пароль admin
## server.port=7080 (можно изменить в application.properties)

## Требуется для запуска

- указать в файле "application.properties"
  spring.datasource.url=адрес БД spring.datasource.username=логин spring.datasource.password=пароль

- чтобы не менять логин-пароль-базу можно создать базу и пользователя локально открываем SQL Shell (заходим в базу
  postgres под логином postgres)

       CREATE DATABASE test_app;
       CREATE USER test_user WITH PASSWORD 'password';
       GRANT ALL PRIVILEGES ON DATABASE test_app to test_user;
       \c test_app;
       CREATE SCHEMA test_app;
       GRANT ALL PRIVILEGES ON SCHEMA test_app to test_user;

  теперь база и пользователь созданы

- создание таблиц и первоначальное их заполнение происходит посредством liquibase
  (миграции можно посмотреть тут - src/main/resources/db/changelog)


## API
Base address - http://localhost:7080/ (you can change it in "application.properties")
### http://localhost:7080/purchase-info/new-order - регистрация нового заказа

Корректный запрос:

    <?xml version="1.0" encoding="utf-8"?>
    <orderRequestDto xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <name>string</name>
      <lastName>string</lastName>
      <age>65</age>
      <purchaseItem>5</purchaseItem>
      <count>5209</count>
      <amount>3538810.2349058</amount>
      <purchaseDate>1980-03-03T21:09:22.50</purchaseDate>
    </orderRequestDto>

Ответ:

    "OK" с HTTP статусом 200

Некорректное значение в параметре age (строка вместо числа)

    <?xml version="1.0" encoding="utf-8"?>
    <orderRequestDto xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <name>string</name>
      <lastName>string</lastName>
      <age>6g5</age>
      <purchaseItem>5</purchaseItem>
      <count>5209</count>
      <amount>3538810.2349058</amount>
      <purchaseDate>1980-03-03T21:09:22.50</purchaseDate>
    </orderRequestDto>

Ответ

    {
    "timestamp": "2023-01-30T06:41:55.700+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error in line 6. Not a number: 6g5",
    "path": "/purchase-info/new-order"
    }

Отсутвует/неправильно указан тег параметра name:

    <?xml version="1.0" encoding="utf-8"?>
    <orderRequestDto xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <aname>string</name>
      <lastName>string</lastName>
      <age>65</age>
      <purchaseItem>5</purchaseItem>
      <count>5209</count>
      <amount>3538810.2349058</amount>
      <purchaseDate>1980-03-03T21:09:22.50</purchaseDate>
    </orderRequestDto>

Ответ

    {
    "timestamp": "2023-01-30T06:42:34.461+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error in line 4. cvc-complex-type.2.4.a: Invalid content was found starting with element 'aname'. One of '{name}' is expected.",
    "path": "/purchase-info/new-order"
    }


Ошибка валидации по xsd схеме:

    <?xml version="1.0" encoding="utf-8"?>
    <orderRequestDto xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <name>string</name>
      <lastName>string</lastName>
      <age>65</age>
      <purchaseItem>0</purchaseItem>
      <count>5209</count>
      <amount>3538810.2349058</amount>
      <purchaseDate>1980-03-03T21:09:22.50</purchaseDate>
    </orderRequestDto>

Ответ:

    {
    "timestamp": "2023-01-30T06:45:29.899+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Error in line 7. cvc-minExclusive-valid: Value '0' is not facet-valid with respect to minExclusive '0' for type 'items'.",
    "path": "/purchase-info/new-order"
    }