#!/usr/bin/env bash

# Create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# Delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# Compile the code into the bin folder, terminates if error occurred
# Notice the change: compiling everything under 'rizz'
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/rizz/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Run the program (replace 'Main' with your actual main class)
# Ensure it points to the correct package (rizz)
java -classpath ../bin rizz.Main < input.txt > ACTUAL.TXT

# Convert to UNIX format (optional, only needed if working with different OS)
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# Compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
