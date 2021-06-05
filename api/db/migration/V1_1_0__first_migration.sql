CREATE TABLE "user" (
     id           SERIAL,
     first_name   VARCHAR(255),
     last_name    VARCHAR(255),
     email_id     VARCHAR(255) UNIQUE,
     phone        VARCHAR(255),
     password     VARCHAR(255),
     secret       VARCHAR(255),
     tag          VARCHAR(255),
     _2fa         BOOLEAN NOT NULL DEFAULT FALSE,
     age          INTEGER,
     address      VARCHAR(255),
     session_id   VARCHAR(255),
     PRIMARY KEY (id)
);
CREATE TABLE "read_byte" (
     id           SERIAL,
     description   VARCHAR(255),
     tag    VARCHAR(255),
     title     VARCHAR(255),
     url     VARCHAR(255),
     PRIMARY KEY (id)
);
