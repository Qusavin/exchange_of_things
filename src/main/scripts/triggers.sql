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

CREATE OR REPLACE TRIGGER items_on_insert
  BEFORE INSERT ON items
  FOR EACH ROW
BEGIN
SELECT items_seq.nextval
INTO :new.id
FROM dual;
END;

CREATE OR REPLACE TRIGGER exchange_requests_on_insert
  BEFORE INSERT ON exchange_requests
  FOR EACH ROW
BEGIN
SELECT exchange_requests_seq.nextval
INTO :new.id
FROM dual;
END;

CREATE OR REPLACE TRIGGER notifications_on_insert
  BEFORE INSERT ON notifications
  FOR EACH ROW
BEGIN
SELECT notifications_seq.nextval
INTO :new.id
FROM dual;
END;