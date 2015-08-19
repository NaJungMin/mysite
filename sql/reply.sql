drop table reply_board;

CREATE TABLE reply_board
(
   no            NUMBER (8),
   content       VARCHAR2 (4000) NOT NULL,
   member_no     NUMBER (8),
   post_no    	 NUMBER (8),
   member_name   VARCHAR2 (30),
   reg_date      DATE NOT NULL
);


ALTER TABLE reply_board
ADD ( CONSTRAINT reply_no_pk PRIMARY KEY ( no ) );

CREATE SEQUENCE reply_no_seq START WITH 1
                             INCREMENT BY 1
                             MAXVALUE 99999999
                             NOCACHE
                             NOCYCLE;
							 

select * from reply_board;


commit;
							 
