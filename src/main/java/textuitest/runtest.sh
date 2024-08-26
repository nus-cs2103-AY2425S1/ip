#!/usr/bin/env bash

# Define paths relative to the current directory
SOURCE_DIR="../"
BIN_DIR="../bin"
INPUT_FILE="input.txt"
ACTUAL_FILE="ACTUAL.TXT"
EXPECTED_FILE="EXPECTED.TXT"

# Create bin directory if it doesn't exist
if [ ! -d "$BIN_DIR" ]; then
    mkdir -p "$BIN_DIR"
fi

# Delete output from previous run
if [ -e "$ACTUAL_FILE" ]; then
    rm "$ACTUAL_FILE"
fi

# Compile the code into the bin folder, terminate if error occurred
if ! find "$SOURCE_DIR" -name "*.java" -print | xargs javac -cp "$SOURCE_DIR" -Xlint:none -d "$BIN_DIR"; then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath "$BIN_DIR" main.java.Main < "$INPUT_FILE" > "$ACTUAL_FILE"

# Compare the output to the expected output
diff "$ACTUAL_FILE" "$EXPECTED_FILE"
if [ $? -eq 0 ]; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi

