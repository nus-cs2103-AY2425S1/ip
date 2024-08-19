#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]; then
    mkdir ../bin
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java; then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# delete actual-test-results directory if it exists
if [ -d "./actual-test-results" ]; then
    rm -r ./actual-test-results
fi

# create the actual-test-results directory to store test case outputs
mkdir ./actual-test-results

# loop through all test files in the tests directory
for testfile in ./tests/test*.txt; do
    # extract the test number (e.g., 1 from test1.txt)
    testnum=$(echo "$testfile" | grep -o '[0-9]\+')

    # corresponding expected output file
    expectedfile="./expected-test-results/expected${testnum}.txt"

    # check if the expected file exists
    if [ ! -f "$expectedfile" ]; then
        echo "Expected file $expectedfile does not exist. Skipping test $testnum."
        continue
    fi

    # specify the output file in the actual-test-results directory
    actualfile="./actual-test-results/actual${testnum}.txt"

    # run the program with the current test input and redirect the output to the actual test file
    java -classpath ../bin DailyTasks < "$testfile" > "$actualfile"

    # convert both actual and expected files to UNIX format
    cp "$expectedfile" EXPECTED-UNIX.TXT
    dos2unix "$actualfile" EXPECTED-UNIX.TXT

    # compare the actual output to the expected output
    echo "Running test $testnum..."
    if diff "$actualfile" EXPECTED-UNIX.TXT > /dev/null; then
        echo "Test $testnum result: PASSED"
    else
        echo "Test $testnum result: FAILED"
    fi
done

# remove the temporary EXPECTED-UNIX.TXT file
if [ -e "./EXPECTED-UNIX.TXT" ]; then
    rm EXPECTED-UNIX.TXT
fi
