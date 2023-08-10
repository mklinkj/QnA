DROP TABLE IF EXISTS t_employee CASCADE;

CREATE TABLE IF NOT EXISTS t_employee (
    id    BIGINT        GENERATED BY DEFAULT AS IDENTITY(START WITH 0) PRIMARY KEY,
    name  VARCHAR(255)
);

INSERT INTO t_employee (name) VALUES ('Tomcat');
INSERT INTO t_employee (name) VALUES ('Struts');
INSERT INTO t_employee (name) VALUES ('Spring');
