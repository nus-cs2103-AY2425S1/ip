# Blue Chatbot

Blue Chatbot is a Java-based application that helps users manage their tasks through simple text commands. It supports adding, listing, marking, unmarking, deleting, finding tasks, and avoiding duplicate entries.

![Blue Chatbot Logo](docs/Ui.png)
## Setting up in Intellij & Prerequisites

- JDK 17
- IntelliJ IDEA (most recent version)

## Features

- **Add Todo Tasks**: Add tasks without deadlines.
- **Add Deadline Tasks**: Add tasks with deadlines.
- **Add Event Tasks**: Add tasks with start and end times.
- **List Tasks**: Display all tasks.
- **Mark Tasks**: Mark tasks as done.
- **Unmark Tasks**: Unmark tasks as not done.
- **Delete Tasks**: Remove tasks from the list.
- **Find Tasks**: Search for tasks by keyword.
- **Detect Duplicates**: Prevent adding duplicate tasks.


### Setting up in IntelliJ

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first).
2. Open the project into IntelliJ as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 17** as explained [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk). In the same dialog, set the **Project language level** field to the `SDK default` option.
4. Locate the `src/main/java/blue/Blue.java` file, right-click it, and choose `Run Blue.main()`. If the setup is correct, you should see the application running.

## Alternatively, Download the Jar File!

Link to jar file: [[Blue.jar]](https://github.com/waihin26/ip/releases/tag/A-Release)

After Downloading the jar file, run the following command in the terminal 
to execute the program: 
```
java -jar blue.jar
``` 
