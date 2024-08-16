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

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Monique < input.txt > ACTUAL.TXT

REM remove trailing spaces from both files
powershell -command "(Get-Content ACTUAL.TXT) | ForEach-Object { $_.TrimEnd() } | Set-Content ACTUAL.TXT"
powershell -command "(Get-Content EXPECTED.TXT) | ForEach-Object { $_.TrimEnd() } | Set-Content EXPECTED.TXT"


REM convert line endings of both files to UNIX format
REM dos2unix ACTUAL.TXT
REM dos2unix EXPECTED.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
