### Recursive algorithm to find the closest pair of points in a given set of points.

### Importing necessary modules
import math
import time
    
### Importing set of points from .txt file
with open("1000points.txt") as pointsFile:
    lines = [line.split() for line in pointsFile]

### Convert all elements in lines from str to int using list comprehension
points = [list(map(int, i)) for i in lines]

### Define function for calculating Euclidean distance
def dist(p1, p2):
    return math.sqrt((p1[0] - p2[0])**2 + (p1[1] - p2[1])**2)

### Define function for brute-force calculation of closest pair
def closestPairBrute(points):
    
    # Initialize tracking variable for # of points
    size = len(points)
    
    # Initialize distance between first pair, then update as necessary
    minimum = dist(points[0], points[1])
    
    # Nested loop to compare every possible pair of points' distances
    for i in range(size - 1):
        
        for j in range(i + 1, size):
            current = dist(points[i], points[j])
            
            # If we found a new minimum, then update from current
            if current < minimum:
                minimum = current
                
    return minimum

### Define function for recursive computation of closest pair
def closestPairRecursive(points):
    
    # Initialize a variable for array length to avoid excessive recomputing
    size = len(points)
    
    # Step 1: Define base case, where we can just use brute-force
    # Ensures that subproblem of just one point is not solved
    if size <= 3:
        return closestPairBrute(points)
    
    # Step 2: Define lists for x,y monotonically sorted versions of list
    sorted_x = sorted(points, key = lambda x: x[0])
    sorted_y = sorted(points, key = lambda x: x[1])
    
    # Step 3: Divide the point set into L/R halves and call recursively
    center = size // 2
    p_left = closestPairRecursive(sorted_x[:center])
    p_right = closestPairRecursive(sorted_y[center:])
    
    # Step 4: Assign closest to the smaller of the two minimums
    if p_left < p_right:
        closest = p_left
    else:
        closest = p_right

    # Nested loop to find closest pair in the vertical strip
    # Only the 7 points that follow p need to be considered
        
    minimum = dist(points[0], points[1])
    lim = 7
    
    for i in range(size):
        
        for j in range(max(0, i - lim), i):
            current = dist(points[i], points[j])
            
            if current < minimum:
                minimum = current
    
    # Finally, return the minimum between the two pairs
    if closest < minimum:
        return closest
    else:
        return minimum

### Test brute force and recursive functions run times
start_time = time.time()
print("Minimum distance: ", closestPairBrute(points))
print("Brute-force approach takes " + "%s seconds" % (time.time() - start_time))

print()

start_time = time.time()
print("Minimum distance: ", closestPairRecursive(points))
print("Divide-and-conquer approach takes " + "%s seconds" % (time.time() - start_time))

