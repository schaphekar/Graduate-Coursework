/* Create the schema for our tables */
create table Restaurant(rID int, name varchar2(100), address varchar2(100), cuisine varchar2(100));

create table Reviewer(vID int, name varchar2(100));

create table Rating(rID int, vID int, stars int, ratingDate date);

/* Populate the tables with our data */
insert into Restaurant values(101, 'India House Restaurant', '59 W Grand Ave Chicago, IL 60654', 'Indian');
insert into Restaurant values(102, 'Bombay Wraps', '122 N Wells St Chicago, IL 60606', 'Indian');
insert into Restaurant values(103, 'Rangoli', '2421 W North Ave Chicago, IL 60647', 'Indian');
insert into Restaurant values(104, 'Cumin', '1414 N Milwaukee Ave Chicago, IL 60622', 'Indian');
insert into Restaurant values(105, 'Shanghai Inn', '4723 N Damen Ave Chicago, IL 60625', 'Chinese');
insert into Restaurant values(106, 'MingHin Cuisine', '333 E Benton Pl Chicago, IL 60601', 'Chinese');
insert into Restaurant values(107, 'Shanghai Terrace', '108 E Superior St Chicago, IL 60611', 'Chinese');
insert into Restaurant values(108, 'Jade Court', '626 S Racine Ave Chicago, IL 60607', 'Chinese');

insert into Reviewer values(2001, 'Sarah M.');
insert into Reviewer values(2002, 'Daniel L.');
insert into Reviewer values(2003, 'B. Harris');
insert into Reviewer values(2004, 'P. Suman');
insert into Reviewer values(2005, 'Suikey S.');
insert into Reviewer values(2006, 'Elizabeth T.');
insert into Reviewer values(2007, 'Cameron J.');
insert into Reviewer values(2008, 'Vivek T.');

insert into Rating values( 101, 2001,2, DATE '2011-01-22');
insert into Rating values( 101, 2001,4, DATE '2011-01-27');
insert into Rating values( 106, 2002,4, null);
insert into Rating values( 103, 2003,2, DATE '2011-01-20');
insert into Rating values( 108, 2003,4, DATE '2011-01-12');
insert into Rating values( 108, 2003,2, DATE '2011-01-30');
insert into Rating values( 101, 2004,3, DATE '2011-01-09');
insert into Rating values( 103, 2005,3, DATE '2011-01-27');
insert into Rating values( 104, 2005,2, DATE '2011-01-22');
insert into Rating values( 108, 2005,4, null);
insert into Rating values( 107, 2006,3, DATE '2011-01-15');
insert into Rating values( 106, 2006,5, DATE '2011-01-19');
insert into Rating values( 107, 2007,5, DATE '2011-01-20');
insert into Rating values( 104, 2008,3, DATE '2011-01-02');


/* Restaurants queries */

/* 1 Find the names of all restaurants offering Indian cuisine */
select name from Restaurant where cuisine = 'Indian';

/* 2 ] Find restaurant names that received a rating of 4 or 5, sort them in increasing order */
select distinct(Restaurant.name), Rating.stars
from Rating join Restaurant on Rating.rID = Restaurant.rID
where Rating.stars = 4 or Rating.stars = 5
order by Rating.stars;

/* 3 Find the names of all restaurants that have no rating */
select Restaurant.name, Restaurant.rID 
from Restaurant 
where name not in(select name from Rating join Restaurant on Rating.rID = Restaurant.rID);

/* 4 Find the names of all reviewers who have ratings with a NULL value for the date */
select Reviewer.name 
from Reviewer join Rating 
on Reviewer.vID = Rating.vID 
where Rating.ratingDate is NULL;

/* 5 For all cases where the same reviewer rated the same restaurant twice and gave it a higher rating the second time */
/* Return the reviewer's name and the name of the restaurant */
select Reviewer.name
from Restaurant, Reviewer, (select R1.rID, R1.vID
from Rating R1, Rating R2
where R1.rID = R2.rID  and R1.vID = R2.vID and R1.stars < R2.stars and R1.ratingDate < R2.ratingDate) X
where Restaurant.rID = X.rID and Reviewer.vID = X.vID;


/* 6 For each restaurant that has at least one rating, find the highest number of stars that a restaurant received. */
/* Return the restaurant name and number of stars. Sort by restaurant name */
select Restaurant.name, max(Rating.stars)  
from Restaurant join Rating on Restaurant.rID = Rating.rID
group by Restaurant.name
order by Restaurant.name; 


/* 7 For each restaurant, return name and the rating spread, i.e the difference between highest and lowest ratings given to that restaurant. */ 
/* Sort by rating spread from highest to lowest, then by restaurant name */
select name,max(stars)-min(stars) as spread 
from Restaurant join Rating 
on Restaurant.rID=Rating.rID 
group by name
order by spread desc;


/* 8 Find the difference between the average rating of Indian restaurants and the average rating of Chinese restaurants */
select round(max(a1)-min(a1),2) as Average_Indian_Vs_Chinese_Rating

from (select avg(av1) a1 from
(select avg(stars) av1 from rating join restaurant on rating.rID=restaurant.rID where Restaurant.cuisine = 'Indian' group by Rating.rID)

union

select avg(av2) a1 from
(select avg(stars) av2 from rating join Restaurant on rating.rID=restaurant.rID where Restaurant.cuisine = 'Chinese' group by Restaurant.rID));

