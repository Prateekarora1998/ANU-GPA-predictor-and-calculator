### Meeting 10 (24/4/19) : Bugs Found & Further Implementations.

##### Summary 
- Prateek decided to drop font size changer &  implement a way in which users can look at their History,
- Kalai wanted to change the Permutation result into a table so that users can comprehend it more easily.
- Prateek suggested that the names of the buttons in permutationCalc can be further changed to promote user-friendliness.
- Kalai decided to make Permutation to stop inheriting from GPA class.Since the reason for inheritance was that Permutation class needed to have access to calculateTotalPoints & GRADEPOINTS data.Jared & Prateek accepted Kalai's idea which was to use an Enum Grade class & transfer Jared's calculateTotalPoints to Permutation class. 
- Kalai pointed out a bug in permutationCalc animations.
- Jared's idea of updating values whenever users needs to was accepted by Prateek & Kalai.

#### Tasks
##### Jared
- CI tool.
- Use Enum Grade Class values in GPA class.
##### Prateek 
- Make changes to Settings so that the discussed implementation can be appended.
##### Kalai
- Fix the animation issue in PermutationCalc : when the user interacts while the buttons are animating,it causes changes to it's attributes and messes up it's position & scale value.
- Create Enum Class for Grades & use it's values appropriately in permutation class.
- Change Permuatation Button Names.
- Change the permutation results in a tabular form.

#### Tasks completed since the previous meeting

#### Future Goals
- Implement a notification such that after a period of time user can update his values.
- Update function under Settings.
- Get Design & Testing Documentation done.
- Link up GPA test to the app.
- Come up with a Icon for the app.