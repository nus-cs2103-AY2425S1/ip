@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL_1.TXT del ACTUAL_1.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\rotodo\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

echo +---------------------------+
echo | First pass (no save file) |
echo +---------------------------+

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin rotodo\RoTodo < input_1.txt > ACTUAL_1.TXT

REM compare the output to the expected output
FC ACTUAL_.TXT EXPECTED_1.TXT

echo ""
echo +---------------------------+
echo | First pass (no save file) |
echo +---------------------------+

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin rotodo\RoTodo < input_2.txt > ACTUAL_2.TXT

REM compare the output to the expected output
FC ACTUAL_.TXT EXPECTED_1.TXT