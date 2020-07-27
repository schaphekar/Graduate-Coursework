### Bubble Sort is the simplest sorting algorithm that works by repeatedly swapping the adjacent elements if they are in wrong order.

### Time Complexity: O(n) best-case (already sorted), O(n²) worst-case

import time

arr1 = [5,2,3,1,4,9,101,333,1,76,555];

def bubbleSort(arr): 
    n = len(arr) 
  
    # Traverse through all array elements 
    for i in range(n-1): 
  
        # Last i elements are already in place 
        for j in range(0, n-i-1): 
  
            # Traverse the array from 0 to n-i-1 
            # Swap if the element found is greater than the next element 
            if arr[j] > arr[j+1]: 
                arr[j], arr[j+1] = arr[j+1], arr[j] 

# Test results
start_time = time.time();
bubbleSort(arr1)
print("--- %s seconds ---" % (time.time() - start_time))
print(arr1);
