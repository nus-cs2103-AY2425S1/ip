#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]; then
  mkdir ../bin
fi

# delete output from previous runs
if [ -e "./ACTUAL.TXT" ]; then
  rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/**.java; then
  echo "********** BUILD FAILURE **********"
  exit 1
fi

for input_file in input*.txt; do
  # Extract the base name from the input file (e.g. "input_<name>")
  base_name=$(basename "$input_file" .txt)

  uppercase_name=$(echo "${base_name#input_}" | tr '[:lower:]' '[:upper:]')
  expected_file="EXPECTED_${base_name#input_}.txt"

  # Run the program, feed commands from the input file, and redirect the output to ACTUAL.TXT
  java -classpath ../bin Mizz <"$input_file" >ACTUAL.TXT

  # Convert to UNIX format
  cp "$expected_file" EXPECTED-UNIX.TXT
  dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

  # Compare the output to the expected output
  diff ACTUAL.TXT EXPECTED-UNIX.TXT
  if [ $? -eq 0 ]; then
    echo -e "Test result for $input_file: PASSED\n"
  else
    echo -e "Test result for $input_file: FAILED\n"
  fi
done

# Clean up the generated files
rm EXPECTED-UNIX.TXT
rm ACTUAL.TXT
