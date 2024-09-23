@echo off

REM create bin directory if it doesn't exist
if not exist ..\bin (
    mkdir ..\bin
)

REM delete output from previous run
if exist ACTUAL.TXT (
    del ACTUAL.TXT
)

REM delete fridayTaskList file if it exists in text-ui-testing/data directory
if exist .\data\fridayTaskList.txt (
    del .\data\fridayTaskList.txt
)

REM compile the code into the bin folder, terminates if error occurred
for /r ..\src\main\java %%f in (*.java) do (
    javac -cp ..\src\main\java -Xlint:none -d ..\bin "%%f"
    if errorlevel 1 (
        echo ********** BUILD FAILURE **********
        exit /b 1
    )
)

REM run the program, feed commands from input.txt file and redirect the output to ACTUAL.TXT
java -classpath ..\bin app.Launcher < input.txt > ACTUAL.TXT

REM compare the output to the expected output
fc ACTUAL.TXT EXPECTED.TXT > nul
if errorlevel 1 (
    echo Test result: FAILED
    exit /b 1
) else (
    echo Test result: PASSED
    exit /b 0
)