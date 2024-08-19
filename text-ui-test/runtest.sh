#!/usr/bin/env bash

# Absolute paths
SRC_PATH="/Users/junlongling/Downloads/NUS/Year 3/CS2103T/ip/src/main/java"
BIN_PATH="/Users/junlongling/Downloads/NUS/Year 3/CS2103T/ip/bin"

# Create bin directory if it doesn't exist
if [ ! -d "$BIN_PATH" ]; then
    mkdir -p "$BIN_PATH"
fi

# Delete output from previous run
if [ -e "./ACTUAL.TXT" ]; then
    rm ACTUAL.TXT
fi

# Compile the code into the bin folder, terminates if an error occurred
if ! javac -cp "$SRC_PATH" -Xlint:none -d "$BIN_PATH" "$SRC_PATH"/*.java; then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Run the program, feed commands from input.txt file and redirect the output to ACTUAL.TXT
java -classpath "$BIN_PATH" GPT < input.txt > ACTUAL.TXT

# Compare the output to the expected output
if diff ACTUAL.TXT EXPECTED.TXT; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
