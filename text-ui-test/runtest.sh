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

# delete data folder from previous run
if [ -d "./data" ]
then
    rm -r ./data
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/ekud/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin ekud.Ekud < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
PRINT_TEST=$(diff ACTUAL.TXT EXPECTED-UNIX.TXT)
SAVE_TEST=$(diff ./data/tasks.txt TASKS-EXPECTED.TXT)
echo "print test:"
echo $PRINT_TEST
echo "save test:"
echo $SAVE_TEST
if [ "$PRINT_TEST" == "" ] && [ "$SAVE_TEST" == "" ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi