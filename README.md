# Miku project

>連れてって いろんな世界を見たい キミと一緒に  - Hatsune Miku

This is a project done by Xingye for a greenfield Java project. It's named after the famous japnese Idol Hatsune Miku. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `miku.Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

```
Hello from
 __  __   __   _   _   _    _
|  \/  | |  | | | / / | |  | |
| |\/| | |  | | |/ /  | |  | |
| |  | | |  | | |\ \  | |__| |
|_|  |_| |__| |_| \_\  \____/
Hello! I'm Miku
What song do you want to listen to today?
```

The Run.class is the launcher:
```Java

/**
 * Executes the program.
 */
public class Run {
   ...
}
```

## User Guide
1. Download the .jar file [<u>here</u>](https://github.com/zhou-colla/ip/releases).
2. **Run** the .jar file | **Alternatively** `java -jar Miku.jar.`
3. Let Miku help you with task management! 💚

## Features
- Arrange tasks for you. Add/Delete/Mark/Unmark the following tasks.
   - Deadline
   - Event
   - Todo
- Search the matching tasks from the stored tasks.

## Upcoming Updates
- [x] ~~Create a better userinterface using javaFX.~~
- [ ] Add musicbox for BGM.
- [ ] Add Miku personality to the UI.

