### The mergesort algorithm sorts an array by dividing the unsorted list into n sublists
### Each contains one element - a list of one element is considered sorted
### Then repeatedly merge sublists to produce new sorted sublists 
### Continue until there is only one, sorted sublist remaining.

### "Stable" sort, so elements maintain original positions wrt to each other.

### Time Complexity: O(n log n)

import time

arr1 = [5,2,3,1,4,9,101,333,1,76,555];

def mergeSort(arr):
    
    if len(arr) > 1:
        
        # Split the original list into halves
        mid = len(arr) // 2
        left = arr[:mid]
        right = arr[mid:]

        # Recursive call on each half
        mergeSort(left)
        mergeSort(right)

        # Two iterators for traversing the two halves
        i = 0
        j = 0
        
        # Iterator for the main list
        k = 0
        
        while i < len(left) and j < len(right):
           
            if left[i] < right[j]:
              
              # The value from the left half has been used
              arr[k] = left[i]
              
              # Move the iterator forward
              i += 1
              
            else:
                arr[k] = right[j]
                j += 1
            
            # Move to the next slot
            k += 1

        # For all the remaining values
        while i < len(left):
           
            arr[k] = left[i]
            i += 1
            k += 1

        while j < len(right):
            
            arr[k]=right[j]
            j += 1
            k += 1
            
### Test results
start_time = time.time();
mergeSort(arr1)
print("--- %.10f seconds ---" % (time.time() - start_time))
print(arr1);
