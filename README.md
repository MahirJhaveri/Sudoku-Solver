# Sudoku-Solver
Intelligent agent that solves Sudoku puzzles

## How does it work?
Sudoku is an eample of a Constriant Satisfaction Problem in AI. The Algorithm works by using a Constraint Propagation & Recursive Backtracking which together speed up the algorithm significantly. Constraint Propagation reffers to updating the constraints( or set of possible values) for other cells once we make a descision for one cell while recurscive backtracking reffers to making assumptions for the values certain cells and proceeding in a depth-first fashion till we either find solution and are done or reach a contradictory case and then work our way backwards.

## An Easy Example
Can be solved by repeated application of constraint propagation only.
<pre>

</pre>
![alt text](https://raw.githubusercontent.com/MahirJhaveri/Sudoku-Solver/master/images/EasyTest.png)

## A Hard Example
Requires both strategies to be solved
<pre>

</pre>
![alt text](https://raw.githubusercontent.com/MahirJhaveri/Sudoku-Solver/master/images/HardTest.png)
