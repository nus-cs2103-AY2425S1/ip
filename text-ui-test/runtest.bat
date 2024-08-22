@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Weeny < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

REM Check the exit code of FC command
IF ERRORLEVEL 1 (
    echo ********** TEST FAILED **********
    echo Please check the differences between ACTUAL.TXT and EXPECTED.TXT.
) ELSE (
    echo ********** TEST PASSED **********
)

REM Pause the script so you can review the results
pause
