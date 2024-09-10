java -classpath ../bin echoa < input.txt > ACTUAL.TXT
REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT


