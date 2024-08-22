#!/usr/bin/env bash

#create bin directory if it doesnt exist
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
java -classpath ../bin Donna < input.txt > ACTUAL.TXT

# remove any trailing spaces from ACTUAL.TXT
sed -i '' 's/[ \t]*$//' ACTUAL.TXT

# compare thew output tp the expected output
diff ACTUAL.TXT EXPECTED.TXT
#diff --strip-trailing-cr ACTUAL.TXT EXPECTED.TXT
if [ $? -eq 0 ]
then
  echo "Test result: PASSED"
  exit 0
else
  echo "Test result: FAILED"
  exit 1
fi