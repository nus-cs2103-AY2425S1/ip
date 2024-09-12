#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi
rm -r ../bin/*
# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java/ -Xlint:none -d ../bin ../src/main/java/revir/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# loop through each input file
for testNo in {1..3}
do
    # run the program, feed commands from input file and redirect the output to the ACTUAL.TXT
    java -classpath ../bin revir.Revir --cli < "testcases/TEST-$testNo.txt" > ACTUAL.txt 2>&1

    # compare the output to the expected output
    diff ACTUAL.txt testcases/EXPECTED-$testNo.txt
    if [ $? -eq 0 ]
    then
        echo "Test Case $testNo: PASSED"
    else
        echo "Test Case $testNo: FAILED"
    fi
    rm ACTUAL.txt
    if [ -d "./data" ]
    then
        rm -r ./data
    fi
done