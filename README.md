# sentinel.Sentinel Chatbot

sentinel.Sentinel is a personal chatbot inspired by the Duke project. It helps you manage tasks, events, and deadlines efficiently, all from the sentinel.command line. This project is configured to use **JDK 17**.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Features](#features)
- [Usage](#usage)
- [Credits](#credits)

## Prerequisites
Ensure that your system is set up with the following:
- **JDK 17**: sentinel.Sentinel requires JDK 17 to compile and run. Follow the instructions [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk) to configure your project correctly.

## Setup

### 1. Configure the Project to Use JDK 17
1. Open your IDE (e.g., IntelliJ IDEA).
2. Configure the project to use **JDK 17** by following [these steps](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
3. In the same dialog, set the **Project language level** field to the `SDK default` option.

### 2. Run the Program
1. Locate the `src/main/java/sentinel.Sentinel.java` file in your project directory.
2. Right-click on `sentinel.Sentinel.java` and choose `Run sentinel.Sentinel.main()`.
3. If the setup is correct, you should see the following output in your console:

```
Hello from
 _____                                                                                      _____ 
( ___ )                                                                                    ( ___ )
 |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   | 
 |   |                                                                                      |   | 
 |   |   ________  _______   ________   _________  ___  ________   _______   ___            |   | 
 |   |  |\   ____\|\  ___ \ |\   ___  \|\___   ___\\  \|\   ___  \|\  ___ \ |\  \           |   | 
 |   |  \ \  \___|\ \   __/|\ \  \\ \  \|___ \  \_\ \  \ \  \\ \  \ \   __/|\ \  \          |   | 
 |   |   \ \_____  \ \  \_|/_\ \  \\ \  \   \ \  \ \ \  \ \  \\ \  \ \  \_|/_\ \  \         |   | 
 |   |    \|____|\  \ \  \_|\ \ \  \\ \  \   \ \  \ \ \  \ \  \\ \  \ \  \_|\ \ \  \____    |   | 
 |   |      ____\_\  \ \_______\ \__\  \__\   \ \__\ \ \__\ \__\\ \__\ \_______\ \_______\  |   | 
 |   |     |\_________\|_______|\|__| \|__|    \|__|  \|__|\|__| \|__|\|_______\|_______|   |   | 
 |   |     \|_________|                                                                     |   | 
 |   |                                                                                      |   | 
 |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___| 
(_____)                                                                                    (_____)
```

## Features
- **sentinel.task.Task Management**: Add, list, and mark tasks as done.
- **sentinel.task.Event Management**: Schedule events with start and end times.
- **sentinel.task.Deadline Tracking**: Set deadlines.

## Usage
These are some commands sentinel.Sentinel has:
1. `todo <sentinel.task>`
2. `deadline <sentinel.task> /by <date>`
3. `event <event> /from <date> /to <date>`
4. `list`
5. `mark <index>`
6. `bye`

## Credits
sentinel.Sentinel is based on the Duke project template. Special thanks to the original creators and the Java community for their contributions.
