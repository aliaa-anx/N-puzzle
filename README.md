# N-Puzzle Solver

A comprehensive N-Puzzle (8-puzzle, 15-puzzle, etc.) solver implemented to explore and compare the efficiency of various search algorithms. This project provides insights into pathfinding performance by tracking step counts and search depth across different heuristic and non-heuristic approaches.

## 🚀 Features

* **Multiple Algorithm Support:** Compare how different search strategies handle the same puzzle state.
* **Efficiency Metrics:** Automatically counts the number of moves (steps) to reach the solution and the total nodes expanded.
* **Customizable Grid Size:** Support for N x N configurations (standard 3x3, 4x4, etc.).
* **Heuristic Comparisons:** Implements Manhattan Distance and Misplaced Tiles for A* search.

## 🧠 Algorithms Implemented

The solver includes several fundamental AI search strategies:

* **Breadth-First Search (BFS):** Guarantees the shortest path but explores nodes level by level, making it memory-intensive.
* **Depth-First Search (DFS):** Explores paths deeply but may not find the optimal solution in terms of step count.
* **A* Search:** An informed search algorithm that uses heuristics to find the optimal path efficiently.
* **Depth-Limited Search / IDDFS:** Balances the benefits of BFS and DFS by searching to a specific depth and increasing it iteratively.

## 📊 Performance Tracking

The primary goal of this project is to analyze the efficiency of each algorithm. For every solved puzzle, the system outputs:

* **Path Cost:** Total moves from the initial state to the goal state.
* **Nodes Expanded:** The total number of states explored during the search.
* **Search Depth:** The maximum depth reached in the search tree.
