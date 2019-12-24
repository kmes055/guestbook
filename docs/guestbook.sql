-- Create feed information table
create table FEED (
	FNO		 INTEGER		NOT NULL COMMENT '피드번호' PRIMARY KEY,
    EMAIL	 VARCHAR(40)	NOT NULL COMMENT '이메일',
    PWD		 VARCHAR(100)	NOT NULL COMMENT '암호',
    MOD_DATE DATETIME		NOT NULL COMMENT '마지막암호변경일'
)
COMMENT '피드기본정보';

-- Set primary key
ALTER TABLE FEED
	ADD CONSTRAINT PK_MEMBERS
		primary key (
			FNO
		);
