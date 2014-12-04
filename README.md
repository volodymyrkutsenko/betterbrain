 
### This is the Arithmetical Equations Generator Release 1.0 release for BetterBrain.

![Colourful Brain](/resources/brain.jpg?raw=true "Colourful Brain")

BetterBrain itself is a logical addition to a book called "Train Your Brain - 60 Days to a Better Brain" by Ryuta Kawashima.
In case you want to find out what the book is about search it in the Internet (some information available via this link [Train Your Brain](http://en.wikipedia.org/wiki/Train_Your_Brain:_60_Days_to_a_Better_Brain)) but here is a brief description of what it offers:
In order to train your brain Ryuta Kawashima suggests simple set of exercises for each day that take no more than 5 minutes.
Every day the trainee should solve a set of 100 simple arithmetical equations as quick as possible. 
Each five days the trainee takes a memory test, a counting test and a stroop test to verify the progress.

The book has this program covered for 60 days. However Ryuta Kawashima insists on continuing the training even after completion of the 60 day program but the book itself does not have any more exercises like ones the trainee has already passed.

The current BetterBrain project is intended to generate the whole 60 day training program similar to what the original book contains.
However each new generated program is generated completely random and brand new so every new generated training program by BetterBrain differs from previous.

Using BetterBrain Arithmetical Equations Generator Release 1.0 release you can generate a single sheet with 100 simple arithmetical equations for one day.
Memory test and stroop test generation are coming up with the next releases.


To generate an equations sheet you should run run BetterBrain .jar file following this pattern:
&lt;directory_with_BetterBrain&gt;/java -jar BetterBrain.jar [-includeAnswers] [numberOfEquations] [maxNumberOfDuplicatesOnSheet] [maxZeroArgumentedEquationsQuantity] [maxZeroResultEquationsQuantity]

-includeAnswers (optional) - outputs the equation answer directly in the equation after the equal sign (so "9 + 7 = " turns into "9 + 7 = 16")
numberOfEquations (optional) - number of simple arithmetical equations per sheet. Defaulted to 60.
maxNumberOfDuplicatesOnSheet (optional) - number of duplicated equations on a single sheet. Defaulted to 0. The original book sometimes has duplicates in its training sheets but mostly it doesn't. Equations like 2 + 3 = 5 and 2 + 3 = 5 are considered duplicates but equations like 2 + 3 = 5 and 3 + 2 = 5 are *not* duplicates.
maxZeroArgumentedEquationsQuantity (optional) - number of equations with at least one zero argument per sheet. Defaulted to 4. The original book usually doesn't have more than 4 per sheet either.
maxZeroResultEquationsQuantity (optional) - number of equations with zero result. Defaulted to 4. The original book usually doesn't have more than 4 per sheet either.

After running this command BetterBrain will give the output similar to this:

2 - 0 = <br />
9 + 7 = <br />
9 \* 3 = <br />
15 - 8 = <br />
2 + 8 = <br />
7 \* 8 = <br />
8 \* 7 = <br />
10 - 1 = <br />
10 - 8 = <br />
9 \* 7 = <br />
1 + 1 = <br />
7 \* 1 = <br />
7 - 0 = <br />
2 \* 7 = <br />
4 \* 5 = <br />
7 \* 6 = <br />
3 \* 8 = <br />
...<br />

To make a simple text file with a sheet you may want to redirect the output to a file (e.g. by adding " > sheet.txt" at the end of the command).

In case you want the answers directly in the sheet use -includeAnswers command argument:

2 - 0 = 2<br />
9 + 7 = 16<br />
9 \* 3 = 27<br />
15 - 8 = 7<br />
2 + 8 = 10<br />
7 \* 8 = 56<br />
8 \* 7 = 56<br />
10 - 1 = 9<br />
10 - 8 = 2<br />
9 \* 7 = 63<br />
1 \+ 1 = 2<br />
7 \* 1 = 7<br />
7 - 0 = 7<br />
2 \* 7 = 14<br />
4 \* 5 = 20<br />
7 \* 6 = 42<br />
3 \* 8 = 24<br />
...<br />


In case you are a Windows user for economy of time you may simply run betterbrain.bat file that will run BetterBrain with its default options and generate the results into equations_sheet.txt

For help use this pattern:
&lt;directory_with_BetterBrain&gt;/java -jar BetterBrain.jar help