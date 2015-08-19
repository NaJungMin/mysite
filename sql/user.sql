DROP TABLE MEMBER;
DROP SEQUENCE member_no_seq;

CREATE TABLE MEMBER
(
   no         NUMBER (8),
   name       VARCHAR2 (30),
   email      VARCHAR2 (80),
   password   VARCHAR2 (30),
   gender     VARCHAR2 (10)
);

ALTER TABLE MEMBER
ADD ( CONSTRAINT member_no_pk PRIMARY KEY ( no ) );


CREATE SEQUENCE member_no_seq START WITH 1
                              INCREMENT BY 1
                              MAXVALUE 99999999
                              NOCACHE
                              NOCYCLE;

SELECT * FROM MEMBER order by no;
delete from member where no=10;

commit;

UPDATE MEMBER
   SET password = 1234, gender = 'female'
 WHERE name = '박재현';
 
 UPDATE MEMBER
   SET password = 1234
 WHERE no = 10;
 

COMMIT;


DELETE FROM MEMBER;