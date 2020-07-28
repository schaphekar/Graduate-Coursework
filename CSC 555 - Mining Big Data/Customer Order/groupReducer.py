import sys

revs_dict = dict()

for line in sys.stdin:
	line = line.strip()
	region, revenue = line.split('\t',1)

	revenue = int(revenue)

	print(region, revenue)

	if region in revs_dict:
		revs_dict[region].append(revenue)

	else:
		revs_dict[region] = [revenue]

print(revs_dict)

for k, v in revs_dict.items():
	print('%s\t\t\t%s' % (k, sum(v)))

# Run with:
# hadoop jar /home/ec2-user/hadoop-2.6.4/share/hadoop/tools/lib/hadoop-streaming-2.6.4.jar -D mapred.reduce.tasks=3
# -D mapreduce.map.memory.mb=3072 -files=groupMapper.py,groupReducer.py
# -input /data/output25 -output /data/output35 -mapper groupMapper.py -reducer groupReducer.py