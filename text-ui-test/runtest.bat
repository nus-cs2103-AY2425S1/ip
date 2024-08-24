@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM change directory to the root project directory
cd /d ..\..\

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath .\ip\bin Wiggly < .\ip\text-ui-test\input.txt > .\ip\text-ui-test\ACTUAL.TXT

REM compare the output to the expected output
FC .\ip\text-ui-test\ACTUAL.TXT .\ip\text-ui-test\EXPECTED.TXT
