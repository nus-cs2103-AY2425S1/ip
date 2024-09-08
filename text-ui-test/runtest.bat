@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder (compile all Java files in subdirectories)
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\drbrown\*.java ..\src\main\java\drbrown\command\*.java ..\src\main\java\drbrown\task\*.java ..\src\main\java\drbrown\utils\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to ACTUAL.TXT
java -classpath ..\bin drbrown.DrBrown < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
