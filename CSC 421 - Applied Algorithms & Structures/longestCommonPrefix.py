# This program takes as input a list of strings as returns the longest common prefix among them as output.

# Sample inputs
words1 = ["flower", "flour", "flame"]
words2 = ["basket", "basking", "basketball"]
words3 = ["animal", "antagonist", "anonymous", "mercury"]

# Function definition
def longestCommonPrefix(arr):
	
	prefix = []

	arrlen = len(arr)

	if arrlen < 1:
		return None
	
	else:

		i = 1
		j = 0

		while i < arrlen: 
			
			# If the letter at index j of the word is the same as the first word and the i^th word, then increment i.
			if arr[0][j] == arr[i][j]:
				i += 1

			# If the letter at index j of the word is the same as the first word and it is also the last word, add the common letter.
			if i == (arrlen-1):
				prefix.append(arr[i][j])
				i = 1
				j += 1

			# If neither condition is satsified, force exit.
			else:
				i = arrlen

	return prefix

# Testing and sample outputs
print(longestCommonPrefix(words1))
# ['f', 'l', 'a']

print(longestCommonPrefix(words2))
# ['b', 'a', 's', 'k']

print(longestCommonPrefix(words3))
# []

