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

# delete fridayTaskList file if it exists in text-ui-testing/data directory
if [ -e "./data/fridayTaskList.txt" ]; then
    rm ./data/fridayTaskList.txt
fi

# compile the code into the bin folder, terminates if error occurred
find ../src/main/java -name "*.java" | xargs javac -cp ../src/main/java -Xlint:none -d ../bin
if [ $? -ne 0 ]; then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
#java -classpath ../bin app.Launcher < input.txt > ACTUAL.TXT
java -classpath "../bin:../src/main/resources" app.Launcher < input.txt > ACTUAL.TXT

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