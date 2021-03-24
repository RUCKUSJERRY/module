DROP TABLE CHAT;
DROP SEQUENCE CHATSEQ;

CREATE SEQUENCE CHATSEQ;
CREATE TABLE CHAT (

	CHAT_SEQ			INT				PRIMARY KEY,
	MEMBER_ID			VARCHAR2(50)	NOT NULL,
	MEMBER_NAME       	VARCHAR2(20)	NOT NULL,
	CHAT_CONTENT		VARCHAR2(1000)  NOT NULL,
	CHAT_REGDATE 		DATE			NOT NULL
	
);