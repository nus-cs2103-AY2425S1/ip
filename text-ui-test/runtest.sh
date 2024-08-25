#!/usr/bin/env bash

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

# Define the path to the Gson JAR file
GSON_JAR_PATH="../lib/gson-2.11.0.jar"
GSON_ERROR_HANDLING_PATH="../lib/error_prone_annotations-2.27.0.jar"

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java:$GSON_JAR_PATH:$GSON_ERROR_HANDLING_PATH -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin:$GSON_JAR_PATH Juno < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi