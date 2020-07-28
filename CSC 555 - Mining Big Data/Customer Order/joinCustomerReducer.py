import sys

currentKey = None
valsCust = None
valsLO = None

for line in sys.stdin:

	split = line.strip().split('\t')
	key = split[0]
	value = split[1]

	if currentKey == key:
		if value.endswith('Customer'):
			valsCust.append(value[:-9])
		else:
			valsLO.append(value)

	else:
		if currentKey:
			for cust in valsCust:
				for lo in valsLO:
					print(currentKey, '|', cust, lo)

		currentKey = key

		if value.endswith('Customer'):
			valsCust = [value[:-9]]
			valsLO = []

		else:
			valsCust = []
			valsLO = [value]

for cust in valsCust:
	for lo in valsLO:
		print(currentKey, '|', cust, '|', lo)

# Run with:
# hadoop jar /home/ec2-user/Hadoop 2.6.4/share/hadoop/tools/lib/hadoop-streaming-2.6.4.jar -D mapred.reduce.tasks=3
# -D mapreduce.map.memory.mb=3072 -files=joinMapper.py,joinReducer.py
# -input /data/tbljoin
# -output /data/output25
# -mapper joinMapper.py
# -reducer joinReducer.py(
