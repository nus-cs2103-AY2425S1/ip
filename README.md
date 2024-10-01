# Garfield

Garfield is a desktop application that allows users to keep track of and manage their tasks through a
chatbot like Graphical User Interface (GUI). Garfield is unique because of its personality, which is 
inspired by the cartoon feline character. Garfield will response with snarky sarcastic remarks, and is
guaranteed to make your day with its humour.

This is built with a project template for a greenfield Java project, named _Duke_.

View the [**User Guide**](https://chuajunyu.github.io/ip/).

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project 
first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Garfield.java` file, right-click it, and choose `Run Garfield.main()` 
(if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see 
something like the below as the output:

```
______________________________________________________________________
Hey. I'm

   _____             __ _      _     _
  / ____|           / _(_)    | |   | |
 | |  __  __ _ _ __| |_ _  ___| | __| |
 | | |_ |/ _` | '__|  _| |/ _ \ |/ _` |
 | |__| | (_| | |  | | | |  __/ | (_| |
  \_____|\__,_|_|  |_| |_|\___|_|\__,_|

Let's get this over with. What do you want?
______________________________________________________________________
```

