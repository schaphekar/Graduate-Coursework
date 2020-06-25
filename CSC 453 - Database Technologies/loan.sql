create table Loan(

loanAmount varchar(10),
dateTaken varchar(10),
loanTitle varchar(70) primary key,

riskScore  varchar(3),
debtIncRatio varchar(10),
zipCode varchar(5),
stateCode varchar(2),
lengthEmployed varchar(2),
policyCode varchar(2),

check (riskScore between 0 and 999)

);

insert into loan values ('1000.00','2007-05-26','Wedding Covered but No Honeymoon','693','10','48127','NM','4 ','0');
insert into loan values ('1000.00','2007-05-26','Consolidating Debt','703','10','01027','MA','1 ','0');
insert into loan values ('11000.00','2007-05-27','Want to consolidate my debt','715','10','21227','MD','1 ','0');
insert into loan values ('6000.00','2007-05-27','waksman','698','38.64','01727','MA','1 ','0');
insert into loan values ('1500.00','2007-05-27','mdrigo','509','9.43','20927','MD','1 ','0');
insert into loan values ('15000.00','2007-05-27','Trinfiniti','645','0','10527','NY','3 ','0');
insert into loan values ('10000.00','2007-05-27','NOTIFYi Inc','693','10','21027','MD','1 ','0');
insert into loan values ('3900.00','2007-05-27','For Justin.','700','10','46927','IN','2 ','0');
insert into loan values ('3000.00','2007-05-28','title?','694','10','80827','CO','4 ','0');
insert into loan values ('2500.00','2007-05-28','timgerst','573','11.76','40727','KY','4 ','0');
insert into loan values ('3900.00','2007-05-28','need to consolidate','710','10','70527','LA','3 ','0');
insert into loan values ('1000.00','2007-05-28','sixstrings','680','10','42427','KY','1 ','0');
insert into loan values ('3000.00','2007-05-28','bmoore5110','688','10','19027','PA','1 ','0');
insert into loan values ('1500.00','2007-05-28','MHarkins','704','10','18927','PA','3 ','0');
insert into loan values ('1000.00','2007-05-28','Moving','694','10','35427','AL','1 ','0');
insert into loan values ('8000.00','2007-05-28','Recent College Grad Wants to Pay Off CCs','708','10','37427','TN','1 ','0');
insert into loan values ('12000.00','2007-05-29','FoundersCafe.com','685','10','77027','TX','3 ','0');
insert into loan values ('1000.00','2007-05-29','UChicago2004','698','10','20727','MD','3 ','0');
insert into loan values ('15000.00','2007-05-29','Cancer is Killing My Credit','680','10','43227','OH','1 ','0');
insert into loan values ('5000.00','2007-05-29','2006-2007 College Debt Loan','680','10','01127','MA','1 ','0');
insert into loan values ('15000.00','2007-05-29','twbmc','712','10','77327','TX','1 ','0');
insert into loan values ('5000.00','2007-05-29','Bills and Vacation','704','10','21227','MD','1 ','0');
insert into loan values ('4950.00','2007-05-29','Vehicle Purchase/Used Auto','693','10','22027','VA','2 ','0');
insert into loan values ('6100.00','2007-05-29','thejanman','684','24.69','45327','OH','2 ','0');
insert into loan values ('12000.00','2007-05-29','Finish Off College','704','10','46227','IN','2 ','0');
insert into loan values ('25000.00','2007-05-29','Phuocpnn','686','10','63127','MO','1 ','0');
insert into loan values ('15000.00','2007-05-29','Established borrower consolodating debt','698','15.56','55927','MN','1 ','0');
insert into loan values ('5000.00','2007-05-29','mainmanandy','719','10','45727','OH','1 ','0');
insert into loan values ('10000.00','2007-05-30','Jaguar10301','602','2.25','22127','VA','1 ','0');
insert into loan values ('10000.00','2007-05-30','rpinho78','475','21.77','06127','CT','1 ','0');
insert into loan values ('1000.00','2007-05-30','ashtru','695','0','44327','OH','1 ','0');
insert into loan values ('4000.00','2007-05-30','sparks','671','36.8','63027','MO','1 ','0');
insert into loan values ('7000.00','2007-05-30','big fat lender','638','17.73','94127','CA','1 ','0');
insert into loan values ('25000.00','2007-05-30','toeeeekne','671','4.5','48327','FL','2 ','0');
insert into loan values ('1000.00','2007-05-30','stnthoma22','429','15.92','46227','IN','2 ','0');
insert into loan values ('6500.00','2007-05-30','natbunn','632','12.98','77327','TX','1 ','0');
insert into loan values ('2000.00','2007-05-30','SGearhart1381','579','32.64','65827','MO','1 ','0');
insert into loan values ('15000.00','2007-05-30','2dozen','474','1.55','45327','OH','4 ','0');
insert into loan values ('6000.00','2007-05-30','Good Heart Bad Credit!','463','12.34','95327','CA','1 ','0');
insert into loan values ('2500.00','2007-05-31','mbcoon','593','18.43','79027','TX','1 ','0');
insert into loan values ('12500.00','2007-05-31','boardwalk','578','31.82','77527','TX','9 ','0');
insert into loan values ('3000.00','2007-05-31','danthe83','536','0','55127','MN','1 ','0');
insert into loan values ('7500.00','2007-05-31','maryk2001','524','61.47','31627','GA','5 ','0');
insert into loan values ('5000.00','2007-05-31','ppatti113','520','8.93','02427','MA','1 ','0');
insert into loan values ('5000.00','2007-05-31','spidermonkey07','0','6','44827','OH','1 ','0');
insert into loan values ('4000.00','2007-05-31','brock1jd','658','43.8','48027','MN','1 ','0');
insert into loan values ('1000.00','2007-05-31','jprice1542','0','4.61','37327','TN','1 ','0');
insert into loan values ('5000.00','2007-05-31','mashowar','492','15.73','47427','IN','4 ','0');
insert into loan values ('1000.00','2007-05-31','jaynesweet','581','28.04','68127','NE','2 ','0');
insert into loan values ('1000.00','2007-06-01','memorial','543','41.35','32827','FL','2 ','0');
insert into loan values ('6025.00','2007-06-01','Fleurdetragedie','660','69.84','30127','GA','2 ','0');
insert into loan values ('10000.00','2007-06-01','jfirson','503','61.07','66827','KS','1 ','0');
insert into loan values ('3000.00','2007-06-01','cdc','556','20.91','78627','TX','1 ','0');
insert into loan values ('10000.00','2007-06-01','katiemass','480','8.7','45227','OH','1 ','0');
insert into loan values ('2100.00','2007-06-01','A clean sweep before buying a new house','684','10','20827','MD','1 ','0');
insert into loan values ('5000.00','2007-06-01','kennysack','592','53.76','47727','IN','1 ','0');
insert into loan values ('10000.00','2007-06-01','dreamerabove','600','3.84','02127','MA','1 ','0');
insert into loan values ('1200.00','2007-06-01','jflack','524','0','72027','AR','4 ','0');
insert into loan values ('4000.00','2007-06-01','ceodiva','460','0','30227','GA','1 ','0');
insert into loan values ('15000.00','2007-06-01','joelforeman','654','27.48','32827','FL','2 ','0');
insert into loan values ('1000.00','2007-06-01','auburnmk23','486','12.4','36827','AL','1 ','0');
insert into loan values ('2500.00','2007-06-01','Scott Stowe','520','13.1','46027','IN','1 ','0');
insert into loan values ('5000.00','2007-06-01','iamheatherleigh','0','0','40527','KY','5 ','0');
insert into loan values ('2500.00','2007-06-01','Paying for college','547','29.16','33127','FL','1 ','0');
insert into loan values ('1000.00','2007-06-01','alivrum','454','7.68','43427','OH','1 ','0');
insert into loan values ('5000.00','2007-06-02','wbnd44','509','20','43627','OH','1 ','0');
insert into loan values ('5000.00','2007-06-02','skyydogg','0','0','53527','WI','1 ','0');
insert into loan values ('1500.00','2007-06-02','Svengar7','665','0','19127','PA','1 ','0');
insert into loan values ('1000.00','2007-06-02','sopinksolovely','454','11.12','11327','NY','2 ','0');
insert into loan values ('1000.00','2007-06-02','angelbabe','561','1.07','10427','NY','1 ','0');
insert into loan values ('8000.00','2007-06-02','K9Genetics','565','11.67','85227','AZ','1 ','0');
insert into loan values ('3500.00','2007-06-02','zminer','479','1.73','80527','CO','1 ','0');
insert into loan values ('2500.00','2007-06-02','moecheeks21','444','0','67427','KS','1 ','0');
insert into loan values ('25000.00','2007-06-02','Lilly','549','20.06','85227','AZ','4 ','0');
insert into loan values ('4500.00','2007-06-02','QuisyFBaby','599','16.8','21227','MD','1 ','0');
insert into loan values ('2000.00','2007-06-03','thesassaman','650','0','98027','WA','1 ','0');
insert into loan values ('10000.00','2007-06-03','regan117','607','23.1','18227','PA','1 ','0');
insert into loan values ('3500.00','2007-06-03','manx203','0','0','15127','PA','1 ','0');
insert into loan values ('5000.00','2007-06-03','eswist','419','3.48','77327','TX','1 ','0');
insert into loan values ('11000.00','2007-06-03','Consolidate Prosper','758','37.3','97227','OR','1 ','0');
insert into loan values ('1500.00','2007-06-03','matt12amazing','504','27.45','66027','KS','1 ','0');
insert into loan values ('1500.00','2007-06-04','dEs','711','58.8','30327','GA','1 ','0');
insert into loan values ('1000.00','2007-06-04','I m trying to repair my credit by paying off some old debts.','530','8.51','72227','AR','1 ','0');
insert into loan values ('7000.00','2007-06-04','jd2427','473','23.96','30027','GA','1 ','0');
insert into loan values ('25000.00','2007-06-04','Annieb6283','606','4.9','19127','PA','1 ','0');
insert into loan values ('10000.00','2007-06-04','LenaPresley','502','0','28227','NC','1 ','0');
insert into loan values ('4000.00','2007-06-04','bhissam','497','9.87','26127','WV','1 ','0');
insert into loan values ('2500.00','2007-06-04','Jonelles Study Abroad' ,'709','10','11227','NY','1 ','0');
insert into loan values ('3000.00','2007-06-04','belle54304','412','20.01','54327','WI','1 ','0');
insert into loan values ('1000.00','2007-06-04','KB143','506','13.01','37227','TN','3 ','0');
insert into loan values ('7500.00','2007-06-04','fsallustio','472','14.36','02127','MA','7 ','0');
insert into loan values ('2000.00','2007-06-04','mlishuslymotvatd','507','0','13727','NY','1 ','0');
insert into loan values ('1000.00','2007-06-04','Gballgamer','567','16.58','80527','CO','1 ','0');
insert into loan values ('1100.00','2007-06-04','MATTHEW','551','0.72','33427','FL','4 ','0');
insert into loan values ('3000.00','2007-06-04','saxman6160','0','4.05','80027','CO','1 ','0');
insert into loan values ('1000.00','2007-06-05','queenlurline','0','0.86','84627','UT','1 ','0');
insert into loan values ('7000.00','2007-06-05','chrisw1025','607','39.79','27827','NC','2 ','0');
insert into loan values ('1000.00','2007-06-05','ErikBell','0','6.55','80027','CO','4 ','0');
insert into loan values ('5000.00','2007-06-05','baileykearns','552','1.6','45027','OH','2 ','0');
insert into loan values ('1300.00','2007-06-05','kimbbarr','643','22.5','08527','NJ','2 ','0');
insert into loan values ('500.00','2007-06-05','Kiana_Noelle','547','18.52','20727','MD','4 ','0');
insert into loan values ('1000.00','2007-06-05','Raistlan','647','7.21','98027','WA','7 ','0');
insert into loan values ('1000.00','2007-06-05','chynadoll3586','531','493.6','48027','AL','1 ','0');
insert into loan values ('3000.00','2007-06-06','Keith24601','612','6.72','08127','NJ','1 ','0');
insert into loan values ('2000.00','2007-06-06','nicole38','656','5.51','21227','MD','1 ','0');
insert into loan values ('5000.00','2007-06-06','Butterfly','528','18.4','23627','VA','2 ','0');
insert into loan values ('2000.00','2007-06-06','speedy079','558','10.85','08527','NJ','1 ','0');
insert into loan values ('10000.00','2007-06-06','demarishargett','461','14.76','28327','NC','3 ','0');
insert into loan values ('6700.00','2007-06-06','LoveLearn','522','4.17','30227','GA','2 ','0');
insert into loan values ('20000.00','2007-06-06','Back To School','690','0.4','07927','NJ','1 ','0');
insert into loan values ('3400.00','2007-06-06','scooterodell','559','24.36','14527','NY','9 ','0');
insert into loan values ('5000.00','2007-06-06','crazymacguy','633','13.03','53927','WI','1 ','0');
insert into loan values ('8000.00','2007-06-06','TheresaF','481','12.21','44127','OH','1 ','0');
insert into loan values ('6000.00','2007-06-07','tjudith','679','26.07','23227','VA','1 ','0');
insert into loan values ('5000.00','2007-06-08','1OTH-MOUNTAIN-MAN','494','5.86','13627','NY','1 ','0');
insert into loan values ('9500.00','2007-06-08','moneywise','582','1.47','99827','AK','1 ','0');
insert into loan values ('3500.00','2007-06-08','tmsworld','583','3.12','10027','NY','1 ','0');
insert into loan values ('1500.00','2007-06-08','Sapphieangel008','468','3.98','45227','OH','1 ','0');
insert into loan values ('10000.00','2007-06-08','FSUgirl','565','6.48','32327','FL','1 ','0');
insert into loan values ('10000.00','2007-06-08','GRNGLD08','576','46.15','23327','VA','1 ','0');
insert into loan values ('800.00','2007-06-08','mozizzle86','696','24','11227','NY','1 ','0');
insert into loan values ('10000.00','2007-06-08','brianmikalw','559','0','13627','NY','1 ','0');
insert into loan values ('5000.00','2007-06-08','thephotoguy.seth','545','10.18','33727','TN','1 ','0');
insert into loan values ('6000.00','2007-06-08','Chase','602','2.36','35427','AL','1 ','0');
insert into loan values ('1000.00','2007-06-08','sgeorges86','626','3','29227','SC','1 ','0');
insert into loan values ('1000.00','2007-06-08','meep737','640','5.51','43027','OH','1 ','0');
insert into loan values ('15000.00','2007-06-09','laurel','528','34.27','13627','NY','1 ','0');
insert into loan values ('2500.00','2007-06-09','kyrstyn','509','14.16','17127','PA','1 ','0');
insert into loan values ('16000.00','2007-06-09','Eagle8XC Tuition loan','667','22.98','05627','VT','1 ','0');
insert into loan values ('2000.00','2007-06-09','Emmyboo0569','607','6.91','23827','VA','1 ','0');
insert into loan values ('4000.00','2007-06-09','jweme','609','14.5','32827','FL','1 ','0');
insert into loan values ('2000.00','2007-06-09','Bizounce','450','14.34','55427','MN','1 ','0');
insert into loan values ('3000.00','2007-06-09','lmhsfan','','9.26','80527','CO','1 ','0');
insert into loan values ('6000.00','2007-06-09','Chuck Ave','492','12','64127','MO','1 ','0');
insert into loan values ('2500.00','2007-06-10','RebelGrad07','549','21.94','38827','MS','7 ','0');
insert into loan values ('3000.00','2007-06-10','mtalty','481','60.11','46227','IN','3 ','0');
insert into loan values ('6000.00','2007-06-10','brint','716','4042.11','75227','TX','1 ','0');
insert into loan values ('17000.00','2007-06-10','Consolidating credit cards','653','5.96','21227','MD','4 ','0');
insert into loan values ('5000.00','2007-06-10','jedurs','621','2.6','23127','VA','1 ','0');
insert into loan values ('7500.00','2007-06-10','Charles','629','45.95','32227','FL','6 ','0');
insert into loan values ('15000.00','2007-06-11','vervec','615','15','02427','MA','8 ','0');
insert into loan values ('4000.00','2007-06-11','Debt Consolidation','608','0','55827','MN','1 ','0');
insert into loan values ('4000.00','2007-06-11','Nickolae3','628','6.36','40327','KY','1 ','0');
insert into loan values ('1000.00','2007-06-11','Alison','585','24','80327','CO','1 ','0');
insert into loan values ('1500.00','2007-06-11','jaja9487','493','6.4','45727','OH','1 ','0');
insert into loan values ('6500.00','2007-06-11','Sammy','512','1.84','02127','MA','9 ','0');
insert into loan values ('5000.00','2007-06-11','Mattd','598','24.7','30027','GA','1 ','0');
insert into loan values ('4000.00','2007-06-11','mattwalker','490','36.92','23527','VA','4 ','0');
insert into loan values ('1500.00','2007-06-11','nfields1','588','9.23','39527','MS','1 ','0');

select*from user_segments where segment_name='LOAN';

