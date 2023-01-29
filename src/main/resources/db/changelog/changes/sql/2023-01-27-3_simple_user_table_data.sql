-- ************************************** "users"

CREATE TABLE "users"
(
    "id"       SERIAL       NOT NULL PRIMARY KEY,
    "username" varchar(30)  NOT NULL UNIQUE,
    "password" varchar(128) NOT NULL,
    "role" varchar(50) NOT NULL
);

-- ************************************** "users-data"
INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$2aRTUZ1FTW9Z8Is2VqJFlOSSi3MAyQ6yj8AFo1tMpaTIdx1k3Ed1q', 'ADMIN');