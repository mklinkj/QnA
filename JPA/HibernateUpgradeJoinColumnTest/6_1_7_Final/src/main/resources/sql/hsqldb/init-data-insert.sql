-- 데이터 INSERT 스크립트
-- user 테이블 데이터 입력
INSERT INTO student (email, name, create_date)
VALUES ('tomee@apache.org', 'TomEE', '2022-09-11 13:00:00');
INSERT INTO student (email, name, create_date)
VALUES ('jpa@java.ee', 'JPA', '2022-09-12 08:00:00');
INSERT INTO student (email, name, create_date)
VALUES ('struts@struts.org', 'Struts', '2022-09-12 08:00:00');

-- membership_card 테이블 초기 데이터
INSERT INTO membership_card (card_number, user_email, expiry_date, enabled)
VALUES ('1000', 'jpa@java.ee', '2026-12-31', 1);
INSERT INTO membership_card (card_number, user_email, expiry_date, enabled)
VALUES ('1001', 'jpa@java.ee', '2027-12-31', 1);
INSERT INTO membership_card (card_number, user_email, expiry_date, enabled)
VALUES ('1002', 'jpa@java.ee', '2028-12-31', 1);

INSERT INTO membership_card (card_number, user_email, expiry_date, enabled)
VALUES ('2000', 'struts@struts.org', '2026-12-31', 1);
INSERT INTO membership_card (card_number, user_email, expiry_date, enabled)
VALUES ('2001', 'struts@struts.org', '2027-12-31', 1);
INSERT INTO membership_card (card_number, user_email, expiry_date, enabled)
VALUES ('2002', 'struts@struts.org', '2028-12-31', 1);
