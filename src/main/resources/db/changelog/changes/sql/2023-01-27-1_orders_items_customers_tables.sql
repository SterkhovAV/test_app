-- ****************************** "items"
CREATE TABLE "items"
(
    "id"   SERIAL      NOT NULL PRIMARY KEY,
    "name" VARCHAR(50) NOT NULL UNIQUE
);

-- ****************************** "customers"
CREATE TABLE "customers"
(
    "id"        SERIAL      NOT NULL PRIMARY KEY,
    "name"      VARCHAR(50) NOT NULL,
    "last_name" VARCHAR(50) NOT NULL,
    "age"       SMALLINT    NOT NULL
);

-- ****************************** "orders"
CREATE TABLE "orders"
(
    "id"            SERIAL                   NOT NULL PRIMARY KEY,
    "customer_id"   INT                      NOT NULL,
    "purchase_item" INT                      NOT NULL,
    "count"         INT                      NOT NULL,
    "amount"        DOUBLE PRECISION         NOT NULL,
    "purchase_date" TIMESTAMP with time zone NOT NULL,
    CONSTRAINT "FK1_orders" FOREIGN KEY ("purchase_item") REFERENCES "items" ("id") ON DELETE CASCADE,
    CONSTRAINT "FK2_orders" FOREIGN KEY ("customer_id") REFERENCES "customers" ("id") ON DELETE CASCADE
);


