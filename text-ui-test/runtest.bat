@ECHO OFF

REM create necessary directories
if not exist ..\bin mkdir ..\bin
if not exist ..\data mkdir ..\data

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
echo Compiling Java files...
javac -cp ..\src\main\java -d ..\bin ..\src\main\java\duck\command\*.java ..\src\main\java\duck\main\Duck.java ..\src\main\java\duck\parser\*.java ..\src\main\java\duck\storage\*.java ..\src\main\java\duck\task\*.java ..\src\main\java\duck\ui\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    echo Java compilation failed. Please check your Java files for errors.
    exit /b 1
)
echo Compilation successful.

REM Check if input.txt exists
if not exist input.txt (
    echo ********** INPUT FILE MISSING **********
    echo input.txt file not found in the current directory.
    exit /b 1
)

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
echo Running the program...
echo Java classpath: ..\bin
echo Main class: duck.main.Duck
echo Input file: input.txt
java -classpath ..\bin duck.main.Duck < input.txt > ACTUAL.TXT 2>&1
IF ERRORLEVEL 1 (
    echo ********** RUNTIME FAILURE **********
    echo The program crashed. Check the Java runtime errors above.
    type ACTUAL.TXT
    exit /b 1
)
echo Program execution completed.

REM Check if ACTUAL.TXT was created
if not exist ACTUAL.TXT (
    echo ********** OUTPUT FILE MISSING **********
    echo ACTUAL.TXT was not created. The program may not have produced any output.
    exit /b 1
)
if not exist data mkdir data
REM compare the output to the expected output
echo Comparing output...
FC ACTUAL.TXT EXPECTED.TXT
IF ERRORLEVEL 1 (
    echo ********** TEST FAILURE **********
    echo The output does not match the expected output. See the differences above.
) ELSE (
    echo All tests passed!
)