DROP TABLE IF EXISTS ADMINISTRATION_TBL;

CREATE TABLE ADMINISTRATION_TBL(
    ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    AGE INT,
    NAME VARCHAR,
    SURNAME VARCHAR,
    MAIL VARCHAR,
    PASSWORD VARCHAR,
    TELEPHONE INT,
    ADMINISTRATOR BOOLEAN,
    REGISTRATION DATE
);
CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;