# Sentinel Chatbot

Sentinel is a personal chatbot inspired by the Duke project. It helps you manage tasks, events, and deadlines efficiently, all from the command line. This project is configured to use **JDK 17**.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Features](#features)
- [Usage](#usage)
- [Credits](#credits)

## Prerequisites
Ensure that your system is set up with the following:
- **JDK 17**: Sentinel requires JDK 17 to compile and run. Follow the instructions [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk) to configure your project correctly.

## Setup

### 1. Configure the Project to Use JDK 17
1. Open your IDE (e.g., IntelliJ IDEA).
2. Configure the project to use **JDK 17** by following [these steps](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
3. In the same dialog, set the **Project language level** field to the `SDK default` option.

### 2. Run the Program
1. Locate the `src/main/java/Sentinel.java` file in your project directory.
2. Right-click on `Sentinel.java` and choose `Run Sentinel.main()`.
3. If the setup is correct, you should see the following output in your console:

```
Hello from
               _____                                                                                      _____\s
              ( ___ )                                                                                    ( ___ )
               |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   |\s
               |   |                                                                                      |   |\s
               |   |   ________  _______   ________   _________  ___  ________   _______   ___            |   |\s
               |   |  |\\   ____\\|\\  ___ \\ |\\   ___  \\|\\___   ___\\\\  \\|\\   ___  \\|\\  ___ \\ |\\  \\           |   |\s
               |   |  \\ \\  \\___|\\ \\   __/|\\ \\  \\\\ \\  \\|___ \\  \\_\\ \\  \\ \\  \\\\ \\  \\ \\   __/|\\ \\  \\          |   |\s
               |   |   \\ \\_____  \\ \\  \\_|/_\\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\ \\  \\\\ \\  \\ \\  \\_|/_\\ \\  \\         |   |\s
               |   |    \\|____|\\  \\ \\  \\_|\\ \\ \\  \\\\ \\  \\   \\ \\  \\ \\ \\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\ \\  \\____    |   |\s
               |   |      ____\\_\\  \\ \\_______\\ \\__\\  \\__\\   \\ \\__\\ \\ \\__\\ \\__\\\\ \\__\\ \\_______\\ \\_______\\  |   |\s
               |   |     |\\_________\\|_______|\\|__| \\|__|    \\|__|  \\|__|\\|__| \\|__|\\|_______\\|_______|   |   |\s
               |   |     \\|_________|                                                                     |   |\s
               |   |                                                                                      |   |\s
               |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___|\s
              (_____)                                                                                    (_____)
```

## Features
- **Task Management**: Add, list, and mark tasks as done.
- **Event Management**: Schedule events with start and end times.
- **Deadline Tracking**: Set deadlines.

## Usage
These are some commands Sentinel has:
1. `todo <task>`
2. `deadline <task> /by <date>`
3. `event <event> /from <date> /to <date>`
4. `list`
5. `mark <index>`
6. `bye`

## Credits
Sentinel is based on the Duke project template. Special thanks to the original creators and the Java community for their contributions.
