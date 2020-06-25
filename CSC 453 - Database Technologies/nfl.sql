/* Table for players */
create table Player(

playerID number(4),
FirstName varchar(15),
LastName varchar(15),

playerPos varchar(15),
playerSkill number (2),

check (playerSkill between 1 and 10),

primary key (playerID)
);


/* Table for teams */
create table Team(

teamID number(4),
teamName varchar(20),
teamCity varchar(20),
teamCoach varchar(20),
teamCaptain number(4),

primary key (teamID),
foreign key (teamCaptain) references player(playerID)

);


/* Bridge table */
create table teamplayer(

teamID number(4),
playerID number(4),

primary key (teamID, playerID),
foreign key (teamID) references team(teamID),
foreign key (playerID) references player(playerID)

);


create table Injury(
injuryID number(4),
injuredPlayer number(4),
injuryDesc varchar(50),

primary key (injuryID),
foreign key (injuredPlayer) references player(playerID)
);

/* Table for games */
create table Game(

datePlayed date,

hostTeam number(4),
hostScore number(2),

guestTeam number(4),
guestScore number(2),

foreign key (hostTeam) references team(teamID),
foreign key (guestTeam) references team(teamID)

);


/* Populating tables with dummy values */
insert into player values(1001,'Aaron','Rodgers','Quarterback',10);
insert into player values(1002,'Peyton','Manning','Quarterback',9);
insert into team values(9001,'Green Bay','Packers','Mike McCarthy',1001);
insert into team values(9002,'Denver','Broncos','John Fox',1002);

insert into injury values(5001,1001,'Knee Sprain');
insert into game values(TO_DATE('2018-09-22','YYYY-MM-DD'),9001,34,9002,21);
