# AssignApp2019s1
# App - ANU GPA

## Team structure and roles 
+ Jared u5953599 - Team member 
   + Update functionality(codes based on Kalai's code from GPACalc)
   + Password functionality (UI had some problems, password_set activity UI done by Prateek)
   + Instrumented testing(the codes were generated automatically, and then code repetition  was minimised  after that)
   + Logic: GPA class  
   + GPA test 
+ Prateek u6742441 - Team member
   + Skeleton/Base UI of the whole app and linking of the screens. 
   + [Database SQL part for storage functionality.](https://gitlab.cecs.anu.edu.au/u6555407/assignapp2019s1/commit/ae6c47c995d985f82725ac23f1bf40f2bba3540d)
   + Searched for appropriate APIs for Animations and implemented it for most of the activities.
   + App Icon 
   + Feedback & about Activity UI.
   + Loading Screen 
   + Setting Activity UI:
     Changed from a Slider based activity to button based one. So to make the app UI consistent.
   + Manual
   + App Summary
   + Corrected Jared's UI for password
+ Kalai u6555407 - Team member
   + Linked Prateek's base UI code to logic part of the app
   + Logic: Permutation class
   + Logic: PermutationGenerator class
   + ScaleEffect on View objects  & other minor animation
   + Client-Server functionality completed with some help of his friend(referenced him on README)
   + PermutationResult 
   + Feature to activate and deactivate scale effect animation 
   + UI Changes to permutation activity and  changes to the naming convention of files and other Resources.
   + Permutation test.
   + Storage functionality UI,but failed to link it up due to time constraint & workloads. [Proof;The adjacent commits can be loooked upon as well.](https://gitlab.cecs.anu.edu.au/u6555407/assignapp2019s1/commit/e47c4793dd80cba563410a92ff906df163b1908a)

## App Overview 

_Our user-friendly app is for the ANU students who can not only calculate his/her GPA but can obtain all possible permutations of Grades so to achieve a target GPA. The app's motive is_
_to make student's life easier by calculating the GPA and can help him/her getting his/her desired GPA fulfilled.Furthermore,for privacy purposes, he or she can lock his/her app using password button._ <br>

<img src="../Resources/AppGif.gif" alt="app gif" align="middle"> 


## Design Documentation 
+ [Design Summary](../Resources/DesignSummary.md)
+ [Testing Summary](../Resources/TestingSummary.md)

## Minuted Meetings
+ [Meeting 1 - 8/4/19 - Deciding on the topic of app & Dividing tasks](../Resources/Meeting1.md)
+ [Meeting 2 - 12/4/19 - Logic & Base/Template of the App](../Resources/Meeting2.md)
+ [Meeting 3 - 14/4/19 - Linking up Logic of the App](../Resources/Meeting3.md)
+ [Meeting 4 - 15/4/19 - Graphical Display & client-server problem](../Resources/Meeting4.md)
+ [Meeting 5 - 16/4/19 - Continous Integration tool and UI design](../Resources/Meeting5.md)
+ [Meeting 6 - 17/4/19 - Considering the extensibility of the App](../Resources/Meeting6.md)
+ [Meeting 7 - 18/4/19 - Client-server issue](../Resources/Meeting7.md)
+ [Meeting 8 - 19/4/19 - Major Revamps ,Client-server issue & Authorships](../Resources/Meeting8.md)
+ [Meeting 9 - 20/4/19 - Stablised Working App](../Resources/Meeting9.md)
+ [Meeting 10 - 24/4/19 - Bugs Found & Further Implementation](../Resources/Meeting10.md)
+ [Meeting 11 - 29/4/19 - Bugs regarding the apps fixed and new idea for update](../Resources/Meeting11.md)
+ [Meeting 12 - 8/5/19 - Bugs regarding the apps fixed and new idea for update (2)](../Resources/Meeting12.md)


## Statement of Originality

I _Jared Graf_ declare that everything I have submitted in this
assignment is entirely my own work, with exceptions given below.
+ [The Animations are taken from Lottie android animation](https://lottiefiles.com/)
+ [The code for animation is taken from Lottie airbnb](https://airbnb.io/lottie/#/android)
+ [learned how to implement dialogs from here](https://developer.android.com/guide/topics/ui/dialogs.html)
+ some of the integration test was created by esspresso. it is specified which parts are mine and which arent

I _Prateek Arora_ declare that everything I have submitted in this
assignment is entirely my own work, with exceptions given below.
+ [The Animations are taken from Lottie android animation](https://lottiefiles.com/)
+ [The code for animation is taken from Lottie airbnb](https://airbnb.io/lottie/#/android)

I _Kalaiarasan Somasundaram_ declare that everything I have submitted in this
assignment is entirely my own work, with exceptions given below.
+ [Ideology of converting recursive function to iterative one](https://stackoverflow.com/questions/159590/way-to-go-from-recursion-to-iteration)
+ [Idea of using Integer Partitioning](https://stackoverflow.com/questions/7331093/getting-all-possible-sums-that-add-up-to-a-given-number)
+ [PermutationCalc Knowing Screen Size](https://stackoverflow.com/questions/4743116/get-screen-width-and-height-in-android)
+ The suggestion given by Danny Feng (u6611178) to solve Client-Server Issue.


### Referencing code or ideas for code from other sources
+ [Ideology of converting recursive function to iterative one](https://stackoverflow.com/questions/159590/way-to-go-from-recursion-to-iteration) 
+ [Idea of using Integer Partitioning](https://stackoverflow.com/questions/7331093/getting-all-possible-sums-that-add-up-to-a-given-number)
+ (IDEA FOR CODE)Server - Client problem solved by the suggestion given by Danny Feng(u6611178);Suggestion: <br>
  1. Use a seperate thread for performing long operations such as network access
     to prevent it blocking UI/Main thread.
  2. Give permission for Network access in the manifest. <br>
+ [Code in PermutationCalc for knowing Screen Size](https://stackoverflow.com/questions/4743116/get-screen-width-and-height-in-android)


### Assets 
_[Converted the video to GIF(On the README)](https://ezgif.com/video-to-gif)
_Lottie animations provided by airbnb is being used this app which is implemented by Prateek(Reference for lottie code and json is in the app code)._

