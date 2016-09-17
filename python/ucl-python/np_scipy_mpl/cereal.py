import numpy as np
from pylab import plot, xlabel, ylabel, grid
# load the cereal data set

names = []
data = []
col_labels = ["calories (number)", "protein (g)", "fat (g)", "sodium (mg)", "dietary fiber (g)", "complex carbohydrates (g)", "sugars (g)", "display shelf (1, 2, or 3, counting from the floor)", "potassium (mg)", "vitamins and minerals (0, 25, or 100, respectively)", "weight (in ounces) of one serving (serving size)", "cups per serving"]

f = open("cereal.txt")
for l in f:
  fields = l.split()
  names.append(fields[0])
  data.append([float(x) for x in fields[3:]])

data = np.array(data)

# We now have extracted the following:
#    names      List containing the names of the cereals tested 
#    col_labels Labels of the attributes in the data set
#    data       numpy.ndarray containing the numeric values 
#               of the 12 attributes for the 77 cereals

# example plot: fat content vs. calories
fat = data[:,2]
calories = data[:,0]
plot(fat, calories ,'kx')
xlabel(col_labels[2])
ylabel(col_labels[0])
grid()

# fit a line through the points
coefficients = np.polyfit(fat, calories, 1)
poly = np.poly1d(coefficients)
plot(fat,poly(fat), 'r-')

