DROP TABLE board;
DROP SEQUENCE board_no_seq;

CREATE TABLE board
(
   no            NUMBER (8),
   title         VARCHAR2 (200) NOT NULL,
   content       VARCHAR2 (4000) NOT NULL,
   member_no     NUMBER (8),
   member_name   VARCHAR2 (30),
   view_cnt      NUMBER (10),
   reg_date      DATE NOT NULL
);

ALTER TABLE board
   ADD (dap NUMBER (8));
   
ALTER TABLE board
   ADD (my_no NUMBER (8));
   
   ALTER TABLE board
   ADD (order_no NUMBER (8));

delete from board where no = 31;
UPDATE board
   SET order_no = 3 where no =28;
UPDATE board
   SET order_no = 3 where no = 28;
SELECT * FROM board order by my_no desc, order_no;
select * from board order by no asc;
UPDATE board SET my_no=4 where my_no=0 and no=4;
ALTER TABLE board
ADD ( CONSTRAINT board_no_pk PRIMARY KEY ( no ) );

INSERT INTO board
     VALUES (board_no_seq.NEXTVAL,
             '제목이요3',
             '내용이요3',
             3,
             '작성자3',
             345,
             SYSDATE);

delete from board where no = 27;

  SELECT no,
         view_cnt,
         member_name,
         title,
         TO_CHAR (reg_date, 'YYYY-MMDD HH:MM:SS'),
         member_no
    FROM board
   WHERE dap != 1
ORDER BY reg_date DESC;



SELECT no,
       view_cnt,
       member_name,
       title,
       TO_CHAR (reg_date, 'YYYY-MMDD HH:MM:SS'),
       member_no
  FROM board
 WHERE no = 3;



CREATE SEQUENCE board_no_seq START WITH 1
                             INCREMENT BY 1
                             MAXVALUE 99999999
                             NOCACHE
                             NOCYCLE;


SELECT no,
       view_cnt,
       member_name,
       title,
       TO_CHAR (reg_date, 'YYYY-MMDD HH:MM:SS'),
       member_no
  FROM board
 WHERE    title LIKE '%와아아%'
       OR content LIKE '%와아아%'
       OR member_name LIKE '%와아아%';

SELECT *
  FROM (SELECT A.*,
               ROWNUM AS rnum,
               FLOOR ( (ROWNUM - 1) / 5 + 1) AS page,
               COUNT (*) OVER () AS totcnt
          FROM (SELECT * FROM board order by my_no desc, order_no) A)
 WHERE page = 1;

SELECT COUNT (*) FROM board where my_no=0;


COMMIT;

  SELECT *
    FROM board
ORDER BY reg_date DESC;