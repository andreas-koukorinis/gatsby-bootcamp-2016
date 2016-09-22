"""
Created on Thu Sep 22 14:25:39 2016

@author: jmagraner
"""

import numpy as np
import pdb
import numpy.random as rnd
import matplotlib.pyplot as plt

########## FUNCTION TO BE COMPUTED: LIKELIHOOD OF THE DATA ####################

# Compute likelihood of the data (either from generated data or from the data
# extracted from the videos)
# This is the function to be computed for different parameter settings
# A_test, B_test, C_test, D_test
# Pick a (small) range around the true parameters (ask the TAs!).

A_test=1.2
B_test=200
C_test=8.
D_test=200.

# Data is a set of coordinates that define the trajectory of a tank in time,
# with (d_t, a_t) giving the state of the tank at time t.
# In here d_t defines the distance to move forward at time t and a_t is
# the angle (orientation) of the tank, in radians.
#(d_t, a_t)
#data should be an np array of size (2, T)
data_augmented = np.concatenate([data[:,0:T-1], data[:,1:]], axis=0)

def loglikelihood(data, A_test, B_test, C_test, D_test):
    
	d_t = data[0,:]
	a_t = data[1,:]
	d_t1 = data[2,:]
	a_t1 = data[3,:]

	sigma2 = np.pi/10.

	min_t = C/(1+np.abs(a_t)) + D/(1+d_t) + 60
	max_t =  C/(1+np.abs(a_t)) + D/(1+d_t) + 110
	#pdb.set_trace()
	l = np.sum(-(0.5/sigma2)*np.power(a_t1 + A* a_t - np.pi*np.exp(-B/d_t),2) - 0.5 *np.log(2*np.pi*sigma2) + np.log((d_t1<max_t)*(d_t1>min_t)*1./(max_t-min_t)))

	return l

print loglikelihood(data_augmented, A_test, B_test, C_test, D_test)

###############################################################################