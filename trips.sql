--------------------------------------------------------
--  DDL for Table TRIPS
--------------------------------------------------------

  CREATE TABLE TRIPS 
   (	"TID" INTEGER, 
	"TRIPSTATE" VARCHAR2(10 BYTE), 
	"TRAVELMODE" VARCHAR2(6 BYTE), 
	"FARE" FLOAT(12),
   Primary key(TID)
   ); 
   
   CREATE TABLE ByCar 
   (	TID INTEGER, 
	RentalCompany VARCHAR2(50 BYTE), 
	Mileage Number(4,2),
  Foreign Key (TID) REFERENCES TRIPS(TID)
   );
   
   CREATE TABLE ByTrain
   (	TID INTEGER,
	Type VARCHAR2(50 BYTE), 
  Coach VARCHAR2(30 BYTE),
  TrainSpeed VARCHAR2(10 BYTE),
	NumberofStops NUMBER(12,0),
  Foreign Key (TID) REFERENCES TRIPS(TID)
   );
   
    CREATE TABLE ByPlane
   (	TID INTEGER, 
	Airline VARCHAR2(40 BYTE), 
  Class VARCHAR2(30 BYTE),
  LayoverTime FLOAT(10),
  Foreign Key (TID) REFERENCES TRIPS(TID)
   ); 
   

INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (1,'IL','Car',100);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (2,'CA','Plane',1020);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (3,'MD','Plane',1300);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (4,'MD','Train',568);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (5,'Non-US','Plane',2300);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (6,'IL','Train',256);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (7,'Non-US','Plane',5000);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (8,'NM','Train',13);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (9,'IN','Car',400);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (10,'Non-US','Train',229);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (11,'Non-US','Plane',4500);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (12,'Non-US','Train',258);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (13,'IL','Plane',640);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (14,'Non-US','Train',138);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (15,'IL','Car',380);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (16,'IL','Car',59);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (17,'Non-US','Plane',3700);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (18,'IN','Car',88);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (19,'MD','Plane',600);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (20,'IL','Car',75);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (21,'IL','Car',156);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (22,'Non-US','Train',40);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (23,'IL','Plane',900);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (24,'MD','Train',650);
INSERT INTO Trips (TID,TripState,TravelMode,Fare) VALUES (25,'CA','Car',114);

INSERT INTO ByCar (TID,RentalCompany,Mileage) VALUES (1,'Budget',19.75);
INSERT INTO ByCar (TID,RentalCompany,Mileage) VALUES (9,'Enterprise',19.75);
INSERT INTO ByCar (TID,RentalCompany,Mileage) VALUES (15,'Almo',22.5);
INSERT INTO ByCar (TID,RentalCompany,Mileage) VALUES (16,'Personal',30);
INSERT INTO ByCar (TID,RentalCompany,Mileage) VALUES (18,'Enterprise',22.5);
INSERT INTO ByCar (TID,RentalCompany,Mileage) VALUES (20,'Personal',29);
INSERT INTO ByCar (TID,RentalCompany,Mileage) VALUES (21,'Personal',27);
INSERT INTO ByCar (TID,RentalCompany,Mileage) VALUES (25,'Enterprise',19.75);


INSERT INTO ByTrain (TID,Type,Coach,TrainSpeed,NumberOfStops) VALUES (4,'Union Pacific Liner/Express','Chair car',50,4);
INSERT INTO ByTrain (TID,Type,Coach,TrainSpeed,NumberOfStops) VALUES (6,'Amtrak/Express','Sleeper',30,3);
INSERT INTO ByTrain (TID,Type,Coach,TrainSpeed,NumberOfStops) VALUES (8,'New Mexico Liner /Express','Chair car',50,2);
INSERT INTO ByTrain (TID,Type,Coach,TrainSpeed,NumberOfStops) VALUES (10,'Japanese Nippon/SuperExpress','Chair car',70,5);
INSERT INTO ByTrain (TID,Type,Coach,TrainSpeed,NumberOfStops) VALUES (12,'Indian Railways/Bullet','Chair car',25,18);
INSERT INTO ByTrain (TID,Type,Coach,TrainSpeed,NumberOfStops) VALUES (14,'German Transit System/SuperFast','Sleeper',70,8);
INSERT INTO ByTrain (TID,Type,Coach,TrainSpeed,NumberOfStops) VALUES (22,'Indian Railways/SuperFast','Sleeper',5,14);
INSERT INTO ByTrain (TID,Type,Coach,TrainSpeed,NumberOfStops) VALUES (24,'Amtrak/Regular','Sleeper',20,2);

INSERT INTO ByPlane (TID,Airline,Class,LayoverTime) VALUES (2,'SouthWest','Premium',4);
INSERT INTO ByPlane (TID,Airline,Class,LayoverTime) VALUES (3,'American Airlines','Business',3);
INSERT INTO ByPlane (TID,Airline,Class,LayoverTime) VALUES (5,'Emirates','Economy',5);
INSERT INTO ByPlane (TID,Airline,Class,LayoverTime) VALUES (7,'Lufthansa','Economy',6);
INSERT INTO ByPlane (TID,Airline,Class,LayoverTime) VALUES (11,'Swiss Air','Economy',10);
INSERT INTO ByPlane (TID,Airline,Class,LayoverTime) VALUES (13,'SouthWest','Premium',2);
INSERT INTO ByPlane (TID,Airline,Class,LayoverTime) VALUES (17,'Korean Air','Economy',3);
INSERT INTO ByPlane (TID,Airline,Class,LayoverTime) VALUES (19,'American Airlines','Economy',1);
INSERT INTO ByPlane (TID,Airline,Class,LayoverTime) VALUES (23,'Alaska Airlines','Economy',2.5);

--------------------------------------------------------
--  Problem C Queries
--------------------------------------------------------

/* 1 List car rental companies which have a mileage of at least 27 miles/gallon */ 
select RentalCompany from ByCar where (Mileage >= 27);

/* 2 List trip IDs taken on train costing strictly more than $15 */
select TID from Trips where (Fare > 150) and TravelMode = 'Train';

/* 3 Find trip IDs and fare that are not taken in the US i.e., `Non-US` trips */ 
select TID, Fare from Trips where TripState = 'Non-US';

/* 4 Find the business class plane trip IDs that are greater than $1 */
select Trips.TID 
from ByPlane join Trips on Trips.TID=ByPlane.TID 
where ByPlane.Class = 'Business' and (Trips.Fare > 1000);

/* 5 Find any car trip more expensive than a trip taken on a train */
select Trips.TID
from ByCar join Trips on Trips.TID=ByCar.TID 
where Fare > any(select Fare from Trips where TravelMode = 'Train');

/* 6 List a pair of distinct trips that have exactly the same value of mileage */
select distinct A.TID, B.TID
from ByCar A, ByCar B
where A.Mileage = B.Mileage and A.TID < B.TID;

/* 7 List a pair of distinct train trips that do not have the same speed */
select distinct A.TID, B.TID
from ByTrain A, ByTrain B
where A.TrainSpeed != B.TrainSpeed and A.TID < B.TID;

/* 8 Find those pair of trips in the same state with the same mode of travel. List such pairs only once */
select distinct A.TID, B.TID
from Trips A, Trips B
where A.TravelMode = B.TravelMode and A.TripState = B.TripState and A.TID < B.TID;

/* 9 Find a state in which trips have been taken by all three modes of transportation: train, plane, and car */
select TripState 
from Trips 
group by TripState 
having count(distinct TravelMode) = 3;

/*10 a The most expensive trip  */
select * from Trips where fare = (select max(fare) from Trips);

/*10 b The cheapest trip taken by either the air, rail, or two separate queries.*/
select*from Trips where fare = (select min(fare) from Trips join ByTrain on Trips.TID=ByTrain.TID);
select*from Trips where fare = (select min(fare) from Trips join ByPlane on Trips.TID=ByPlane.TID);
