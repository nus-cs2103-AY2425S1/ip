#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -d "./debug" ]
then
    if [ ! -z "$( ls -A './debug' )" ]
    then
        rm -r ./debug/*
    fi
else
    mkdir ./debug
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# make a temp folder
if [ ! -d "./temp" ]
then
    mkdir ./temp
fi

function unit_test {
    local input_file_path=./input/input_$1.txt
    local expected_file_path=./expected/EXPECTED_$1.txt
    local debug_file_path=./debug/ACTUAL.txt

    # run the program, feed commands from input.txt file and redirect the output to the ACTUAL.txt
    java -classpath ../bin Duck < $input_file_path > temp/ACTUAL.txt

    # convert to UNIX format
    cp $expected_file_path temp/EXPECTED-UNIX.txt
    dos2unix temp/ACTUAL.txt temp/EXPECTED-UNIX.txt

    # compare the output to the expected output
    diff temp/ACTUAL.txt temp/EXPECTED-UNIX.txt
    if [ $? -eq 0 ]
    then
        echo "Test for $1: PASSED"
    else
        echo "Test for $1: FAILED"
        # Copy the actual and expected outputs for the failed tests to the debug folder
        cp temp/ACTUAL.txt debug/ACTUAL_$1.txt
        cp temp/EXPECTED-UNIX.txt debug/EXPECTED_$1.txt
    fi
}

for input_file in ./input/input_*.txt; do
    test_name=$(echo $input_file | sed -n "s/^.*_\(\S*\)\..*$/\1/p")
    unit_test $test_name
done

# remove the temp folder
rm -r ./temp

exit 0