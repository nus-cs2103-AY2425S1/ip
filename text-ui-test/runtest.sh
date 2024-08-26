#!/usr/bin/env bash

# Delete the /data directory from the previous run
DATA_DIR="./data"
if [ -d "$DATA_DIR" ]; then
    rm -r "$DATA_DIR"
fi

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Filter out commented lines from the input file
# Lines starting with '#' are ignored
# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
grep -v '^#' input.txt | java -classpath ../bin PurrfessorDipsy > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
if diff ACTUAL.TXT EXPECTED-UNIX.TXT; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi