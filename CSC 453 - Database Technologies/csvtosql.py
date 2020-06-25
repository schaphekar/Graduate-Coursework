# Quick-and-dirty script to transform CSV data to SQL-ready insert statements
import csv

f = open('loan.csv')

csv_f = csv.reader(f)

for row in csv_f:
	print("insert into loan values (" + "'" + row[0] + "'" + ',' + "'" + row[1] + "'" + ',' +
	 "'" + row[2] + "'" + ',' + "'" + row[3] + "'" + ',' + "'" + row[4] + "'" + ',' + 
	 "'" + row[5] + "'" + ',' + "'" + row[6] + "'" + ',' + "'" + row[7] + "'" + ',' + 
	 "'" + row[8] + "'" + ');')

