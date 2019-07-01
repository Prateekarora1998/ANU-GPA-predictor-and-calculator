### TestingSummary

#### Permutation Class Test
##### Purpose:
- To make sure permutation method of Permutation Class works as expected.

##### Test Cases  & Testing Methodology Overview:
- Test cases contains a base case & cases of two other random set of inputs.
- A brute force method of finding possible permutations is used to produce the expected output.
- Test is run automatically before program, to alert developers to issues faster

##### Testing Scope:
- Just for the core method of Permutation class : calculatePermutation().

##### Items not tested:
- The breaking point of the method,For the case in which input values are extremely unreasonable;which wont be possible.Negative values since the input of the user can be control via android layout configurations.


#### GPA Class Test
##### Purpose:
- To ensure the functions of the GPA class return the correct data

##### Test Cases  & Testing Methodology Overview:
- Contains 3 tests: a base case(input of 0s only), a simple case(mix of small ints) and a more complex random case(random mix of ints between 0 and 5, inclusive)
- Test is run automatically before program, to alert developers to issues faster

##### Testing Scope:
- Tests only the GPA class and its component methods

##### Items not tested:
- extremely large inputs


#### Main Activity Test
##### Purpose:
- to perform a basic check of the gui and the data saved

##### Test Cases  & Testing Methodology Overview:
- Contains 3 tests: a base case(all inputs 0), a basic check(all inputs 1) and a random test(all inputs a random int between 1 and 3, inclusive)
- Test is run automatically before program, to alert developers to issues faster

##### Testing Scope:
- The MainActivity, Home, GPACalc, Settings and Update activities
- The sharedPreference files created by some of them and the data therein

##### Items not tested:
- Unlisted activities
