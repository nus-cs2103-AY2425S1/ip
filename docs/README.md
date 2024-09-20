# Vinegar Chatbot

Welcome to **Vinegar Chatbot**, your personal task manager! Vinegar helps you manage tasks such as todos, deadlines, and events directly from the command line interface. It also includes additional features such as help documentation and sample data loading.

## Getting Started

To get started with Vinegar Chatbot, follow these steps:

1. **Install Java (JDK 11 or later)**.
2. **Clone the repository**:  
   git clone https://github.com/yourusername/vinegar-chatbot.git
3. **Navigate to the project directory**:  
   cd vinegar-chatbot
4. **Run the application** using Gradle:  
   gradle run

## Features

### 1. Add a Task
Vinegar supports three types of tasks: `todo`, `deadline`, and `event`.

- **Todo**: Adds a basic task without any time constraints.
- Command:
 ```
 todo <description>
 ```
- Example:
 ```
 todo Read documentation
 ```

- **Deadline**: Adds a task with a specific due date.
- Command:
 ```
 deadline <description> /by <date>
 ```
- Example:
 ```
 deadline Submit project proposal /by 2023-09-25
 ```

- **Event**: Adds a task that has a specific time range.
- Command:
 ```
 event <description> /at <start> /to <end>
 ```
- Example:
 ```
 event Team meeting /at 2023-09-22 /to 2023-09-23
 ```

### 2. Mark a Task as Done
Mark tasks as completed using the `done` command.

- Command:  

Here’s how you can approach adding a User Guide to your chatbot project in the README.md file, along with enabling GitHub Pages to publish the documentation.

1. Update README.md with a User Guide
   Your README.md should provide users with clear instructions on how to use your chatbot, including all major features. Here’s a sample structure for the User Guide section:

Sample README.md with User Guide
markdown
Copy code
# Vinegar Chatbot

Welcome to **Vinegar Chatbot**, your personal task manager! Vinegar helps you manage tasks such as todos, deadlines, and events directly from the command line interface. It also includes additional features such as help documentation and sample data loading.

## Getting Started

To get started with Vinegar Chatbot, follow these steps:

1. **Install Java (JDK 11 or later)**.
2. **Clone the repository**:  
   git clone https://github.com/yourusername/vinegar-chatbot.git

markdown
Copy code
3. **Navigate to the project directory**:  
   cd vinegar-chatbot

markdown
Copy code
4. **Run the application** using Gradle:  
   gradle run

perl
Copy code

## Features

### 1. Add a Task
Vinegar supports three types of tasks: `todo`, `deadline`, and `event`.

- **Todo**: Adds a basic task without any time constraints.
- Command:
 ```
 todo <description>
 ```
- Example:
 ```
 todo Read documentation
 ```

- **Deadline**: Adds a task with a specific due date.
- Command:
 ```
 deadline <description> /by <date>
 ```
- Example:
 ```
 deadline Submit project proposal /by 2023-09-25
 ```

- **Event**: Adds a task that has a specific time range.
- Command:
 ```
 event <description> /at <start> /to <end>
 ```
- Example:
 ```
 event Team meeting /at 2023-09-22 /to 2023-09-23
 ```

### 2. Mark a Task as Done
Mark tasks as completed using the `done` command.

- Command:  
  mark <task_number>
- Example:  
  mark 1

### 3. Delete a Task
Remove tasks from your list using the `delete` command.

- Command:  
  delete <task_number>
- Example:  
  delete 3

### 4. View All Tasks
List all tasks currently in the task list.

- Command:
  list

### 5. Find Tasks
Search for tasks by keyword using the `find` command.

- Command:
  find <keyword>
- Example:  
  find documentation

### 6. Help
To view the help page, use the `help` command.

- Command:  
  help

### 7. Exit the Chatbot
Exit the chatbot using the `bye` command.

- Command:  
  bye

## First-Time Setup

If you're running Vinegar for the first time, the app will automatically load sample data, including:
- A `Todo`: "Read documentation"
- A `Deadline`: "Submit project proposal"
- An `Event`: "Team meeting"

You can view these tasks using the `list` command.

## GitHub Pages

You can also view the full User Guide at:  
[https://yourusername.github.io/vinegar-chatbot/](https://yourusername.github.io/vinegar-chatbot/)


  


