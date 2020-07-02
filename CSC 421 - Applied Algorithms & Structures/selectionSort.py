### The selection sort algorithm sorts an array by repeatedly finding the minimum element from the unsorted part and putting it at the beginning. 
### As a result, the algorithm maintains two subarrays for a given array.

### Time Complexity: O(n²), very inefficient for large lists

# Importing necessary modules
import time

# Start time
start_time = time.time();

# Sample Array Initialization
arr1 = [5,2,3,1,4,9,101,333,1,76,555];

# Selection Sort Implementation
def selectionSort(arr):
    for i in range(len(arr)):
        min_index = i;
        
        for j in range(i+1, len(arr)): 
            if arr[min_index] > arr[j]: 
                min_index = j; 
        arr[i], arr[min_index] = arr[min_index], arr[i]; 
        
# Test results
selectionSort(arr1);
print("--- %s seconds ---" % (time.time() - start_time))
print(arr1);
