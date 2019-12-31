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

-- Create Modify history table
create table HISTORY (
	MNO		 BIGINT		NOT NULL COMMENT '변경일련번호' AUTO_INCREMENT PRIMARY KEY,
    FNO		 BIGINT		NOT NULL COMMENT '피드번호',
    MOD_DATE BIGINT		NOT NULL COMMENT '변경일시',
    CONTENT  VARCHAR(1000) NOT NULL COMMENT '변경내용'
)
COMMENT '변경이력';

-- Trigger for Modify history
DELIMITER $$
CREATE TRIGGER update_trig
AFTER INSERT ON FEED FOR EACH ROW
begin
	IF NEW.EMAIL NOT REGEXP '^[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$' THEN
      SIGNAL sqlstate '45001' set message_text = "Invalid Email";
	END IF;
	IF LENGTH(NEW.PWD) < 4 THEN
      SIGNAL sqlstate '45001' set message_text = "Password too short";
	END IF;
	IF NEW.CONTENT = '' THEN
      SIGNAL sqlstate '45001' set message_text = "No Content";
    END IF;
	INSERT INTO HISTORY
	VALUES(NULL, NEW.FNO, NEW.MOD_DATE, NEW.CONTENT);
END;
$$
CREATE TRIGGER modify_trig
AFTER UPDATE ON FEED FOR EACH ROW
begin
	IF NEW.CONTENT = '' THEN
		SIGNAL sqlstate '45001' set message_text = "No Content";
	ELSE
		INSERT INTO HISTORY
		VALUES(NULL, NEW.FNO, NEW.MOD_DATE, NEW.CONTENT);
	END IF;
    
END;
$$
DELIMITER ;

-- Clear Table
delete from feed;
delete from history;

-- Reset Board Number
alter table feed auto_increment=1;
alter table history auto_increment=1;

-- Drop Trigger
drop trigger update_trig;
drop trigger modify_trig;

-- Test part
select * from feed;
select * from history;

insert into feed values(NULL, 'a@brave.com', '1111', NOW(), NOW(), 'What number is this?');
UPDATE FEED SET CONTENT='SQL Trigger Testing', MOD_DATE=NOW() WHERE FNO=14;

select fno from feed where fno=2 and pwd='1111';