#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL_1.TXT" ]
then
    rm ACTUAL_1.TXT
fi

# delete save from previous run
if [ -e "./data/rotodo.txt" ]
then
    rm ./data/rotodo.txt
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java/ -Xlint:none -d ../bin ../src/main/java/rotodo/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

echo "+---------------------------+"
echo "| First pass (no save file) |"
echo "+---------------------------+"

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin rotodo/RoTodo < input_1.txt > ACTUAL_1.TXT

# convert to UNIX format
cp EXPECTED_1.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL_1.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL_1.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test 1 result: PASSED"
else
    echo "Test 1 result: FAILED"
fi

echo ""
echo "+---------------------------+"
echo "| Second pass (w save file) |"
echo "+---------------------------+"

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin rotodo/RoTodo < input_2.txt > ACTUAL_2.TXT

# convert to UNIX format
cp EXPECTED_2.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL_2.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL_2.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test 2 result: PASSED"
else
    echo "Test 2 result: FAILED"
fi

exit 0