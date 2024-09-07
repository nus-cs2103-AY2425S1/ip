# Timo project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Timo_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Timo.java` file, right-click it. If the setup is correct, you should see a GUI

There are 3 main commands that you can run:

- todo `<task>`
- deadline `<task>` /by `<time>`
- event `<task>` /from `<time>` /to `<time>`

Other commands include:
1. mark `<number>` (to mark task as done)
1. unmark `<number>` (to unmark task)
1. list (show current list of tasks)
1. delete `<number>` (to delete the task)
1. bye (to say bye and save the tasks into a file in your drive)

Hope this GUI helps you in managing your tasks more effectively!
