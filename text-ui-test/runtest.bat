@ECHO OFF

REM create bin directory if it doesn't exist
if not exist "..\bin" mkdir "..\bin"

REM delete output from previous run
if exist "ACTUAL.TXT" del "ACTUAL.TXT"

REM compile each .java file in the src\main\java directory
for %%f in ("C:\Users\Jerry Jian\Desktop\ip\src\main\java\*.java") do (
    javac -cp "C:\Users\Jerry Jian\Desktop\ip\src\main\java" -Xlint:none -d "..\bin" "%%f"
)
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath "C:\Users\Jerry Jian\Desktop\ip\bin" ChatBaby < "input.txt" > "ACTUAL.TXT"

REM compare the output to the expected output
FC "ACTUAL.TXT" "EXPECTED.TXT"
