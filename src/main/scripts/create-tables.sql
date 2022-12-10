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

-- Создание таблицы Статусы обмена
CREATE TABLE exchange_statuses
(
    id NUMBER,
    name VARCHAR2(30 CHAR) UNIQUE NOT NULL
);

-- Первичный ключ таблицы Статусы обмена
ALTER TABLE exchange_statuses
    ADD (
    CONSTRAINT exchange_statuses_pk PRIMARY KEY (id)
  );

-- Создание последовательности для автогенерации первичного ключа
CREATE SEQUENCE exchange_statuses_seq START WITH 1;

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
    receiver_item_id NUMBER NOT NULL,
    sender_item_id NUMBER NOT NULL,
    status_id NUMBER NOT NULL,
    exchange_date TIMESTAMP NOT NULL
);

-- Первичный ключ таблицы Заявки на обмен
ALTER TABLE exchange_requests
    ADD (
    CONSTRAINT exchange_requests_pk PRIMARY KEY (receiver_item_id, sender_item_id, status_id)
  );

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

-- Создание внешнего ключа на таблицу Статусы обмена
ALTER TABLE exchange_requests
    ADD CONSTRAINT exchange_status_fk
        FOREIGN KEY (status_id)
            REFERENCES exchange_statuses (id);
