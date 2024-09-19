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
1. Configure the project to use **JDK 17** by following [these steps](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
1. In the same dialog, set the **Project language level** field to the `SDK default` option.

### 2. Run the Program
1. Locate the `src/main/java/sentinel/Sentinel.java` file in your project directory.
1. Right-click on `Sentinel.java` and choose `Run Sentinel.main()`.
1. If the setup is correct, you should see the following output in your console:

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
- **Task Management**: Add, delete, list, and mark tasks as done.
- **Event Management**: Schedule events with start and end times.
- **Deadline Tracking**: Set deadlines.

## Usage
These are some commands Sentinel has:
1. `todo <task>`
1. `deadline <task> /by <date>`
1. `event <event> /from <date> /to <date>`
1. `find <keyword>`
1. `list`
1. `mark <index>`
1. `delete <index>`
1. `bye`

### Example usage:
```
event Birthday party /from 2024-09-15T18:00 /to 2024-09-15T21:00
```
This creates an event called "Birthday party" from 6:00 PM to 9:00 PM on September 15, 2024.

Expected Output:
```
Got it. I've added this event: [E][ ] Birthday party (from: 2024-09-15T18:00 to: 2024-09-15T21:00)
```

## Product Screenshot
![Product_Screenshot](Ui.png)

## Credits
Sentinel is based on the Duke project template. Special thanks to the original creators and the Java community for their contributions.
