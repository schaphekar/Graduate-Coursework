# Importing necessary modules
import time

# Sample Array/Search Value Initialization
arr1 = [5,3,1,4,9,101,333,1,76,2,555];
v = 76

# Function definition for linear search
def linearSearch(arr, v):
    for i in range(len(arr)):
        if arr[i] == v:
            print("Value", v, "found at index", i);
            break;
    return -1
        
# Test results
start_time = time.time();
linearSearch(arr1, v);
print("--- %s seconds ---" % (time.time() - start_time))
