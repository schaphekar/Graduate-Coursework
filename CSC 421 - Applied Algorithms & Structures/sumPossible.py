### Given a sorted list, these functions determine if there are elements whose sum adds up to a target value.

import time

# Array initialization
arr1 = [5,2,3,1,4,9,101,333,1,76,555]
arr1 = sorted(arr1)

t1 = 77
t2 = 338

# Function to check if it's possible to get t from two distinct elements
def sumPossible2(arr,target):
    
    i = 0
    j = len(arr)-1
    
    while i < j:
        
        if arr[i] + arr[j] == target:
            return arr[i], arr[j]
        
        if arr[i] + arr[j] < target:
            i += 1
        
        else:
            j -= 1
        
    return -1

# Function to check if it's possible to get t from three distinct elements
def sumPossible3(arr,target):
    
    i = 0
    j = 1
    k = len(arr)-1
    
    while i < k:
       
        while j < k:
            
            if arr[i] + arr[j] + arr[k] == target:
                return arr[i], arr[j], arr[k]
            
            if arr[i] + arr[j] + arr[k] < target:
                j += 1
                
            else:
                k -= 1
        
        i += 1
        
    return -1

# Test results
start_time = time.time();
print(sumPossible2(arr1,t1))
print("--- %s seconds ---" % (time.time() - start_time))

start_time = time.time()
print(sumPossible3(arr1,t2))
print("--- %s seconds ---" % (time.time() - start_time))
