# Bro Bot User Guide

![Bro Bot UI](./UI.png)

Bro Bot is your personal task manager designed to help you organize and keep track of your tasks effortlessly. Whether you need to jot down simple todos, set deadlines, or plan events spanning multiple days, Bro Bot has you covered. With an intuitive command-line interface, managing your tasks has never been easier.

## Features

### Adding Deadlines

Bro Bot allows you to add tasks with specific deadlines, ensuring you never miss an important deadline again.

#### **Action and Outcome:**
- **Action:** Add a new task with a deadline.
- **Outcome:** The task is added to your task list with the specified deadline, formatted for easy readability.

#### **Usage Examples:**
  ```deadline Submit assignment /by 25/12/2023 2359```


### Adding Todo Tasks

Easily add simple tasks that don't have a specific deadline.

#### **Action and Outcome:**
- **Action:** Add a new todo task.
- **Outcome:** The task is added to your task list and is initially marked as not completed.

#### **Usage Example:**
```todo read a book```

### Adding Event Tasks

Plan events that span multiple days with clear start and end times.

#### **Action and Outcome:**
- **Action:** Add a new event task with a start and end time.
- **Outcome:** The event is added to your task list with the specified duration, formatted for clarity.

#### **Usage Example:**
```event Company Retreat /from 01/01/2024 0900 /to 03/01/2024 1700```


### Listing All Tasks

View all the tasks you've added, along with their statuses and details.

#### **Action and Outcome:**
- **Action:** List all current tasks.
- **Outcome:** Displays all tasks with their type, completion status, and relevant details.

#### **Usage Example:**
```list```

### Marking Tasks as Completed

Indicate that a task has been completed.

#### **Action and Outcome:**
- **Action:** Mark a specific task as completed.
- **Outcome:** The task's status is updated to completed.

#### **Usage Example:**
```mark 1```

### Unmarking Completed Tasks

Revert a task's status from completed to not completed.

#### **Action and Outcome:**
- **Action:** Unmark a specific task.
- **Outcome:** The task's status is updated to not completed.

#### **Usage Example:**
```unmark 1```

### Finding Tasks

Search for tasks that contain specific keywords.

#### **Action and Outcome:**
- **Action:** Find tasks matching a given keyword.
- **Outcome:** Displays all tasks that contain the keyword in their description.

#### **Usage Example:**
```find assignment```

### Deleting Tasks

Remove tasks that are no longer needed.

#### **Action and Outcome:**
- **Action:** Delete a specific task from the list.
- **Outcome:** The task is removed, and the task list is updated accordingly.

#### **Usage Example:**
```delete 3```

### Exiting Bro Bot

Terminate the application gracefully.

#### **Action and Outcome:**
- **Action:** Exit the Bro Bot application.
- **Outcome:** The application shuts down, saving any necessary data.

#### **Usage Example:**
```bye```

## Getting Started

Follow these instructions to get a copy of Bro Bot up and running on your local machine for development and testing purposes.

### Prerequisites

- **Java 11 or higher:** Ensure that you have Java installed on your machine. You can download it from [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

### Installation
To install follow the steps below
1. Clone this repository
2. Navigate to the project directory using ```cd bro-Bot```
3. Build using ```./gradlew build```
4. Run using ```java -jar build/libs/bro-bot.jar```
