import sys

for line in sys.stdin:
	line = line.strip()
	split = line.split('|')

	if int(split[17]) <= 6 and int(split[17]) >= 4 and split[5] = 'AMERICA':
		print('%s\t%s' % (split[4], split[18]))
	