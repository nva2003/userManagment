
--DROP TABLE users IF EXISTS;



CREATE TABLE users (
  id         INTEGER IDENTITY PRIMARY KEY,
  last_name  VARCHAR_IGNORECASE(30),
  first_name VARCHAR(30),
  midle_name VARCHAR(30),
  address    VARCHAR(255),
  city       VARCHAR(80),
  phone VARCHAR(30),
  email VARCHAR(30),
  company VARCHAR(30),
  department VARCHAR(30),
  title VARCHAR(30),
  time_before VARCHAR(30),
  ip VARCHAR(30),
  NUMBER_ASOZ VARCHAR(30),
  when_Changed timestamp ,
  when_Created timestamp,
  id_Admin_Changed INTEGER ,
  id_Admin_Created INTEGER
);


--CREATE INDEX users_last_name ON users (last_name);
CREATE MEMORY TABLE logins (login_id INTEGER PRIMARY KEY,user_name    VARCHAR(30)  NOT NULL,password     VARCHAR(30)  NOT NULL,user_id      INTEGER,pwd_initial  BOOLEAN,pwd_set_date VARCHAR(30),pwd_state    VARCHAR(30));
CREATE TABLE user_roles (
  role_id     INTEGER     PRIMARY KEY NOT NULL,
  user_name   VARCHAR(30) NOT NULL,
  role_name   VARCHAR(30)      NOT NULL,
  description VARCHAR(30));
CREATE TABLE systems (system_id   INTEGER PRIMARY KEY,system_name VARCHAR(30),description VARCHAR(30),login_id    INTEGER,admin       INTEGER);
