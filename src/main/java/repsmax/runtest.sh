#!/bin/bash

# Compile the program
javac Repsmax.java Task.java Todo.java Deadline.java Event.java

# Run the program with input redirection and save the output to an actual output file
java Repsmax < input.txt > actual.txt

# Compare the actual output with the expected output
diff actual.txt expected.txt

# If the diff command returns a difference, it will be shown in the console.
# If there is no difference, the test passed.
