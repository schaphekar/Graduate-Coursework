

DROP TABLE product_price_history;
CREATE TABLE product_price_history 
(
  product_id number(5), 
  product_name varchar2(32), 
  supplier_name varchar2(32), 
  unit_price number(7,2) 
); 

DROP TABLE product CASCADE CONSTRAINTS;
CREATE TABLE product 
(
  product_id number(5), 
  product_name varchar2(32), 
  supplier_name varchar2(32), 
  unit_price number(7,2) 
); 

INSERT INTO Product VALUES (100, 'Important Product', 'Important Things', 999);
INSERT INTO Product VALUES (200, 'Ok Product', NULL, 400);
INSERT INTO Product VALUES (300, 'Unknown product', 'Things', 45);


-- Create the price_history_trigger and execute it.

CREATE or REPLACE TRIGGER price_history_trigger 

BEFORE UPDATE OF unit_price 
ON product 

FOR EACH ROW 
BEGIN 
   INSERT INTO product_price_history 
   VALUES 
       (:old.product_id, 
        :old.product_name, 
        :old.supplier_name, 
        :old.unit_price); 
END; 
/ 


SELECT * FROM product_price_history;

-- Now update the price of a product
UPDATE PRODUCT SET unit_price = 800 WHERE product_id = 100

UPDATE PRODUCT SET supplier_name = 'Very Imp. Things' WHERE product_id = 100

UPDATE PRODUCT SET unit_price = 500 WHERE product_id = 100

-- Monitor different things
DROP TABLE product_check;
CREATE TABLE product_check
(
  Message varchar2(50), 
  Check_Date DATE
);


CREATE or REPLACE TRIGGER Before_Update_Stat_product 
BEFORE UPDATE ON product 

BEGIN
   INSERT INTO product_check 
   VALUES('Before update, statement level',sysdate); 
END; 
/ 

CREATE or REPLACE TRIGGER Before_Upddate_Row_product 
BEFORE UPDATE ON product 

FOR EACH ROW 
BEGIN 
   DBMS_OUTPUT.PUT_LINE('execute --');    
   INSERT INTO product_check 
   Values('Before update row level', sysdate); 
END; 
/ 


CREATE or REPLACE TRIGGER After_Update_Stat_product 
AFTER UPDATE ON product 

BEGIN 
   INSERT INTO product_check 
   VALUES('After update, statement level', sysdate); 
END; 
/ 

CREATE or REPLACE TRIGGER After_Update_Row_product 
AFTER UPDATE ON product 

FOR EACH ROW 
BEGIN 
   INSERT INTO product_check 
   VALUES('After update, Row level', sysdate); 
END; 
/ 


-- Now update and fire all 4 triggers
UPDATE PRODUCT SET unit_price = 1111  
WHERE product_id in (100,200); 

select * from product;

select * from product_check;
