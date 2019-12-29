drop table FEED;

-- Create feed information table
create table FEED (
	FNO		 BIGINT 		NOT NULL COMMENT '피드번호' AUTO_INCREMENT PRIMARY KEY,
    EMAIL	 VARCHAR(40)	NOT NULL COMMENT '이메일',
    PWD		 VARCHAR(100)	NOT NULL COMMENT '암호',
    GEN_DATE DATETIME		NOT NULL COMMENT '생성일',
    MOD_DATE DATETIME		NOT NULL COMMENT '마지막암호변경일',
    CONTENT	 VARCHAR(1000)	NOT NULL COMMENT '게시글내용'
)
COMMENT '피드기본정보';

create table HISTORY (
	MNO		 BIGINT		NOT NULL COMMENT '변경일련번호' AUTO_INCREMENT PRIMARY KEY,
    FNO		 BIGINT		NOT NULL COMMENT '피드번호',
    MOD_DATE BIGINT		NOT NULL COMMENT '변경일시',
    CONTENT  VARCHAR(1000) NOT NULL COMMENT '변경내용'
)
COMMENT '변경이력';

DELIMITER $$
CREATE TRIGGER update_trig
AFTER INSERT ON FEED FOR EACH ROW
begin
	   INSERT INTO HISTORY
	   VALUES(NULL, NEW.FNO, NEW.MOD_DATE, NEW.CONTENT);
END;
$$
CREATE TRIGGER modify_trig
AFTER UPDATE ON FEED FOR EACH ROW
begin
	   INSERT INTO HISTORY
	   VALUES(NULL, NEW.FNO, NEW.MOD_DATE, NEW.CONTENT);
END;
$$
DELIMITER ;

select * from feed;
select * from history;

delete from feed where fno > 0;
delete from history where mno > 0;

drop trigger update_trig;
drop trigger modify_trig;

-- reset
alter table feed auto_increment=1;
alter table history auto_increment=1;

insert into feed values(NULL, 'a@b.c', 1234, NOW(), NOW(), 'I am lisa!');
UPDATE FEED SET CONTENT='No, I am Elsa.', MOD_DATE=NOW() WHERE FNO=1;

select fno from feed where fno=2 and pwd='1111';