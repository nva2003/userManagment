
--DROP TABLE users IF EXISTS;



CREATE TABLE users (
  user_id         INTEGER IDENTITY PRIMARY KEY NOT NULL,
  input_user_id         VARCHAR(12) NOT NULL,
  last_name  VARCHAR_IGNORECASE(30) NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  midle_name VARCHAR(30) NOT NULL,
  phone VARCHAR(255),
  email VARCHAR(45) NOT NULL,
  company VARCHAR(255) NOT NULL,
  department VARCHAR(255),
  title VARCHAR(70),
  time_before timestamp NOT NULL,
  ip VARCHAR(70),
  NUMBER_ASOZ VARCHAR(150) NOT NULL,
  when_Created timestamp NOT NULL
  ,when_Changed timestamp NOT NULL
  ,creator VARCHAR(50)  NOT NULL
  ,editor VARCHAR(50)  NOT NULL
  ,Creator_IP VARCHAR(70) NOT NULL
  ,Editor_IP VARCHAR(70) NOT NULL

);


--CREATE INDEX users_last_name ON users (last_name);
CREATE MEMORY TABLE logins (
  user_name    VARCHAR(50)  NOT NULL PRIMARY KEY
  ,system_id  INTEGER   NOT NULL
  ,password     VARCHAR(128)  NOT NULL
  ,login_id INTEGER IDENTITY NOT NULL
  ,user_id      INTEGER NOT NULL
  ,pwd_initial  CHAR(1)  NOT NULL
  ,pwd_set_date timestamp NOT NULL
  ,pwd_expiration_time timestamp NOT NULL
  ,pwd_state    INTEGER NOT NULL
  ,when_Created timestamp NOT NULL
  ,when_Changed timestamp NOT NULL
  ,creator VARCHAR(50)  NOT NULL
  ,editor VARCHAR(50)  NOT NULL
  ,Creator_IP VARCHAR(70) NOT NULL
  ,Editor_IP VARCHAR(70) NOT NULL
);

CREATE TABLE user_roles (
  role_id     INTEGER   IDENTITY PRIMARY KEY NOT NULL
  ,user_name   VARCHAR(50) NOT NULL
  ,role_name   VARCHAR(100)      NOT NULL
  ,description VARCHAR(255)
  ,when_Created timestamp
  ,when_Changed timestamp
  ,system_id   INTEGER
  ,creator VARCHAR(50)  NOT NULL
  ,editor VARCHAR(50)  NOT NULL
  ,Creator_IP VARCHAR(70) NOT NULL
  ,Editor_IP VARCHAR(70) NOT NULL
  );

CREATE TABLE systems (
  system_id   INTEGER PRIMARY KEY,
  system_name VARCHAR(255),
  audit_id   INTEGER
);
