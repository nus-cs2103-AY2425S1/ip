#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Loop through each test folder
for test_dir in ./test*; do
    echo "Running test in $test_dir"

    # delete output from previous run
    if [ -e "$test_dir/ACTUAL.TXT" ]
    then
        rm "$test_dir/ACTUAL.TXT"
    fi

    # run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
    java -classpath ../bin Schedulo < "$test_dir/input.txt" > "$test_dir/ACTUAL.TXT"

    # convert to UNIX format
    cp "$test_dir/EXPECTED.TXT" "$test_dir/EXPECTED-UNIX.TXT"
    dos2unix "$test_dir/ACTUAL.TXT" "$test_dir/EXPECTED-UNIX.TXT"

    # compare the output to the expected output
    diff "$test_dir/ACTUAL.TXT" "$test_dir/EXPECTED-UNIX.TXT"
    if [ $? -eq 0 ]
    then
        echo "Test result in $test_dir: PASSED"
    else
        echo "Test result in $test_dir: FAILED"
        exit 1
    fi
done
