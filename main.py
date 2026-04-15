import heapq
import time
import random
import math
from copy import deepcopy
import tkinter as tk
import threading


def goalState(n):
    if n == 8:
         return [
            [1, 2, 3],
            [4, 5, 6],
            [7, 8, 0],  # 0 represents the blank tile
        ]
    elif n == 15:
        return [
            [1, 2, 3, 4],
            [5, 6, 7, 8],
            [9, 10, 11, 12],
            [13, 14, 15, 0],
        ]
    elif n == 24:
         return [
            [1, 2, 3, 4, 5],
            [6, 7, 8, 9, 10],
            [11, 12, 13, 14, 15],
            [16, 17, 18, 19, 20],
            [21, 22, 23, 24, 0],
        ]
    else:
        raise ValueError("Invalid input. Supported sizes are 8, 15, and 24.")


def possibleNextStates(state):
    newStates = []
    iBlank, jBlank = None, None

    for i in range(len(state)):
        for j in range(len(state)):
            if state[i][j] == 0:
                iBlank, jBlank = i, j

    if iBlank > 0:  # Up
        stateCopy = deepcopy(state)
        stateCopy[iBlank][jBlank] = stateCopy[iBlank - 1][jBlank]
        stateCopy[iBlank - 1][jBlank] = 0
        newStates.append(stateCopy)

    if iBlank < len(state) - 1:  # Down
        stateCopy = deepcopy(state)
        stateCopy[iBlank][jBlank] = stateCopy[iBlank + 1][jBlank]
        stateCopy[iBlank + 1][jBlank] = 0
        newStates.append(stateCopy)

    if jBlank > 0:  # Left
        stateCopy = deepcopy(state)
        stateCopy[iBlank][jBlank], stateCopy[iBlank][jBlank - 1] = stateCopy[iBlank][jBlank - 1], stateCopy[iBlank][jBlank]
        newStates.append(stateCopy)

    if jBlank < len(state) - 1:  # Right
        stateCopy = deepcopy(state)
        stateCopy[iBlank][jBlank], stateCopy[iBlank][jBlank + 1] = stateCopy[iBlank][jBlank + 1], stateCopy[iBlank][jBlank]
        newStates.append(stateCopy)

    return newStates


def flatten_2d_array(state):
    arr_1d = []
    for row in state:
        for value in row:
            arr_1d.append(value)
    return arr_1d


def inversion_count(arr):
    n = len(arr)
    inv_count = 0
    for i in range(n - 1):
        for j in range(i + 1, n):
            if arr[i] != 0 and arr[j] != 0 and arr[i] > arr[j]:
                inv_count += 1
    return inv_count


def check_solvability(state):
    new_arr = flatten_2d_array(state)
    grid_size_val = int(math.sqrt(len(new_arr)))

    if (grid_size_val % 2):
        inv_count = inversion_count(new_arr)
        if (inv_count % 2 == 0):
            return True
        else:
            return False
    elif (grid_size_val % 2 == 0):
        inv_count = inversion_count(new_arr)
        blank_index = blank_pos(new_arr)
        if (inv_count % 2 == 0 and blank_index % 2 == 1):
            return True
        elif (inv_count % 2 == 1 and blank_index % 2 == 0):
            return True
        else:
            return False


def blank_pos(arr):
    grid_size = int(math.sqrt(len(arr)))  # Get the grid size
    blank_index = arr.index(0)
    row_from_top_index = blank_index // grid_size  # Calculate the row index from the top
    row_from_bottom_index = grid_size - row_from_top_index
    return row_from_bottom_index


def randomizedInitialState(goalState, moves=50):
    state = deepcopy(goalState)
    for _ in range(moves):
        nextStates = possibleNextStates(state)
        state = random.choice(nextStates)

    # Keep generating random states until a solvable one is found
    while not check_solvability(state):
        state = deepcopy(goalState)
        for _ in range(moves):
            nextStates = possibleNextStates(state)
            state = random.choice(nextStates)
    return state


def findPosition(tile, goalState):
    for i in range(len(goalState)):
        for j in range(len(goalState)):
            if tile == goalState[i][j]:
                return i, j


#-------------------------------------------------------------------------------------------------

def randomizedInitialState(goalState, moves=50):
    state = deepcopy(goalState)
    for _ in range(moves):
        nextStates = possibleNextStates(state)
        state = random.choice(nextStates)

    # Keep generating random states until a solvable one is found
    while not check_solvability(state):
        state = deepcopy(goalState)
        for _ in range(moves):
            nextStates = possibleNextStates(state)
            state = random.choice(nextStates)
    return state


def findPosition(tile, goalState):
    for i in range(len(goalState)):
        for j in range(len(goalState)):
            if tile == goalState[i][j]:
                return i, j

# first heuristic function
def misplacedTiles(state, goal_state):
    count = 0
    for i in range(len(state)):
        for j in range(len(state)):
            if state[i][j] != goal_state[i][j]:
                count += 1
    return count


#second heuristic function
def misplacedTilesAndPathCost(state, goal_state):
    count = 0
    for i in range(len(state)):
        for j in range(len(state)):
            if state[i][j] != goal_state[i][j]:
                count += 1
    return count

#third heuristic function
def manhattanDistance(state, goal_state):
    count = 0
    for i in range(len(state)):
        for j in range(len(state)):
            if state[i][j] != 0:
                iGoal, jGoal = findPosition(state[i][j], goal_state)
                count += abs(i - iGoal) + abs(j - jGoal)
    return count

#fourth heuristic fuunction
def euclideanDistance(state, goalState):
    totalDistance = 0
    for i in range(len(state)):
        for j in range(len(state)):
            tile = state[i][j]
            if tile != 0:
                 iGoal, jGoal = findPosition(tile, goalState)
                 totalDistance +=  ((i - iGoal)**2 + (j - jGoal)**2) ** 0.5
    return totalDistance

#fifth heuristic function
def linear_conflict(state,goal):
    conflict=0
    row_conflict=0
    col_conflict=0
    grid_size=len(state)
    #goal states for each tile
    goal_states={}
    for i in range(grid_size):
        for j in range(grid_size):
            goal_states[goal[i][j]]=(i,j)
    #check row conflicts
    for i in range(grid_size):
        for j in range(grid_size):
            #if not blank
            if state[i][j]!=0:
                #1:(0,0)>>return 0,0
                goalCurrentTileRow,goalCurrentTileCol=goal_states[state[i][j]]
                #means tile is in same row
                if i==goalCurrentTileRow:
                    #start from next col :1,2,3 at row 0 then start comparing from col 1 >>2 to avoid repetion
                    for q in range(j+1,grid_size):
                        #avoid blank
                        if state[i][q]!=0:
                            #get goal state next tile to compare
                            goalOfNextTileRow,goalOfNextTileCol=goal_states[state[i][q]]
                            #same row diff cols nextgoal is left the currentgoal>>reverse >>row conflict
                            if goalOfNextTileRow==goalCurrentTileRow and goalCurrentTileCol>goalOfNextTileCol:
                                row_conflict+=1
                                print(f"Row Conflict: {state[i][j]} and {state[i][q]} at row {i}")

                # after finishing row conflict check column conflict
                if j == goalCurrentTileCol:
                    # col wise: 1
                    #   2
                    #   3 >> curr>>1 next >>2(curr row+1)
                    for k in range(i + 1, grid_size):
                        # check next not blank
                        if state[k][j] != 0:
                            goalOfNextTileRow, goalOfNextTileCol = goal_states[state[k][j]]
                            # 1 5 3
                            # 4 2 6 >>(5,2)should be reversed >>conflict goalrow 5>>1 goalrow 2>>0 current5>>0 current2>>1
                            if goalOfNextTileCol == goalCurrentTileCol and goalOfNextTileRow < goalCurrentTileRow:
                                col_conflict += 1
                                print(f"Column Conflict: {state[i][j]} and {state[k][j]} at column {j}")
                                # Output the final count of conflicts
                print(f"Row Conflicts:   {row_conflict}")
                print(f"Column Conflicts: {col_conflict}")
                conflict = row_conflict + col_conflict
                manhattan_distance = manhattanDistance(state, goal)
                print("manhattan_distance:")
                print(manhattan_distance)
                total_penlity = (conflict) * 2 + manhattan_distance
                return total_penlity

# Search Algorithm
def bestFirstSearch(initialState, goal_state, heuristicFunction):
    path = []
    pathCost = 0
    frontierQ = [(heuristicFunction(initialState, goal_state), initialState, path, pathCost)]
    heapq.heapify(frontierQ)

    visitedStates = []
    while frontierQ:
        _, state, path, pathCost = heapq.heappop(frontierQ)

        # Check if goal state is reached
        if state == goal_state:
            return path + [state]
        visitedStates.append(state)
        for generatedState in possibleNextStates(state):
            if generatedState not in visitedStates:
                if heuristicFunction == misplacedTilesAndPathCost:
                    nextStatesPathCost = pathCost + 1
                    heapq.heappush(frontierQ, (
                    heuristicFunction(generatedState, goal_state) + nextStatesPathCost, generatedState, path + [state],
                    nextStatesPathCost))
                else:
                    heapq.heappush(frontierQ,
                                   (heuristicFunction(generatedState, goal_state), generatedState, path + [state], pathCost))


#------------------------------------------------------------------------------------------
class NumberPuzzle(tk.Tk):
    def __init__(self):
        super().__init__()

        self.title("Number Puzzle")
        self.geometry("900x700+300+50")
        self.resizable(False, False)
        self.configure(background="#FFF7D1")
        self.create_info_panel()
        self.tile_panel = tk.Frame(self, bg="#FFF7D1")
        self.tile_panel.place(relx=0.6, rely=0.4, anchor="center")
        self.default_num_tiles = 8
        self.goal_state = goalState(self.default_num_tiles)
        self.initial_state = randomizedInitialState(self.goal_state)
        self.create_tiles(self.initial_state, 0)

    def create_tiles(self, state, stepCount):
        for i in range(len(state)):
            for j in range(len(state)):
                tile = tk.Button(
                    self.tile_panel,
                    text=state[i][j] if state[i][j] != 0 else "",
                    font=("Arial", 24),
                    width=5,
                    height=2,
                    bg="#FFABAB" if state[i][j] != 0 else "white",
                    fg="#000000",
                    relief="solid",
                    bd=2
                )
                tile.grid(row=i, column=j, padx=0, pady=0)

        steps = tk.Label(self.tile_panel, text=f"Steps: " + str(stepCount), font=("Arial", 16), bg="#FFF7D1")
        steps.grid(row=5, column=0, columnspan=4, padx=50, pady=10)
        self.refreshPuzzles()

    def create_info_panel(self):
        self.info_frame = tk.Frame(self, background="#FFF7D1")
        self.info_frame.grid(row=1, column=0, columnspan=1, padx = 20,pady=100)
        self.tile_count_entry = tk.Entry(self.info_frame, font=("Arial", 16), width=5)
        self.tile_count_entry.pack(padx=10, pady=10)

        # Buttons for different heuristics
        MT_button = tk.Button(
            self.info_frame,
            text="Misplaced Tiles",
            font=("Arial", 13),
            command=self.MT_puzzle,
            bg="#FFABAB",
            fg="#000000"
        )
        MT_button.pack(padx=10, pady=10)

        MTPC_button = tk.Button(
            self.info_frame,
            text="Misplaced Tiles With Path Cost",
            font=("Arial", 13),
            command=self.MTPC_puzzle,  # Call start_puzzle method when clicked
            bg="#FFABAB",
            fg="#000000"
        )
        MTPC_button.pack(padx=10, pady=10)  # Position the button in the info frame

        Manhattan_button = tk.Button(
            self.info_frame,
            text="Manhattan Distance",
            font=("Arial", 13),
            command=self.MD_puzzle,
            bg="#FFABAB",
            fg="#000000"
        )
        Manhattan_button.pack(padx=10, pady=10)

        Euclidean_button = tk.Button(
            self.info_frame,
            text="Euclidean Distance",
            font=("Arial", 13),
            command=self.Euclidean_puzzle,
            bg="#FFABAB",
            fg="#000000"
        )
        Euclidean_button.pack(padx=10, pady=10)

        Linear_button = tk.Button(
            self.info_frame,
            text="Linear Conflict",
            font=("Arial", 13),
            command=self.LC_puzzle,
            bg="#FFABAB",
            fg="#000000"
        )
        Linear_button.pack(padx=10, pady=10)

    def refreshPuzzles(self):
        self.update()
        self.update_idletasks()
        self.after(100)

    def start_puzzle(self, heuristic_function):
        try:
            num_tiles = int(self.tile_count_entry.get())
            for widget in self.tile_panel.winfo_children():
                widget.destroy()
            if num_tiles in [8, 15, 24]:
                self.goal_state = goalState(num_tiles)
                self.initial_state = randomizedInitialState(self.goal_state)
                self.create_tiles(self.initial_state, 0)
                search_thread = threading.Thread(target=self.run_search, args=(self.initial_state, num_tiles, heuristic_function))
                search_thread.start()
            else:
                print("Please enter a valid number of tiles (8, 15, or 24).")
        except ValueError:
            print("Please enter a valid integer.")

    def run_search(self, initialState, num_tiles, heuristic_function):
        goal_state = goalState(num_tiles)
        path0 = bestFirstSearch(initialState, goal_state, heuristic_function)
        stepCount = 0
        for state in path0:
            self.create_tiles(state, stepCount)
            stepCount += 1
            time.sleep(0.05)

    def MT_puzzle(self):
        self.start_puzzle(misplacedTiles)

    def MTPC_puzzle(self):
        self.start_puzzle(misplacedTilesAndPathCost)

    def MD_puzzle(self):
        self.start_puzzle(manhattanDistance)

    def Euclidean_puzzle(self):
        self.start_puzzle(euclideanDistance)

    def LC_puzzle(self):
        self.start_puzzle(linear_conflict)


gui = NumberPuzzle()
gui.mainloop()
