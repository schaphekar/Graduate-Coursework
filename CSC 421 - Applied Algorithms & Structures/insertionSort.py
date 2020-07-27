### Insertion sort works left to right, comparing each element to elements to its left.
### The element is then inserted into the correct position in the array.

### Time Complexity: O(n²), efficient for datasets that are already somewhat sorted.

import time

arr1 = [5,2,3,1,4,9,101,333,1,76,555];
arr2 = [5,2,3,1,4,9,101,333,1,76,555];

# Algorithm Implementation
def insertionSort(arr):
    
    for i in range(1, len(arr)): 
        key = arr[i] 
        j = i-1
        
        while j >=0 and key < arr[j] : 
                arr[j+1] = arr[j] 
                j -= 1
        arr[j+1] = key 

# Recursive Implementation
def insertionSortRecursive(arr, n): 
    
    # Base case
    if n<=1: 
        return 0
      
    # Sort first n-1 elements 
    insertionSortRecursive(arr, n-1) 
    last = arr[n-1] 
    j = n-2
      
    while (j>=0 and arr[j]>last): 
        arr[j+1] = arr[j] 
        j = j-1
  
    arr[j+1]=last 
      
# Test results
start_time = time.time();
insertionSort(arr1)
print(arr1)
print("--- %s seconds ---" % (time.time() - start_time))

start_time = time.time();
insertionSortRecursive(arr2, 11)
print(arr2)
print("--- %s seconds ---" % (time.time() - start_time))

