#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Mar 8 18:21:19 2019

@author: Siddharth
"""
### This is a 2nd order Markov model that generates novel baby names
### Novel names are derived from existing popular names using probabilities derived from 1000 word dataset each for male/female names

#######################################################################################################

### Importing necessary modules
import numpy as np

### Import dataset
fo1 = open("namesBoys.txt")
fo2 = open("namesGirls.txt")

### Return lists containing the lines
boys = fo1.readlines()
girls = fo2.readlines()

### Trimming leading and trailing whitespace
boys = [s.strip() for s in boys]
girls = [s.strip() for s in girls]

### Some stats about the dataset
print('Length of shortest male name:   ', min(len(x) for x in boys))
print('Length of longest male name:    ', max(len(x) for x in boys))
print('Length of shortest female name: ', min(len(x) for x in girls))
print('Length of longest female name:  ', max(len(x) for x in girls))
print()

### Adding underscores to build dictionaries based on previous 2 letters
boys = ['__' + s + '_' for s in boys]
girls = ['__' + s + '_' for s in girls]

### User input for word constraints 
maxlength = 11
minlength = 2
gender = girls
numnames = 5

#######################################################################################################

def buildDictionaries(gender):
    ### Initialize empty dictionaries
    seq = dict()
    seq2 = dict()
        
    ### The nested loop builds out the dictionary using sequences
    for name in gender:
        for letter in range(len(name)):
            if name[letter-1:letter] not in seq:
                seq[name[letter-1:letter]] = {name[letter]:1}
            else:
                if name[letter] not in seq[name[letter-1:letter]]:
                    seq[name[letter-1:letter]][name[letter]] = 0
                else:
                    seq[name[letter-1:letter]][name[letter]] += 1 
    
    ### Then the probability structure is built according to occurrences                    
    for sequence, freq in seq.items():
    
        ### Initialize structures for probability dictionary
        seq2[sequence] = []
        aster = freq.values()
        
        ### Key-value pairs
        seq2[sequence].append(list(freq.keys()))
        
        ### Summing the total instances
        frequency = 0
        for count in aster:
            frequency += count
        
        ### Probabilities of the respective sequences
        for x in aster:
            sequenceprob = x/frequency
            seq2[sequence].append(sequenceprob)
    
    return seq2

#######################################################################################################

def addLetter(sequence, prob):
    return np.random.choice(prob[sequence][0], 1, prob[sequence][1])[0]

#######################################################################################################

def buildWord(gender):
        
    ### Initialize strings for additional letters
    newname = '__'
    nextletter = ''
    
    ### Build probability objects    
    prob = buildDictionaries(gender)
    
    ### First letter
    letter1 = addLetter('__', prob)
    
    ### Append new letter to word being built
    newname += letter1
    
    ### Loop that checks for end of word depending on letter added
    while nextletter != '_':
        
        nextletter = addLetter(newname[-2:], prob)
        newname = newname + nextletter
    
    return newname
            
####################################################################################################### 
    
def addWord(gender,minlength,maxlength,numnames):
    
    ### Initialize empty lists, one each for valid and invalid names
    novelnames = []
    toolong = []
    redundant = []
    totalnames = 0
    
    ### Initialize loop counter
    i = 0
    
    ### Create modified dataset to compare for novel names
    gender2 = [k.replace("_", "") for k in gender]
    
    while i < numnames:
        
        ### Call word generator function and modify output
        add = buildWord(gender) 
        add = add.replace("_","")
        totalnames += 1
        
        ### Eliminate names that are redundant or are too long
        if len(add) <= maxlength and add not in gender2:
            novelnames.append(add)
            print(add,len(add))
            i += 1
            
        elif len(add) <= maxlength and add in gender2:
            redundant.append(add)
            print(add,len(add))
        
        ### For validation purposes check output of names with excess letters
        else:
            print(add,len(add))
            toolong.append(add)
    
    ### Final names   
    print()
    print('***** Total names generated: ', totalnames) 
    print()    
    print('***** Novel names: ' , novelnames,len(novelnames))
    print()
    print('***** Novel names exceeding max length: ' , toolong,len(toolong))
    print()
    print('***** Redundant names: ', redundant, len(redundant))
    
######################################################################################################   

### Build list of novel names!
addWord(gender,minlength,maxlength,numnames)

###############################################################################
