![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/RadieonAjax/ip/gradle.yml?style=flat) ![GitHub Tag](https://img.shields.io/github/v/tag/RadieonAjax/ip)


# Shrimp

> Manage Tasks, Free Your Mind

Shrimp is a fast and lightweight task manager designed to keep you organized without getting in your way. Whether you need to keep track of tasks, manage deadlines, or get reminders, Shrimp has you covered.

```javascript
                   .__          .__               
              _____|  |_________|__| _____ ______ 
             /  ___/  |  \_  __ \  |/     \\____ \
             \___ \|   Y  \  | \/  |  Y Y  \  |_> >
            /____  >___|  /__|  |__|__|_|  /   __/
                 \/     \/               \/|__|    
```

## Why Shrimp?

- **Simple and intuitive interface**: No flashy UI—just type and go.
- **Fast and responsive**: Add and manage tasks without delay.
- **Perfect for Java learners**: Shrimp is an ideal tool for those looking to enhance their Java programming skills.

## How to Use:

1. **Download** the latest release version of Shrimp from [here](https://github.com/RadieonAjax/ip/releases).
2. **Double-click** to launch the application.
3. Start adding tasks by typing commands like `add`, `mark`, `delete`, etc.
4. Manage your tasks and let Shrimp take care of the details!

## Feature Roadmap:
- **Add support for Deadline, Event and Todo** 
- **Add support for `delete` and `clear`**
- **Task persistence across sessions** 
- **Graphical User Interface for easier access**
- **More to come...**

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/shrimp/gui/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). 

Stay tuned for more features to come!
