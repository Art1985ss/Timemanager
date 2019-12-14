INSERT INTO users (name) VALUES
	("User 1"),
    ("User 2"),
    ("User 3");
    
INSERT INTO projects (name, rate) VALUES
	 ("Project 1", 1.55),
     ("Project 2", 1.80),
     ("Project 3", 2.00),
     ("Project 4", 2.20);
     
DELIMITER $$ ;
CREATE PROCEDURE InsertRand(IN NumRows INT, IN MinVal INT, IN MaxVal INT)
    BEGIN
        DECLARE i INT;
        SET i = 1;
        START TRANSACTION;
        WHILE i <= NumRows DO
            INSERT INTO timereports (user_id, project_id, hours) VALUES
				 (0 + CEIL(RAND() * (3 - 0)),  (0 + CEIL(RAND() * (4 - 0)), (MinVal + CEIL(RAND() * (MaxVal - MinVal))));
            SET i = i + 1;
        END WHILE;
        COMMIT;
    END
DELIMITER ; $$
CALL InsertRand(15, 3, 8);

DELETE FROM timereports;

select * from user_projects where uid = 1;

select uid, sum(rate * hours), count(*) from user_projects group by uid;