#GENETIC AlGORITHM

###Notes:
_You can hard code the equation into the "WorkingGA.solveEquation" method to make it possible to solve other equation as well. 
I will try to make it easier to fix in the future._

##INTRODUCTION AND PROBLEM

The the Genetic Algorithm itself is pretty easy to understand and to implement the logic inside it. However, due to some programming problems, namely becaise there are too many object to be reference around, cause data to messed with each other which cause me more than half a day just to figure out the problem and to fully fix it.

For example, we have the a binary number X. X itself is reference by a Solution S, which can contains more than 1 Xs (2 in this case because x1 and x2). Solution S itself is referenced by the whole population which contains 19 other Ss. Now, this is not a problem, but when I tried to mix them up using crossover and mutation, this cause some value Xs and solution Ss is referenced many times since they are better than other solutions, and since I didn't create "new" object for each of them, one value is changed many time which messed up the whole program. It tool me quite a while to find everything and fix it.

##THIS PROJECT

In this project, I have implement the Routelette selecting method, the rank based method and the Tournament method with some of their configurations benchmark.

In the future, I want to implement other Crossover and mutation method to test their performance as well. 

I also want to try implement Gray-code number to use instead of Binary number, to compare between them.

##BENCHMARK

_All bench mark run on 500 run of the genetic algorithm, time is in miliseconds, try to minimize the function_
 > f(x1, x2) = (x_1^2 + x_2 - 11)^2 + (x_1 + x_2^2 - 7)^2.

Since we know the minimize point of f(x1, x2) is at (3,2) at f(x) = 0, it is easy to just set the exitting condition to fitness >= 0.999. However, for another problem, another exitting condition is neccesray.


###Roulette Wheel Selection
*Population 10 - Average loop count 211 - execution time 41537
*Population 20 - Average loop count 90 - execution time 35390
*Population 40 - Average loop count 41 - execution time 29176
*Population 60 - Average loop count 28 - execution time 29547


###Tournament Selection
*Population 10 - Size = 15% (3/20)- Average loop count 205 - execution time 39003
*Population 20 - Size = 15% (/20)- Average loop count 83 - execution time 29451
*Population 20 - Size = 20% (4/20)- Average loop count 82 - execution time 32353 
*Population 20 - Size = 25% (5/20)- Average loop count 83 - execution time 30860
*Population 40 - size = 20% (4/20)- Average loop count 41 - execution time 30091
*Population 40 - size = 15% (3/20)- Average loop count 42 - execution time 30523
*Population 60 - size = 15% (3/20)- Average loop count 28 - execution time 33022

###Rank Based Selection
*Population 10 - Rank distribution: 30%, 25%, 20%, 15%, 9%, 1% - Average loop count 200 - execution time 39154
*Population 20 - Rank distribution: 40%, 30%, 18%, 11%, 7%, 4% - Average loop count 84 - execution time 32357
*Population 20 - Rank distribution: 50%, 30%, 12%, 8% - Average loop count 80 - execution time 30256
*Population 40 - Rank distribution: 40%, 30%, 18%, 11%, 7%, 4% - Average loop count 38 - execution time 27662
*Population 40 - Rank distribution: 30%, 25%, 20%, 15%, 9%, 1% - Average loop count 40 - execution time 29371
*Population 60 - Rank distribution: 30%, 25%, 20%, 15%, 9%, 1%, - Average loop count 29 - execution time 32980
