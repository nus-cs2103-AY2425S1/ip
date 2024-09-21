# LutChat Chatbot - User Guide

Welcome to LutChat! This guide will help you get started with LutChat, a simple command-line chatbot that helps you manage and keep track of your tasks effectively.

## Table of Contents

1. [Introduction](#introduction)
2. [Getting Started](#getting-started)
3. [Features](#features)
4. [Commands](#commands)
5. [Examples](#examples)
6. [Saving and Loading](#saving-and-loading)
7. [Exiting the Application](#exiting-the-application)
8. [Troubleshooting](#troubleshooting)

## Introduction

LutChat is a chatbot application that allows users to manage their tasks in a structured way. Users can create simple to-dos, set deadlines, and add events. The application supports adding, deleting, marking tasks as done, and displaying all tasks. It also saves tasks to a file, so you can retrieve them the next time you start LutChat.

## Getting Started

### Prerequisites

- Java Runtime Environment (JRE) 8 or higher

### Running LutChat

1. Download the `lutchat.jar` file from the repository.
2. Open a terminal or command prompt.
3. Navigate to the directory where the `lutchat.jar` file is located.
4. Run the application using the following command:
   ```bash
   java -jar lutchat.jar
   ```

## Features

- **Add Tasks:** Create different types of tasks (`todo`, `deadline`, `event`).
- **List Tasks:** View all tasks currently stored.
- **Mark as Done:** Mark tasks as completed.
- **Delete Tasks:** Remove tasks from the list.
- **Save and Load:** Automatically saves tasks to a file and loads them when the application starts.

## Commands

Below is a list of supported commands in LutChat:

### 1. Add a To-do
Adds a simple to-do task to your list.
- **Format:** `todo <description>`
- **Example:** `todo Read a book`

### 2. Add a Deadline
Adds a task with a specified deadline.
- **Format:** `deadline <description> /by <date>`
- **Example:** `deadline Submit assignment /by 2024-09-30`

### 3. Add an Event
Adds an event that occurs within a specific time frame.
- **Format:** `event <description> /from <start date> /to <end date>`
- **Example:** `event Team meeting /from 2024-09-25 /to 2024-09-26`

### 4. List All Tasks
Displays all tasks in the list with their status (done/not done).
- **Format:** `list`
- **Example Output:**
  ```
  1. [T][ ] Read a book
  2. [D][X] Submit assignment (by: 2024-09-30)
  ```

### 5. Mark a Task as Done
Marks a specified task as completed.
- **Format:** `done <task number>`
- **Example:** `done 1`

### 6. Delete a Task
Removes a task from the list.
- **Format:** `delete <task number>`
- **Example:** `delete 2`

### 7. Exit the Application
Saves the current list of tasks and exits the application.
- **Format:** `bye`

## Saving and Loading

LutChat automatically saves your tasks to a file every time you add, delete, or mark a task as done. When you start LutChat, it will load the saved tasks from the file so you can continue where you left off.

## Exiting the Application

To exit the application, simply type the `bye` command. Your tasks will be saved automatically.

## Troubleshooting

- **Commands Not Working:** Ensure you follow the correct command format as specified above.
- **File Save/Load Errors:** If LutChat fails to save or load tasks, ensure you have proper file permissions in the directory where the application is running.

---

Images:
- https://www.pinterest.com/pin/cute-ghost-icon--754986325039285968/ (Cute Ghost Icon)
- https://www.dreamstime.com/illustration/human-being-fat-blob-person-people-androgynous-cute-cartoon-sticker-label-stick.html (Human Blob Icon)

This guide should help you use LutChat effectively to manage your tasks. If you encounter any issues or have suggestions for improvements, feel free to open an issue in the GitHub repository!
