#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Feb 13 18:32:05 2019
@author: Siddharth Chaphekar

This program implements an agent that takes an initial state of the 8 puzzle and returns a solution path to the goal state.
The search strategy implemented is a simple breadth-first search (BFS). 

"""

### Importing necessary modules
import numpy as np
import time

### Class for node structure
class Node():
    def __init__(self,state,parent,action,depth,step_cost,path_cost,heuristic_cost):
        self.state = state 
        self.parent = parent 
        self.action = action 
        self.depth = depth 
        self.step_cost = step_cost
        self.path_cost = path_cost 
        self.heuristic_cost = heuristic_cost 
        
        self.move_up = None 
        self.move_left = None
        self.move_down = None
        self.move_right = None
    
    ### Check validity of up/down/right/left moves
    def try_move_down(self):
        
        zero_index=[i[0] for i in np.where(self.state==0)] 
        if zero_index[0] == 0:
            return False
        else:
            up_value = self.state[zero_index[0]-1,zero_index[1]] 
            new_state = self.state.copy()
            new_state[zero_index[0],zero_index[1]] = up_value
            new_state[zero_index[0]-1,zero_index[1]] = 0
            return new_state,up_value
        
    def try_move_right(self):
        zero_index=[i[0] for i in np.where(self.state==0)] 
        if zero_index[1] == 0:
            return False
        else:
            left_value = self.state[zero_index[0],zero_index[1]-1] 
            new_state = self.state.copy()
            new_state[zero_index[0],zero_index[1]] = left_value
            new_state[zero_index[0],zero_index[1]-1] = 0
            return new_state,left_value
     
    def try_move_up(self):
        zero_index=[i[0] for i in np.where(self.state==0)] 
        if zero_index[0] == 2:
            return False
        else:
            lower_value = self.state[zero_index[0]+1,zero_index[1]]
            new_state = self.state.copy()
            new_state[zero_index[0],zero_index[1]] = lower_value
            new_state[zero_index[0]+1,zero_index[1]] = 0
            return new_state,lower_value
        
    def try_move_left(self):
        zero_index=[i[0] for i in np.where(self.state==0)] 
        if zero_index[1] == 2:
            return False
        else:
            right_value = self.state[zero_index[0],zero_index[1]+1]
            new_state = self.state.copy()
            new_state[zero_index[0],zero_index[1]] = right_value
            new_state[zero_index[0],zero_index[1]+1] = 0
            return new_state,right_value
        
        
    def get_h_cost(self,new_state,goal_state,heuristic_function,path_cost,depth):
        if heuristic_function == 'num_misplaced':
            return self.h_misplaced_cost(new_state,goal_state)
        elif heuristic_function == 'manhattan':
            return self.h_manhattan_cost(new_state,goal_state)
       
        elif heuristic_function == 'fair_manhattan':
            return self.h_manhattan_cost(new_state,goal_state) - path_cost + depth
    
    ### Heuristic = # of misplaced tiles
    def h_misplaced_cost(self,new_state,goal_state):
        cost = np.sum(new_state != goal_state)-1 
        if cost > 0:
            return cost
        else:
            return 0 

    ### Heuristic = Manhattan distance
    def h_manhattan_cost(self,new_state,goal_state):
        current = new_state
        
        goal_position_dic = {1:(0,0),2:(0,1),3:(0,2),8:(1,0),0:(1,1),4:(1,2),7:(2,0),6:(2,1),5:(2,2)} 
        sum_manhattan = 0
        for i in range(3):
            for j in range(3):
                if current[i,j] != 0:
                    sum_manhattan += sum(abs(a-b) for a,b in zip((i,j), goal_position_dic[current[i,j]]))
        return sum_manhattan
    
    ### If goal state is reached, traverse back and print full path
    def print_path(self):
      
        state_trace = [self.state]
        action_trace = [self.action]
        depth_trace = [self.depth]
        step_cost_trace = [self.step_cost]
        path_cost_trace = [self.path_cost]
        heuristic_cost_trace = [self.heuristic_cost]
        
        while self.parent:
            self = self.parent

            state_trace.append(self.state)
            action_trace.append(self.action)
            depth_trace.append(self.depth)
            step_cost_trace.append(self.step_cost)
            path_cost_trace.append(self.path_cost)
            heuristic_cost_trace.append(self.heuristic_cost)

        step_counter = 0
        while state_trace:
            print('step',step_counter)
            print(state_trace.pop())
            print('action=',action_trace.pop(),', depth=',str(depth_trace.pop()),\
            ', step cost=',str(step_cost_trace.pop()),', total_cost=',\
            str(path_cost_trace.pop() + heuristic_cost_trace.pop()),'\n')
            
            step_counter += 1
            
    def breadth_first_search(self, goal_state):
        start = time.time()
        
        queue = [self] 
        queue_num_nodes_popped = 0 
        queue_max_length = 1 
        
        depth_queue = [0] 
        path_cost_queue = [0] 
        visited = set([]) 
        
        while queue:
            
            if len(queue) > queue_max_length:
                queue_max_length = len(queue)
                
            current_node = queue.pop(0) 
            queue_num_nodes_popped += 1 
            
            current_depth = depth_queue.pop(0) 
            current_path_cost = path_cost_queue.pop(0) 
            visited.add(tuple(current_node.state.reshape(1,9)[0])) 
            
            if np.array_equal(current_node.state,goal_state):
                current_node.print_path()
                
                print('Time performance:',str(queue_num_nodes_popped),'nodes popped off the queue.')
                print('Space performance:', str(queue_max_length),'maximum nodes in the queue at any given time.')
                print('Time spent: %0.2fs' % (time.time()-start))
                return True

            else:                
                
                if current_node.try_move_down():
                    new_state,up_value = current_node.try_move_down()
                    
                    if tuple(new_state.reshape(1,9)[0]) not in visited:
                      
                        current_node.move_down = Node(state=new_state,parent=current_node,action='down',depth=current_depth+1,\
                                              step_cost=up_value,path_cost=current_path_cost+up_value,heuristic_cost=0)
                        queue.append(current_node.move_down)
                        depth_queue.append(current_depth+1)
                        path_cost_queue.append(current_path_cost+up_value)
               
                if current_node.try_move_right():
                    new_state,left_value = current_node.try_move_right()
                    
                    if tuple(new_state.reshape(1,9)[0]) not in visited:
                        
                        current_node.move_right = Node(state=new_state,parent=current_node,action='right',depth=current_depth+1,\
                                              step_cost=left_value,path_cost=current_path_cost+left_value,heuristic_cost=0)
                        queue.append(current_node.move_right)
                        depth_queue.append(current_depth+1)
                        path_cost_queue.append(current_path_cost+left_value)
            
                if current_node.try_move_up():
                    new_state,lower_value = current_node.try_move_up()
                    
                    if tuple(new_state.reshape(1,9)[0]) not in visited:
                    
                        current_node.move_up = Node(state=new_state,parent=current_node,action='up',depth=current_depth+1,\
                                              step_cost=lower_value,path_cost=current_path_cost+lower_value,heuristic_cost=0)
                        queue.append(current_node.move_up)
                        depth_queue.append(current_depth+1)
                        path_cost_queue.append(current_path_cost+lower_value)

                if current_node.try_move_left():
                    new_state,right_value = current_node.try_move_left()
                    
                    if tuple(new_state.reshape(1,9)[0]) not in visited:
                        
                        current_node.move_left = Node(state=new_state,parent=current_node,action='left',depth=current_depth+1,\
                                              step_cost=right_value,path_cost=current_path_cost+right_value,heuristic_cost=0)
                        queue.append(current_node.move_left)
                        depth_queue.append(current_depth+1)
                        path_cost_queue.append(current_path_cost+right_value)
                        
### Choose from possible initial states
test = np.array([1,2,3,8,6,4,7,5,0]).reshape(3,3)
easy = np.array([1,3,4,8,6,2,7,0,5]).reshape(3,3)
medium = np.array([2,8,1,0,4,3,7,6,5]).reshape(3,3)
hard = np.array([5,6,7,4,0,8,3,2,1]).reshape(3,3)

### Initialize initial state and goal state
initial_state = test
goal_state = np.array([1,2,3,8,0,4,7,6,5]).reshape(3,3)
print(initial_state,'\n')
print(goal_state)        

# Run puzzle with BFS              
root_node = Node(state=initial_state,parent=None,action=None,depth=0,step_cost=0,path_cost=0,heuristic_cost=0)
root_node.breadth_first_search(goal_state) 
 
