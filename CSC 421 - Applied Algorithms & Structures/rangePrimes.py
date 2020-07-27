### Find all the prime numbers in a given range (inclusive) and yield the total count.

import sys

for line in sys.stdin:

	# Standard comma-separated user input. Only takes first two arguments.
	bounds = [int(x) for x in line.split(",")]
	lower = bounds[0]
	upper = bounds[1]

	prime = 0

	# Nested loop to test primality for each number. Adding 1 to upper for inclusivity of range bounds.
	for i in range(lower, upper+1):
		for j in range(2,i):
			if (i % j) == 0:
				break

		else:
			print(i)
			prime += 1

	print("# of prime numbers between", lower, "and", upper, "->", prime)
	sys.exit()

