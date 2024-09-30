# BRMO Task Management Bot User Guide

Welcome to the BetaRMinusOne Task Management Bot! This guide will help you understand how to use the bot to manage your tasks efficiently.

## Table of Contents

1. Introduction
2. [Getting Started](#getting-started)
3. Commands
    - [List Tasks](#list-tasks)
    - [Add Tasks](#add-tasks)
    - [Mark Tasks](#mark-tasks)
    - [Unmark Tasks](#unmark-tasks)
    - [Delete Tasks](#delete-tasks)
    - [Find Tasks](#find-tasks)
4. [Error Handling](#error-handling)
5. [Exiting the Bot](#exiting-the-bot)

## Introduction

BRMO is a task management bot that helps you keep track of your tasks. You can add, delete, mark, unmark, and find tasks using simple commands.

## Getting Started

To start using BRMO, simply run the JAR file using:

```sh
java -jar brmo.jar
```

## Commands

### List Tasks

To list all tasks in your task list, use the following command:

```sh
list
```

### Add Tasks

You can add different types of tasks: Todo, Deadline, and Event.

#### Add Todo

To add a todo task, use the following command:

```sh
todo <task description>
```

Example:

```sh
todo Read a book
```

#### Add Deadline

To add a deadline task, use the following command:

```sh
deadline <task description> /by <due date>
```

Example:

```sh
deadline Submit assignment /by 20-10-2024
```

#### Add Event

To add an event task, use the following command:

```sh
event <task description> /from <start date> /to <end date>
```

Example:

```sh
event Team project /from 20-10-2024 /to 21-10-2024
```

### Mark Tasks

To mark a task as done, use the following command:

```sh
mark <task number>
```

Example:

```sh
mark 1
```

### Unmark Tasks

To unmark a task as not done, use the following command:

```sh
unmark <task number>
```

Example:

```sh
unmark 1
```

### Delete Tasks

To delete a task from your task list, use the following command:

```sh
delete <task number>
```

Example:

```sh
delete 1
```

### Find Tasks

To find tasks that contain a specific keyword, use the following command:

```sh
find <keyword>
```

Example:

```sh
find book
```

## Error Handling

If you enter an invalid command or the command has incorrect formatting, BRMO will display an error message. Make sure to follow the correct command format as described above.

## Exiting the Bot

To exit the bot, simply type:

```sh
bye
```

BRMO will save your tasks and exit.

---