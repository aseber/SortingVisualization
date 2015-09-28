# Sorting Visualization

This program visualizes common sorting algorithms while allowing the user to set specific data conditions

**In order to compile this program, you will need to include the libraries in the /libs/ folder**

## Algorithms represented:
* Bogo Sort
* Bubble Sort
* Gnome Sort
* Insertion Sort
* Merge Sort
* Selection Sort
* Shaker Sort
* Shell Sort
* Quick Sort

## Data sets represented:
* Direction
  * Forward - Data is generated in sorted order
  * Reverse - Data is generated in reverse sorted order
* Randomness
  * Sorted - Does not modify and elements in the array
  * Almost Sorted - Randomizes by doing DATASIZE/50 random replacements
  * Random - Randomizes the data by doing DATASIZE*3 random replacements
* Uniqueness
  * All Unique - All data points are unique
  * Few Unique - All data points are modified to fall in a range of four values, no values are unique, each of the four values should be equally represented

## Controls
* Spacebar - Starts the simulation with the currently selected algorithm and data set
* Alt - Enables debug information
* Escape - Resets the data set to a copy of its original state when generated

## Settings menu
* Modify Algorithm dialog:
  * Setting which algorithm you want to run
  * Setting data set direction
  * Setting data set randomness
  * Setting data set uniqueness
  * Setting data set size *DATASIZE*

* Window menu
  * Setting window size, not necessary as you can simply resize it by hand
  * Enable/Disable 'Set' updates
  * Enable/Disable 'Compare' updates
  * Enable/Disable 'Get' updates
  * Enable/Disable 'Screen updates' while sorting
    * It is very important that you disable all drawing features if you want to get an accurate representation of the algorithms runtime! Screen updates take a lot of time to draw and are not multithreaded at this time!

* Sleep Timer
  * Allows you to slow down the algorithm execution

## Change detection
When running the program you will notice that the colors red, green, and blue flash on the screen.

Each color represents a specific function that the algorithm is doing
* Red - The algorithm retrieved an element at a specific index
* Green - The algorithm compared two elements
  * Both elements are colored green
* Blue - The algorithm set an element at a specific index

## Running the program
Running the program is as simple as pressing the "Start Simulation" button or pressing the spacebar. You can pause the simulation midway by again pressing the spacebar. You can also modify the speed at which the algorithm runs by setting the sleep timer in the settings menu.

At the top right of the program you will notice a set of numbers. These numbers are very important in determining the efficiency of the algorithm
* Accesses - The number of times the algorithm accessed elements in the data set
* Compares - The number of times the algorithm compared two elements in the data set
* Sets - The number of times the algorithm set elements in the data set
* Run Time - Shows the running time of the algorithm
