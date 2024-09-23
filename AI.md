# Use of AI

### 1. Change the batch file: 
Added logic to print "Test result: PASSED" or "Test result: FAILED" based on output comparison.

### Lines added to runtest.bat
```bash
FC ACTUAL.TXT EXPECTED.TXT > NUL

REM check the errorlevel to determine if the files are identical
IF ERRORLEVEL 1 (
    echo ********** FAILED **********
    exit /b 1
) ELSE (
    echo ********** PASSED **********
)

REM End of script
```

### 2. Creating this markdown file