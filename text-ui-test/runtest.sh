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

# delete savafile from previous run
if [ -e "./savefile" ]
then
    rm savefile
fi
cp ../src/test/resources/savefile_original ./savefile

# compile the code into the bin folder, terminates if error occurred
pushd ..
if ! ./gradlew shadowjar
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi
popd || exit

# shellcheck disable=SC1073
until [ -e "./savefile" ]
do
  true
done

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -jar ../build/libs/yappingbot.jar -c -s ./savefile < input.txt > ACTUAL.TXT

# convert to UNIX format
# cp EXPECTED.TXT EXPECTED-UNIX.TXT
#dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# append savefile to ACTUAL.TXT
cat savefile >> ACTUAL.TXT
# compare the output to the expected output
diff ACTUAL.TXT EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
