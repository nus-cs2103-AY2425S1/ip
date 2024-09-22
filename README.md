# FRIDAY

This is a project built off of the Duke project template for a greenfield Java project. It's named FRIDAY, after the artifical intelligence in Iron Man. Given below are instructions on how to use it.

## FRIDAY Overview

> “The best way to predict the future is to invent it.” – Alan Kay

FRIDAY is a comprehensive task management tool designed to streamline your productivity. It’s:

- **Text-based** and easy to use
- **Highly customizable** to fit your needs
- **FAST** — saves you time 🏃‍♂️
- **User-friendly** interface

### Key Features

1. - [x] **Task Management**: Organize and track your tasks efficiently.
2. - [ ] **Deadlines Management**: Set and manage deadlines (_coming soon_).
3. - [ ] **Reminders**: Never miss an important task (_coming soon_).

To get started:

- Download it from [here](https://github.com/volleyballkickedme/ip).
- Navigate to the folder that contains the jar file and run it with  `java -jar FRIDAY.jar`.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/FRIDAY.java` file, right-click it, and choose `Run FRIDAY.main()` (if the code editor is showing compile errors, try restarting the IDE).

### Sample Code

Here’s a snippet of the main method for Java programmers:

```java
public class Main {
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}
```
