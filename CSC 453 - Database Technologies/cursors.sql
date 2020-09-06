
DROP TABLE Point2D;
CREATE TABLE Point2D
(
        X INTEGER,
        Y INTEGER,

        PRIMARY KEY (X, Y)
);

INSERT INTO Point2D VALUES (5, 6);
INSERT INTO Point2D VALUES (6, 7);
INSERT INTO Point2D VALUES (11, 13);
INSERT INTO Point2D VALUES (37, 42);
INSERT INTO Point2D VALUES (15, 14);

-- A more complicated query
 DECLARE
    /* Output variables to hold the result of the query */
  A Point2D.X%TYPE;
  B Point2D.Y%TYPE;

    /* Cursor Declaration */
 CURSOR T1Cursor IS
         SELECT X, Y
         FROM Point2D   
         WHERE X < Y;    
 BEGIN
 OPEN T1Cursor;
 LOOP
    /* retrieve each row of the result of the above query into PL/SQL
       variables */
    FETCH T1Cursor INTO a, b;

    IF T1Cursor%FOUND THEN
          DBMS_OUTPUT.PUT_LINE('I just found ' || A || ', ' || B);
    END IF;

    /* If there are no more rows to fetch, exit the loop */
    EXIT WHEN T1Cursor%NOTFOUND;

    INSERT INTO Point2D VALUES(b, a);

 END LOOP;
 
 DBMS_OUTPUT.PUT_LINE('In total, we fetched ' || T1Cursor%rowcount || ' rows.');
 /* Free cursor used by the query */
 CLOSE T1Cursor;
 END;
/

SELECT * FROM Point2D;


-- A parametrized cursor
 DECLARE
    /* Output variables to hold the result of the query */
  A Point2D.X%TYPE;
  B Point2D.Y%TYPE;

    /* Cursor Declaration */
 CURSOR T1Cursor (LOW INTEGER, HIGH INTEGER) IS
         SELECT X, Y
         FROM Point2D   
         WHERE X BETWEEN LOW AND HIGH;    
 BEGIN
 OPEN T1Cursor(1, 8);
 LOOP
    /* retrieve each row of the result of the above query into PL/SQL
       variables */
 FETCH T1Cursor INTO a, b;

    /* If there are no more rows to fetch, exit the loop */
 EXIT WHEN T1Cursor%NOTFOUND;

 DBMS_OUTPUT.PUT_LINE('In total, we fetched ' || T1Cursor%rowcount || ' row(s).');
 DBMS_OUTPUT.PUT_LINE('Now, we will try to insert (' || B || ', ' || A || ')');

 INSERT INTO Point2D VALUES(b, a);

 END LOOP;
    /* Free cursor used by the query */
 CLOSE T1Cursor;
 END;
/

SELECT * FROM Point2D;

--An implicit cursor

DECLARE  
   total_rows number(2); 
BEGIN 
   UPDATE Point2D 
   SET y = y + 5 
   WHERE x = 5;
   IF sql%notfound THEN 
      dbms_output.put_line('no points selected'); 
   ELSIF sql%found THEN 
      total_rows := sql%rowcount;
      dbms_output.put_line( total_rows || ' points selected '); 
   END IF;  
END; 
/      
