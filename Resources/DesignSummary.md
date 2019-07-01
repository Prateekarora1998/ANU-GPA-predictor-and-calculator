   
#### Project Idea
- Calculate all possible permutations of HDs,Ds,CRs,Ps needed for acquiring a specified  overall GPA.
- Inbuilt GPA calc

#### Existing Implementation
- Using ~~backtracking permutation algorithm OR heap's algorithm~~ to obtain the possible permutation.
- Not gonna use those algos as the worst case complexity is O(N!)
- going to use **"Integer partitioning"**.
- Building the GPA calc is going to be straightforward:the calc will follow the ANU GPA system.
  [ANU GPA system](http://www.anu.edu.au/students/program-administration/assessments-exams/grade-point-average-gpa)


#### Extensibilty 
- One can use the [existing Database code](https://gitlab.cecs.anu.edu.au/u6555407/assignapp2019s1/commit/ae6c47c995d985f82725ac23f1bf40f2bba3540d)(By Prateek) to include the storage facility.
- One can apply ScaleEffect on any View Objects with ease using ScaleEffect Class.
- GPA  & permutation related classes can be altered to facilitate other Grading systems by changing the calculation formulas accordingly.
- Fingerprint recognisation can be added easily, by making amendments to Password related classes.


#### Disclamer: 
The calculations done within the app doesn't guarantee the expected output in some cases due to precision issue.However,the app tries to provide the closest outputs.

