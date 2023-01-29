-- ********************************** "items_data"
INSERT INTO items (id, name)
VALUES (1, 'Телевизор'),
       (2, 'Смартфон'),
       (3, 'Соковыжималка'),
       (4, 'Наушники'),
       (5, 'Клавиатура');

-- ********************************** "customers_data"
INSERT INTO customers ("name", "last_name", "age")
    -- old orders
VALUES ('Василий', 'Петров', 25),
       ('Николай', 'Сидоров', 16),
       ('Анатолий', 'Кузнецов', 18),
       ('Александр', 'Топоров', 18);

-- ********************************** "orders_data"
INSERT INTO orders ("customer_id", "purchase_item", "count", "amount", "purchase_date")
    -- old orders
VALUES (1, 5, 6, 120, NOW() - interval '1 year'),
       (2, 2, 8, 750, NOW() - interval '1 year'),
       (3, 4, 12, 950, NOW() - interval '1 year'),
       (4, 3, 1, 850, NOW() - interval '1 year'),
       -- ~two weeks ago orders
       (1, 1, 4, 750, NOW() - INTERVAL '2 week'),
       (2, 2, 8, 550, NOW() - INTERVAL '2 week'),
       (3, 4, 6, 1350, NOW() - INTERVAL '2 week'),
       (4, 5, 7, 150, NOW() - INTERVAL '2 week'),
       -- ~new orders
       (1, 3, 1, 850, NOW()),
       (2, 2, 2, 950, NOW()),
       (3, 1, 4, 350, NOW()),
       (4, 1, 3, 750, NOW());