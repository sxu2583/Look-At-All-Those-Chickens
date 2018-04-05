ESS Simulation

----------------------
Java - Language Picked
----------------------
In this version of the Hawk v Dove simulation I chose to use Java as
my language. This is because when outlining my approach to creating the
simulation I noted that I could take advantage of OOP and create a class
called bird which would be the blueprint for a bird object. With which I could
then easily keep track of bird data as well as manipulate when necessary.
Not to mention by doing this approach I was able to make my code more modular
and if necessary I can add on to it at a later time.


------------------
Running Simulation
------------------
Running the program in java requires compiling the code
and running the executable (Goto section if you don't know how)
with 1 mandatory arguments and 3 other
optional arguments.

First Argument = [Population of Birds]
Second Argument = {Optional}[Percentage of Hawks]
Third Argument = {Optional}[Resource Amount]
Fourth Argument = {Optional}[Hawk v Hawk Interaction Loss]

The mandatory argument is the population without otherwise the simulation cannot
run. If you don't input anything for the other arguments the program will use the
built in default values.

-----------
Conclusions
-----------
After creating and conducting different models on this simulation program
what I have noticed is that by themselves, since doves cannot be killed, a model
with just doves will just continue growing. However, if we put in just Hawks the opposite
effect happens where all the birds die. So after testing out groups where there
are both hawks and doves its easy to notice that when the number of hawks is less then
doves (best when just 2) we see that the bird population again remains stable.
With hawk v hawk losses being a low probability event but still evident. The hawk strategy
only results in almost no deaths when we start changing our parameters to decrease the
loss of Hawk v Hawk and increase the resource amount. Though changing the percentage of
hawks only benefits hawks when it's decreased. But in all cases Dove's do very well
even when changing the resource amount to a low number.

The point of an ESS is to find a strategy that will be stable under natural selection.
For this type of model with Hawks v Doves we notice that a population of just doves
will just continue to grow as they will all just share the resources. While one with only
hawks will always die out. Therefore the payoffs of a fully Hawk Strategy will not be
as successful as the dove strategy. So we can conclude because of this that natural
selection will favour the Dove strategy. And the alleles for Dove behaviour will increase
whereas the alleles for Hawk behaviour will decrease. Thus in this case Hawk is not an ESS
but we do see that Doves is the better strategy so Dove is an ESS.

By conducting these simulations what I learned about Evolutionary Stable Strategies was
that even if you can make a move with risk that will result in a large payoff it may not
be the best move in the long run. Not to mention that ESS did in fact raise important notes
on how wildlife "fight" with each other that I had never noticed before. If every species
was to fight over a resource with deadly force then all populations would decrease to
nothing. In the case of dove, taking a passive approach not only saved the lives of
all of its population but at the same time gave it higher rewards in the long run (in most cases).

ESS, relates to Game Theory since Game Theory is basically an approach in which the strategies
for dealing with competitive situations depends not only on the player but may also depend
on the choices of all participants. And that's what ESS takes into account. ESS, takes into account
the best strategy for stability in natural selection and to do that it must account for all the
actions of each player. And the payoffs for each strategy.

Ess, also relates to Intelligent Systems because an intelligent system
would be able to analyze and respond to the simulation in order to make an action or choose the best
strategy. In this case with our simulation the program itself didn't learn anything but we as the user
learned from information (trial and error) what the best actions would be so in this case we are the
intelligent agents being fed the data.

--------------
How To Compile
--------------
- Download the files and open up a terminal.
- Change directories until you are in the source folder for this program
- type in "javac Simulation.java"
- then type in "java Simulation [arg1] {arg2} {arg3} {arg4}"
- where arg are the arguments you want to use