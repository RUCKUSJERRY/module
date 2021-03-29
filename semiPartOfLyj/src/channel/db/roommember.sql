DROP TABLE ROOMMEMBER;
DROP SEQUENCE ROOMMEMBERSEQ;

CREATE SEQUENCE ROOMMEMBERSEQ;
CREATE TABLE ROOMMEMBER (
	
	ROOMMEMBER_SEQ	INT				 PRIMARY KEY,
	CHANNEL_NAME 	VARCHAR2(100) 	 NOT NULL,
	MEMBER_ID 		VARCHAR2(50) 	 NOT NULL,
	MEMBER_NAME		VARCHAR2(50) 	 NOT NULL,
	REGDATE			DATE			 NOT NULL
	
);

INSERT INTO ROOMMEMBER
VALUES (ROOMMEMBERSEQ.NEXTVAL,'전체채팅방', '11', '11', SYSDATE);

INSERT INTO ROOMMEMBER 
VALUES (ROOMMEMBERSEQ.NEXTVAL,'경영진채팅방', '11', '11', SYSDATE);

INSERT INTO ROOMMEMBER 
VALUES (ROOMMEMBERSEQ.NEXTVAL,'영업채팅방', '11', '11', SYSDATE);

INSERT INTO ROOMMEMBER 
VALUES (ROOMMEMBERSEQ.NEXTVAL,'개발채팅방', '11', '11', SYSDATE);

INSERT INTO ROOMMEMBER
VALUES (ROOMMEMBERSEQ.NEXTVAL,'전체채팅방', '22', '22', SYSDATE);

INSERT INTO ROOMMEMBER 
VALUES (ROOMMEMBERSEQ.NEXTVAL,'경영진채팅방', '22', '22', SYSDATE);

INSERT INTO ROOMMEMBER 
VALUES (ROOMMEMBERSEQ.NEXTVAL,'영업채팅방', '22', '22', SYSDATE);

INSERT INTO ROOMMEMBER 
VALUES (ROOMMEMBERSEQ.NEXTVAL,'개발채팅방', '22', '22', SYSDATE);

INSERT INTO ROOMMEMBER
VALUES (ROOMMEMBERSEQ.NEXTVAL,'전체채팅방', '55', '55', SYSDATE);

INSERT INTO ROOMMEMBER 
VALUES (ROOMMEMBERSEQ.NEXTVAL,'경영진채팅방', '55', '55', SYSDATE);

INSERT INTO ROOMMEMBER 
VALUES (ROOMMEMBERSEQ.NEXTVAL,'영업채팅방', '55', '55', SYSDATE);

INSERT INTO ROOMMEMBER 
VALUES (ROOMMEMBERSEQ.NEXTVAL,'개발채팅방', '55', '55', SYSDATE);

SELECT * FROM ROOMMEMBER;