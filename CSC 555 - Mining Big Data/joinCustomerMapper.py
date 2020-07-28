import sys

for line in sys.stdin:

	line = line.strip()
	split = line.split('|')

	if len(split) == 9:
		print(split[0] + '\t' + '|'.join(split[1:]), "Customer")
	
	else:
		print(split[2], '\t', '|'.join(split[:2], '|'.join(split[3:]))
