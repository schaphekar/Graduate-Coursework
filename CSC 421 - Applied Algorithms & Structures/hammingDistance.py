### The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
### This program takes as input two integers and returns their Hamming distance.

# Integers to compare
print("Input two integers: ")
x, y = map(int, input().split())

# Convert to binary, then to string
x = str(bin(x))
y = str(bin(y))

# Create lists from each element in the strings
xbin = list(map(str, x))
ybin = list(map(str, y))

# Remove unnecessary characters
for i in xbin:
	if i == 'b':
		xbin.pop(1)

for i in ybin:
	if i == 'b':
		ybin.pop(1)

# Insert zeros to equalize the lengths of the list
if len(xbin) < len(ybin):
	while len(xbin) < len(ybin):
		xbin.insert(0,'0')

if len(xbin) > len(ybin):
	while len(xbin) > len(ybin):
		ybin.insert(0,'0')

# Hamming distance counter
h = 0
hamming = 0

while h < len(xbin):
	if xbin[h] != ybin[h]:
		hamming += 1
	h += 1

print("The Hamming distance between the two integers is: ", hamming)
