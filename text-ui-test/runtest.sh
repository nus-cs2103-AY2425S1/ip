#!/usr/bin/env bash

# Exit immediately if a command exits with a non-zero status.
set -e

# Change to the script's directory to ensure relative paths work correctly.
cd "$(dirname "$0")"

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]; then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]; then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminate if error occurred
if ! javac -Xlint:none -d ../bin $(find ../src/main/java -name "*.java"); then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin Zero < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
if diff ACTUAL.TXT EXPECTED-UNIX.TXT > /dev/null; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
