@ECHO OFF

REM Set the path to JavaFX lib
set JAVAFX_LIB="C:\Users\muham\Downloads\openjfx-17.0.12_windows-x64_bin-sdk\javafx-sdk-17.0.12\lib"

REM Create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM Delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM Compile all Java files in the 'rizz.source' package into the bin folder
javac --module-path %JAVAFX_LIB% --add-modules javafx.controls,javafx.fxml -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\rizz\source\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM Run the program, feed commands from input.txt file and redirect the output to ACTUAL.TXT
java --module-path %JAVAFX_LIB% --add-modules javafx.controls,javafx.fxml -classpath ..\bin rizz.source.Launcher < input.txt > ACTUAL.TXT

REM Compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
