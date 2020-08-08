### This program defines a function that takes in two arguments, a string and a list of integers.
### It returns the index of the closest matching letter with regard to the querying index.

# Sample string to be queried
s = 'lshclspplsl'

# Set of index queries
queries1 = [1,5,9]
queries2 = [0,4,8,10]

# Function definition
def closestMatch(s, queries):
    
    # Array to hold the final index for each query
    arr = []
    
    # For every query in the queries array, look at every letter in the string
    for i in queries:
        
        # For every letter in the string, check if it is the same as the query index letter
        close = [-1]
        for j in range(len(s)):
            
            # If the letter is the same and it's not the same index, append the index
            if s[i] == s[j] and i != j:
                close.append(j)
        
        # If there are indeed other matching letters, then drop the -1 element
        if len(close) > 1:
            close.pop(0)
        
        # Find the closest letter's index and append the value to output array
        absdiff = lambda list_value : abs(list_value - i)
        closestval = min(close, key=absdiff)
        arr.append(closestval)
    
    return arr

# Find closest s
print(closestMatch(s, queries1))     # Output: [5, 1, 5]

# Find closest l
print(closestMatch(s, queries2))     # Output: [4, 0, 10, 8]

