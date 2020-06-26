CREATE TABLE manufacturers (
   code number PRIMARY KEY NOT NULL,
   name varchar(50) NOT NULL 
);

CREATE TABLE products (
   code NUMBER PRIMARY KEY NOT NULL,
   name varchar(50) NOT NULL ,
   price real NOT NULL ,
   manufacturer number NOT NULL
      CONSTRAINT fk_manufacturers_code REFERENCES manufacturers(code)
);

INSERT INTO manufacturers(code,name) VALUES(1,'Sony');
INSERT INTO manufacturers(code,name) VALUES(2,'Creative Labs');
INSERT INTO manufacturers(code,name) VALUES(3,'Hewlett-Packard');
INSERT INTO manufacturers(code,name) VALUES(4,'Iomega');
INSERT INTO manufacturers(code,name) VALUES(5,'Fujitsu');
INSERT INTO manufacturers(code,name) VALUES(6,'Winchester');

INSERT INTO products(code,name,price,manufacturer) VALUES(1,'Hard drive',240,5);
INSERT INTO products(code,name,price,manufacturer) VALUES(2,'Memory',120,6);
INSERT INTO products(code,name,price,manufacturer) VALUES(3,'ZIP drive',150,4);
INSERT INTO products(code,name,price,manufacturer) VALUES(4,'Floppy disk',5,6);
INSERT INTO products(code,name,price,manufacturer) VALUES(5,'Monitor',240,1);
INSERT INTO products(code,name,price,manufacturer) VALUES(6,'DVD drive',180,2);
INSERT INTO products(code,name,price,manufacturer) VALUES(7,'CD drive',90,2);
INSERT INTO products(code,name,price,manufacturer) VALUES(8,'Printer',270,3);
INSERT INTO products(code,name,price,manufacturer) VALUES(9,'Toner cartridge',66,3);
INSERT INTO products(code,name,price,manufacturer) VALUES(10,'DVD burner',180,2);

--Computer Store Answers

/* 1 Select the names of the products with a price less than or equal to $ */
select name from Products where (price <= 200);

/* 2 Select all the products with a price between $60 and $1 */
select*from Products where (price >= 60) and (price <= 120);

/* 3 Select the name and price in cents (i.e., the price is in dollars) */
select name, (price*100) as price_in_cents from Products;

/* 4 Select the product name, price, and manufacturer name of all the products */
select Products.name, Products.price, Manufacturers.name 
from Products join Manufacturers on Products.manufacturer = Manufacturers.code;

/* 5 Select all manufactures who currently do not have any listed products */
select Manufacturers.name 
from Manufacturers
where Manufacturers.code not in(select Products.manufacturer from Products);

/* 6 Select the name of each manufacturer along with the name and price of its most expensive product */
select B.name, A.Name, A.Price
from Products A, Manufacturers B
where A.Manufacturer = B.Code and A.Price =
     (select max(A.Price)
         from Products A
         where A.Manufacturer = B.Code);


/* 7 Select the names and average prices of manufacturer whose products have an average price larger than or equal to $1 */
select a.name, avg(b.price)
from Manufacturers a join Products b
on a.code = b.Manufacturer
group by a.name
having avg(b.price)>=150;

