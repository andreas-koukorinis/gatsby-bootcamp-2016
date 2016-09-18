'''
######################################

SWC/Gatsby Boot Camp Python tutorial
uniitest module

######################################

    Why should we do testing?

1.  Make sure our code behave properly under different conditions 
    (5/2 vs 5.0/2, log(0), 1/0)

2.  Regression testing: use bugs found in the past to make sure the
    current version do no reproduce the same bug

3.  Before shipping/publishing the code, a prepared test suite will 
    let the user know if the package they downloaded behave the same 
    way you expected it to. 



    Basic usage:

1.  Define a class derived from unittest
2.  Fill the class with functions that start with test_
3.  You run the tests by placing unittest.main() in your file
    
'''



import unittest

# 1.  Define a class derived from unittest
class TestStringMethods(unittest.TestCase):

# 2.  Fill the class with functions that start with test_
    def test_upper(self):
        self.assertEqual('foo'.upper(), 'FOO')

    def test_isupper(self):
        self.assertTrue('FOO'.isupper())
        self.assertFalse('Foo'.isupper())

    def test_split(self):
        s = 'hello world'
        self.assertEqual(s.split(), ['hello', 'world'])
        # check that s.split fails when the separator is not a string
        with self.assertRaises(TypeError):
            s.split(2)
# 3.  You run the tests by placing unittest.main() in your file

if __name__ == '__main__':
    unittest.main()
