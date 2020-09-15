import sys

file = open("myfile.txt", "r+")
counter = {}

for word in file.read().split():
	 if word not in counter:
	 	counter[word] = 1
	 
	 else:
	 	counter[word] += 1

for i in counter.keys():
	print("%s %s " %(i, counter[i]))