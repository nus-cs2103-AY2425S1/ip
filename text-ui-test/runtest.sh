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

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin SigmaBot < input.txt > ACTUAL.TXT

# strip out ANSI color codes from ACTUAL.TXT
sed -E 's/\x1b\[[0-9;]*m//g' ACTUAL.TXT > ACTUAL-NO-COLOR.TXT

# strip out ANSI color codes from EXPECTED.TXT (if it contains any)
cp EXPECTED.TXT EXPECTED-UNIX.TXT
sed -E 's/\x1b\[[0-9;]*m//g' EXPECTED-UNIX.TXT > EXPECTED-NO-COLOR.TXT

# convert to UNIX format
dos2unix ACTUAL-NO-COLOR.TXT EXPECTED-NO-COLOR.TXT

# compare the output to the expected output
diff ACTUAL-NO-COLOR.TXT EXPECTED-NO-COLOR.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
