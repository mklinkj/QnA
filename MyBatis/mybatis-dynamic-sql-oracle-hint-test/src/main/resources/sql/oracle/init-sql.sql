DROP TABLE t_member_test;

CREATE TABLE t_member_test
(
    id       VARCHAR2(10) PRIMARY KEY,
    pwd      VARCHAR2(10),
    name     VARCHAR2(50),
    joinDate DATE DEFAULT SYSDATE
);

INSERT INTO t_member_test
VALUES ('user01', '1111', 'user01',
        TO_DATE('2023-02-01', 'YYYY-MM-DD'));

INSERT INTO t_member_test
VALUES ('user02', '1111', 'user02',
        TO_DATE('2023-02-02', 'YYYY-MM-DD'));

INSERT INTO t_member_test
VALUES ('user03', '1111', 'user03',
        TO_DATE('2023-02-03', 'YYYY-MM-DD'));

INSERT INTO t_member_test
VALUES ('user04', '1111', 'user04',
        TO_DATE('2023-02-04', 'YYYY-MM-DD'));

INSERT INTO t_member_test
VALUES ('user05', '1111', 'user05',
        TO_DATE('2023-02-05', 'YYYY-MM-DD'));

INSERT INTO t_member_test
VALUES ('user06', '1111', 'user06',
        TO_DATE('2023-02-06', 'YYYY-MM-DD'));

INSERT INTO t_member_test
VALUES ('user07', '1111', 'user07',
        TO_DATE('2023-02-07', 'YYYY-MM-DD'));

INSERT INTO t_member_test
VALUES ('user08', '1111', 'user08',
        TO_DATE('2023-02-08', 'YYYY-MM-DD'));

INSERT INTO t_member_test
VALUES ('user09', '1111', 'user09',
        TO_DATE('2023-02-09', 'YYYY-MM-DD'));

INSERT INTO t_member_test
VALUES ('user10', '1111', 'user10',
        TO_DATE('2023-02-10', 'YYYY-MM-DD'));