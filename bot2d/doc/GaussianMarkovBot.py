import numpy as np
import pdb
import numpy.random as rnd
import matplotlib.pyplot as plt


#true parameters (used to generate the movent of the tanks in the video)
# A=0.8
# B=180.
# C=8.
# D=200.


################ GENERATE DATA FROM TRUE MODEL ################################

# generate some data from the model, in case the guys could not extract it
# from the videos!!

# Data is a set of coordinates that define the trajectory of a tank in time,
# with (d_t, a_t) giving the state of the tank at time t.
# In here d_t defines the distance to move forward at time t and a_t is
# the angle (orientation) of the tank, in radians.

# Generate data: use true parameters
B=180.
C=8.
D=200.
# Pick length of trajectory
T = 300

def generate_data(A, B, C, D):

	#(d_t, a_t)
	sigma = np.sqrt(np.pi/10.)
	data = np.zeros((2, T))
	data[:,0] = rnd.rand(1)*50 + 60

	for t in range(1, T):
		d_t = data[0,t-1]
		a_t = data[1,t-1] 
		data[0,t] = C/(1+abs(a_t)) + D/(1+d_t) + 60 + rnd.rand(1)*50
		data[1,t] = -A*a_t + np.pi*np.exp(-B/d_t) + rnd.randn(1)*sigma


	return data

# (d_t, a_t, d_t+1, a_t+1)
data = generate_data(A=A, B=B, C=C, D=D)
print data

###############################################################################



########## FUNCTION TO BE COMPUTED: LIKELIHOOD OF THE DATA ####################

# Compute likelihood of the data (either from generated data or from the data
# extracted from the videos)
# This is the function to be computed for different parameter settings
# A_test, B_test, C_test, D_test
# Pick a (small) range around the true parameters.

A_test=A
B_test=B
C_test=C
D_test=D


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



