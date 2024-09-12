## Gumball

> “Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity-quotes/))

Gumball frees your mind of having to remember things you need to do ~~stopping you from being too lazy~~. It's,

-text-based

-easy to learn

-FAST to use

All you need to do is,

1.download it from _here_.

2.double-click it.

3.add your tasks.

4.let it manage your tasks for you 😸:

Plus its completely __Free__
Features:

- [x] Managing tasks

- [x] Managing deadlines

- [ ] Reminders (coming soon)

Here is a look at the main method:

```
public static void main(String[] args) {
        try {
            Gumball chat = new Gumball();
            chat.start();
        } catch (IOException e) {
            UI.print(e.getMessage());
        } catch (InputErrorException e) {
            UI.print("Error in file please start over.");
        }
    }
```



## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
