@ECHO OFF

REM Create bin directory if it doesn't exist
if not exist "..\bin\" mkdir "..\bin"

REM Delete output from previous run
if exist "ACTUAL2.TXT" del "ACTUAL2.TXT"

REM Compile the code into the bin folder, make sure to handle errors
pushd "..\src\main\java"

REM Compile all .java files in all subdirectories recursively
for /r %%a in (*.java) do (
    javac -cp "." -Xlint:none -d "..\..\..\bin" "%%a"
    IF ERRORLEVEL 1 (
        echo ********** BUILD FAILURE **********
        popd
        exit /b 1
    )
)
popd

REM Run the program, feed commands from input.txt file and redirect the output to ACTUAL.TXT
java -classpath "..\bin" sumode.SumoDE < "input.txt" > "ACTUAL2.TXT"

REM Compare the output to the expected output
FC "ACTUAL2.TXT" "EXPECTED.TXT"
IF ERRORLEVEL 1 (
    echo Output mismatch detected.
    exit /b 1
)
