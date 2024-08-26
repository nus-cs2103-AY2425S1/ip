@ECHO OFF

REM Delete the /data directory from the previous run
IF EXIST data (
    RMDIR /S /Q data
)

REM Create the bin directory if it doesn't exist
IF NOT EXIST ..\bin (
    mkdir ..\bin
)

REM Delete output from the previous run
IF EXIST ACTUAL.TXT (
    del ACTUAL.TXT
)

REM Compile the code into the bin folder
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM Filter out commented lines from input.txt (lines starting with '#')
REM Redirect non-commented lines to temp_input.txt
FOR /F "usebackq tokens=* delims= " %%a IN (input.txt) DO (
    ECHO %%a | FINDSTR /B /V "#" >> temp_input.txt
)

REM Run the program, feed commands from temp_input.txt file and redirect the output to ACTUAL.TXT
java -classpath ..\bin PurrfessorDipsy < temp_input.txt > ACTUAL.TXT

REM Clean up temp_input.txt
del temp_input.txt

REM Compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
IF %ERRORLEVEL% EQU 0 (
    echo Test result: PASSED
    exit /b 0
) ELSE (
    echo Test result: FAILED
    exit /b 1
)
