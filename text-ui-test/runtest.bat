@ECHO OFF

REM Create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM Delete output from previous run
del ACTUAL.TXT

REM Compile the code into the bin folder
REM Adjusting the path to the new package structure
javac -Xlint:none -d ..\bin ..\src\main\java\bangmang\Command\*.java ..\src\main\java\bangmang\Task\*.java ..\src\main\java\bangmang\Exception\*.java ..\src\main\java\bangmang\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM No error here, errorlevel == 0

REM Run the program, ensure the fully qualified class name is correct
REM Update the classpath to point to the bin directory and the main class name
java -classpath ..\bin bangmang < input.txt > ACTUAL.TXT

REM Compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
