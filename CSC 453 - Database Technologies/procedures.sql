
DROP TABLE IF EXISTS AuthUsers;
CREATE TABLE AuthUsers 
(
    ID INTEGER,
    Name CHAR(10)
);


CREATE OR REPLACE PROCEDURE AddAuthUser(ID IN NUMBER) 
AS
BEGIN
    INSERT INTO AuthUsers VALUES(ID, 'Alex');
END;
/

-- Check for warnings if needed
select *
from   user_errors ur

-- Declaring variables is optional
BEGIN
   AddAuthUser(10);
END;

select * from authusers;

DROP TABLE AllUsers;
CREATE TABLE AllUsers 
(
    ID INTEGER,
    Name CHAR(10)
);

INSERT INTO AllUsers VALUES (9, 'Jane');
INSERT INTO AllUsers VALUES (10, 'Alex');
INSERT INTO AllUsers VALUES (11, 'Mike');
INSERT INTO AllUsers VALUES (12, 'Eliza');
INSERT INTO AllUsers VALUES (13, 'Elaine');

SELECT * from AuthUsers;

--CREATE PROCEDURE AddAuthUser(ID IN NUMBER) 
CREATE OR REPLACE PROCEDURE AddAuthUser(IDX IN NUMBER) 
AS
-- We do not use DECLARE here
   UName  AllUsers.Name%TYPE;
BEGIN
    SELECT Name INTO UName FROM AllUsers WHERE AllUsers.ID = IDX;

    INSERT INTO AuthUsers VALUES(IDX, UName);
END;
/


BEGIN
   AddAuthUser(11);
END;

SELECT * FROM AuthUsers;


CREATE OR REPLACE PROCEDURE AddAuthUser(IDX IN NUMBER) 
AS
-- We do not use DECLARE here
   UName  AllUsers.Name%TYPE;
   UCount NUMBER(1);
BEGIN
    SELECT COUNT(*) INTO UCount FROM AuthUsers WHERE ID = IDX;
    
    IF UCount < 1 THEN
       SELECT Name INTO UName FROM AllUsers WHERE AllUsers.ID = IDX;

       INSERT INTO AuthUsers VALUES(IDX, UName);
    ELSE
       DBMS_OUTPUT.PUT_LINE('User ' || IDX || ' is already authorized!');    
    END IF;
END;
/

BEGIN
   AddAuthUser(12);
END;

select * from AuthUsers;


CREATE OR REPLACE PROCEDURE PFactorial (p_num IN OUT NUMBER)
IS
  v_fact            NUMBER;
BEGIN
   -- Initialize!  Otherwise you get no output
   v_fact := 1;
   
   --Loop
   LOOP
     EXIT WHEN p_num = 0;
        v_fact := v_fact * p_num;
        p_num := p_num - 1;
   END LOOP;

   -- Move the v_fact value into the p_num field.
   p_num := v_fact;
END;
/

DECLARE
  my_answer NUMBER;
BEGIN
   my_answer := 7;
  
   PFactorial(my_answer);
  
   DBMS_OUTPUT.PUT_LINE('Factorial is ' || my_answer);      
END;

-- Factorial Function
CREATE OR REPLACE FUNCTION Factorial (n POSITIVE) RETURN INTEGER IS  
BEGIN
  IF n = 1 THEN  -- terminating condition
     RETURN 1;
  ELSE
     RETURN n * Factorial(n - 1);  -- recursive call
  END IF;
END;
/

DECLARE
   Result POSITIVE;
   ValN   POSITIVE;
BEGIN
   ValN := 6;
   Result := Factorial(ValN);
   DBMS_OUTPUT.PUT_LINE('Factorial is ' || Result);    
END;
/

-- Can use regular SQL Queries
SELECT ID, Factorial(ID) FROM AuthUsers;


select object_type, object_name
from user_objects
where object_type = 'PROCEDURE'
   or object_type = 'FUNCTION';
   
   