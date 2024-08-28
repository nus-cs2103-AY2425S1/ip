#!/usr/bin/env bash

# create out/production/ip directory if it doesn't exist
if [ ! -d "./out/production/ip" ]; then
    mkdir -p ./out/production/ip
fi

# delete output from previous run
if [ -e "./text-ui-test/ACTUAL.TXT" ]; then
    rm ./text-ui-test/ACTUAL.TXT
fi

# delete the data/tasks.csv file if it exists
if [ -e "./data/tasks.csv" ]; then
    rm ./data/tasks.csv
fi

# compile the code into the out/production/ip folder, terminates if error occurred
if ! javac -cp ./src/main/java -Xlint:none -d ./out/production/ip $(find ./src/main/java/orion -name "*.java"); then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ./out/production/ip orion.Orion < ./text-ui-test/input.txt > ./text-ui-test/ACTUAL.TXT

# convert to UNIX format
cp ./text-ui-test/EXPECTED.TXT ./text-ui-test/EXPECTED-UNIX.TXT
dos2unix ./text-ui-test/ACTUAL.TXT ./text-ui-test/EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ./text-ui-test/ACTUAL.TXT ./text-ui-test/EXPECTED-UNIX.TXT
if [ $? -eq 0 ]; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi

