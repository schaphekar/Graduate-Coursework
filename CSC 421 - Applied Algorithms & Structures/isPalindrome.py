### This program takes any type of input and checks to see whether that input is a palindrome, i.e identical when reversed.

# Take single-line user input
x = input()

def isPalindrome(x) -> bool:
        
        # Build list of string characters from input
        x = [x for x in str(x)]
        
        # New list with elements reversed
        xrev = x[::-1]
        
        # Check if new list matches old list
        i = 0

        while i < (len(x)):
            if x[i] == xrev[i]:
                i += 1
            else:
                return False
            
        return True

# Return boolean
print(isPalindrome(x))