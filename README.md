# kita project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)

2. Open the project into Intellij as follows:
   
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.

3. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.

4. After that, locate the `src/main/java/kita.kita.java` file, right-click it, and choose `Run kita.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   
   ```
   Hello from kita!!
   ```

## Gradle Commands

- **Note:** Gradle automatically saves plugins and dependencies into a global home folder cache `~/.gradle/caches`
  
  - These are all automatically downloaded the first time you run/build/test

- **<u>Run Script</u>**
  
  - ```
    .\gradlew run 
    ```

- **<u>Run Tests</u>**
  
  - ```
    .\gradlew test
    ```

- **<u>Compile to a `.jar` file</u>**
  
  - ```
    .\gradlew clean shadowJar
    ```

- **<u>Clean `build` repo</u>**
  
  - ```
    .\gradlew clean
    ```

- **<u>Style Check </u>**
  
  - Style files are in `config/checkstyle`
  - ```
    .\gradlew checkstyleMain checkstyleTest <name of style file>
    ```

- **<u>Style Format</u>**
  
  - We use `Spotless` for auto-formatting, though it does not follow the CS2103T style exactly
  - ```
    .\gradlew spotlessApply
    ```

- **<u>Run a Full Check</u>**

    - This runs `checkstyle` and unit tests in our case
    - ```
    .\gradlew check
    ```