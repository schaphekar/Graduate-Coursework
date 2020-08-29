### This function takes a string as input and returns the first unique letter or character.

def first_unique(s):
	
	# Dictionary that tracks letter occurrences
	lettercount = {}

	# Iterate through string to add letters to dictionary
	for letter in s:
		if letter not in lettercount:
			lettercount[letter] = 1
		else:
			lettercount[letter] += 1

	for letter in lettercount:
		if lettercount[letter] == 1:
			return letter

	return None

# Sample output, prints 'c'
print(first_unique('ababca'))
 
# Sample output, prints '$'
print(first_unique('aaaaa$1#szs'))
