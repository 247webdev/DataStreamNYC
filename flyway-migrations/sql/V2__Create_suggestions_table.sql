CREATE TABLE SUGGESTIONS (
    ID serial PRIMARY KEY,
    TITLE varchar(100) NOT NULL,
    CONTENT TEXT NOT NULL,
    USER_NAME varchar(100) NOT NULL
);
