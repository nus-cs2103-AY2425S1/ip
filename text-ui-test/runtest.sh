#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "./bin" ]; then
    mkdir ./bin
fi

# find all .java files in the duke directory and subdirectories and compile them into the bin folder
if ! find ./src/main/java/duke -name "*.java" -print0 | xargs -0 javac -cp ./bin -Xlint:none -d ./bin; then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# delete actual-test-results directory if it exists
if [ -d "./text-ui-test/actual-test-results" ]; then
    rm -r ./text-ui-test/actual-test-results
fi

# create the actual-test-results directory to store test case outputs
mkdir ./text-ui-test/actual-test-results

# loop through all test files in the tests directory
for testfile in ./text-ui-test/tests/test*.txt; do
    # extract the test number (e.g., 1 from test1.txt)
    testnum=$(echo "$testfile" | grep -o '[0-9]\+')

    # corresponding expected output file
    expectedfile="./text-ui-test/expected-test-results/expected${testnum}.txt"

    # check if the expected file exists
    if [ ! -f "$expectedfile" ]; then
        echo "Expected file $expectedfile does not exist. Skipping test $testnum."
        continue
    fi

    # specify the output file in the actual-test-results directory
    actualfile="./text-ui-test/actual-test-results/actual${testnum}.txt"

    # run the program with the current test input and redirect the output to the actual test file
    java -classpath ./bin duke.DailyTasks < "$testfile" > "$actualfile"

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

    # remove the temporary EXPECTED-UNIX.TXT file
    if [ -e "./EXPECTED-UNIX.TXT" ]; then
        rm EXPECTED-UNIX.TXT
    fi
done
