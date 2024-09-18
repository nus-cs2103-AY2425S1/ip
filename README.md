# D++e

**D++e** is a *homework project* created by ***a student***. It has the following advantage:
- Runs completely on CLI, friendly to users who type fast
- Neat user interface

## Setting up in Intellij
The code is written in Java and you can edit it if you want. Here is a sample code:
```Java
 public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
```
With this code, `Task("hi")` creates a new task.
- [X] It is open-source
- [X] It still has room for improvement
- [ ] It has you involved as a member of project! :sweat_smile:

> To code, or not to code, that is the question --- me

[If you like my project, then click here!](https://github.com/nus-cs2103-AY2425S1/ip)

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/DPlusPlusE.java` file, right-click it, and choose `Run DPlusPlusE.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
