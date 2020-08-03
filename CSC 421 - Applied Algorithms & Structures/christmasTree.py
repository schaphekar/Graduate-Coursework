### This program prints the classic Christmas tree based on user input # of rows.

# Condensed version of function using string multipliers and centering
def christmasTreeCondensed(n):
    for i in range(n):
        print(("+" * (i * 2 + 1)).center(n * 2 - 1))

# Nested loop version
def christmasTreeNested(n):
    z = n-1
    x = 1
    
    for i in range(0,n):
        
        for i in range(0, z):
            print(' ', end = '')
            
        for i in range(0, x):
            print('+', end = '')
            
        for i in range(0, z):
            print(' ', end = '')
            
        x = x + 2
        z = z - 1
        print()
    
# Test functions
christmasTreeCondensed(8)
print()
christmasTreeNested(8)