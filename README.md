# Hoshi - Your Task Assistant

Hoshi is a user-friendly assistant that aids you in keeping track of your tasks so you will remain on top of things!

## Features

1. Add ToDos/Deadlines/Events
2. Mark/Unmark Tasks as Complete/Incomplete
3. Delete Tasks
4. Find Tasks
5. List Tasks
6. Help Window
7. Graphical User Interface

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   - Click `Open`.
   - Select the project directory, and click `OK`.
   - If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. Ensure Gradle is installed
5. After that, run gradle either by CLI `gradle run` or inbuilt Gradle GUI in IntelliJ. Running `gradle build` is optional but recommended

## Running JAR File (Quick Start)

1. Ensure JDK-17.0.12 or equivalent is installed locally
2. Download the latest JAR file [here](https://github.com/ITLimJiaWei/ip/releases)
3. Open a command terminal `cd` into folder the jar file was placed in and use `java -jar hoshi.jar` command to run the application

## Acknowledgements

1. ASCII Art - [Link](https://patorjk.com/software/taag/#p=testall&f=Star%20Wars&t=HOSHI)
2. JavaFX initOwner Method - [Link](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html)
3. JavaFX PauseTransition Method - [Link](https://stackoverflow.com/questions/30543619/how-to-use-pausetransition-method-in-javafx)
4. Arrays copyOfRange Method - [Link](https://www.geeksforgeeks.org/java-util-arrays-copyofrange-java/)