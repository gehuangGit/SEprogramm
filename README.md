# Minesweeper

This project is an implementation of the well-known Minesweeper game for the lecture Software Engineering of the University of Applied Science Konstanz, Germany.

It was important to have a good Model-View-Controller-structure and to implement some patterns like the Observer-Pattern (which is necessary for running the TUI and the GUI at the same time).
Furthermore we should use dependency injection with Google Guice.

Further requirements of this project were:

+ using the version control with git on github.
+ performing test-driven-development using JUnit.
+ metrics with sonar.
+ components and interfaces between each layer.

The initial UI was a textual User-Interface, so we could operate with the code in an early state of the project. The graphical User-Interface had not to be built until the model- and the controller-layer are (almost) finished, and there is no need to perform big changes on these layers anymore.
