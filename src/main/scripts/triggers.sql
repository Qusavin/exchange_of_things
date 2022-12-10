-- Триггеры
CREATE OR REPLACE TRIGGER users_on_insert
  BEFORE INSERT ON users
  FOR EACH ROW
BEGIN
SELECT users_seq.nextval
INTO :new.id
FROM dual;
END;

CREATE OR REPLACE TRIGGER sessions_on_insert
  BEFORE INSERT ON sessions
  FOR EACH ROW
BEGIN
SELECT sessions_seq.nextval
INTO :new.id
FROM dual;
END;

CREATE OR REPLACE TRIGGER exchange_statuses_on_insert
  BEFORE INSERT ON exchange_statuses
  FOR EACH ROW
BEGIN
SELECT exchange_statuses_seq.nextval
INTO :new.id
FROM dual;
END;

CREATE OR REPLACE TRIGGER items_on_insert
  BEFORE INSERT ON items
  FOR EACH ROW
BEGIN
SELECT items_seq.nextval
INTO :new.id
FROM dual;
END;