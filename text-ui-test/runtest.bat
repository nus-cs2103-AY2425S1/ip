@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM set the path to the save file
SET "savefile=list_test.txt"

REM delete the list file if there is one
if exist %savefile% del %savefile%

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Barney %savefile% < input.txt > ACTUAL.TXT

REM remove the save file
if exist %savefile% del %savefile%

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
