-- Создание таблицы Пользователи
CREATE TABLE users
(
    id NUMBER NOT NULL,
    username VARCHAR2 (50 CHAR) UNIQUE NOT NULL,
    name VARCHAR2 (50 CHAR) NOT NULL,
    password VARCHAR2 (150 CHAR) NOT NULL,
    is_blocked NUMBER(1, 0) DEFAULT 0,
    role VARCHAR2(30 CHAR) NOT NULL
);

-- Первичный ключ таблицы Пользователи
ALTER TABLE users
    ADD (
    CONSTRAINT users_pk PRIMARY KEY (id)
  );

-- Создание последовательности для автогенерации первичного ключа
CREATE SEQUENCE users_seq START WITH 1;

-----------------------------------------------------------

-- Создание таблицы Сессии
CREATE TABLE sessions
(
    id NUMBER,
    expired_at TIMESTAMP NOT NULL,
    user_id NUMBER NOT NULL
);

-- Первичный ключ таблицы Токены
ALTER TABLE sessions
    ADD (
    CONSTRAINT sessions_pk PRIMARY KEY (id)
  );

-- Создание последовательности для автогенерации первичного ключа
CREATE SEQUENCE sessions_seq START WITH 1;

-- Создание внешнего ключа на таблицу Пользователи
ALTER TABLE sessions
    ADD CONSTRAINT sessions_fk
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE;

---------------------------------------------------------------

-- Создание таблицы Вещи
CREATE TABLE items
(
    id NUMBER NOT NULL,
    title VARCHAR2 (50 CHAR) UNIQUE NOT NULL,
    description VARCHAR2 (255 CHAR) NOT NULL,
    image VARCHAR2 (255 CHAR) NOT NULL,
    views_number NUMBER DEFAULT 0,
    category VARCHAR2 (50 CHAR) NOT NULL,
    is_available NUMBER(1, 0) DEFAULT 0,
    owner_id NUMBER NOT NULL
);

-- Первичный ключ таблицы Вещи
ALTER TABLE items
    ADD (
    CONSTRAINT items_pk PRIMARY KEY (id)
  );

-- Создание последовательности для автогенерации первичного ключа
CREATE SEQUENCE items_seq START WITH 1;

-- Создание внешнего ключа на таблицу Пользователи
ALTER TABLE items
    ADD CONSTRAINT items_users_fk
        FOREIGN KEY (owner_id)
            REFERENCES users (id);

-----------------------------------------

-- Создание таблицы Заявки на обмен
CREATE TABLE exchange_requests
(
    id NUMBER NOT NULL,
    receiver_item_id NUMBER NOT NULL,
    sender_item_id NUMBER NOT NULL,
    status VARCHAR2 (50 CHAR) DEFAULT 'In process' NOT NULL,
    exchange_date TIMESTAMP NOT NULL
);

-- Первичный ключ таблицы Заявки на обмен
ALTER TABLE exchange_requests
    ADD (
    CONSTRAINT exchange_requests_pk PRIMARY KEY (id)
  );

-- Создание последовательности для автогенерации первичного ключа
CREATE SEQUENCE exchange_requests_seq START WITH 1;

-- Создание внешнего ключа на таблицу Вещи
ALTER TABLE exchange_requests
    ADD CONSTRAINT exchange_receiver_fk
        FOREIGN KEY (receiver_item_id)
            REFERENCES items (id);

-- Создание внешнего ключа на таблицу Вещи
ALTER TABLE exchange_requests
    ADD CONSTRAINT exchange_sender_fk
        FOREIGN KEY (sender_item_id)
            REFERENCES items (id);

-- Создание таблицы Уведомления
CREATE TABLE notifications
(
    id NUMBER,
    user_id NUMBER NOT NULL,
    message VARCHAR(200 CHAR)
);

-- Первичный ключ таблицы Запросы вступления в команду
ALTER TABLE notifications
    ADD (
    CONSTRAINT notifications_pk PRIMARY KEY (id)
  );

-- Создание последовательности для автогенерации первичного ключа
CREATE SEQUENCE notifications_seq START WITH 1;

-- Создание внешнего ключа на таблицу Пользователи
ALTER TABLE notifications
    ADD CONSTRAINT user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE;