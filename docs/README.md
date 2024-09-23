# Edith Project

This is a chatbot named after the Marvel fictional character **_Edith_**.
It is a desktop app meant to help you manage your tasks efficiently!

![Picture of Edith Chatbot Application](Ui.png)

## User Guide

### Setting up
1. Ensure that you have Java `17` installed on your computer.
2. Download the latest release of the app [here](https://github.com/sam-theman88/ip/releases).
3. Copy the file to the folder you want to use as your _home folder_ for your Edith chatbot.
4. Open a command terminal, `cd` into the folder containing the `edith.jar` file, and use the command `java -jar edith.jar`.
5. A GUI will shortly appear.
6. Have fun interacting with the chatbot!
7. Refer to the **Features** section below for details of all available commands.

### Features
> ℹ️ Notes about the command format:
> 1. Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
     e.g in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used like this: `todo borrow book`.
> 2. `DATETIME` must always be in the format `day/month/year HHmm`.<br>
     e.g for `deadline DESCRIPTION /by DATETIME`, it should be `deadline return book /by 23/19/2024 1800`.
> 3. `DATE` must always be in the format `day/month/year`.<br>
     e.g for `list DATE`, it should be `list 23/19/2024`.
> 4. Parameters must be in the specified order.<br>
     e.g if the command format specifies `DESCRIPTION from/DATE to/DATE`, users must follow this format strictly.

#### Adding a todo task: `todo` or `t`
- Adds a `todo` task into the task list.
- Format: `todo DESCRIPTION` or `t DESCRIPTION`
- Examples:
    - `todo borow book`
    - `t borow book`

#### Adding a deadline task: `deadline` or `d`
- Adds a `deadline` task into the task list.
- Format: `deadline DESCRIPTION /by DATETIME` or `d DESCRIPTION /by DATETIME`
- Examples:
    - `deadline return book /by 24/9/2024 1800`
    - `d borow book /by 24/9/2024 1800`

#### Adding an event task: `event` or `e`
- Adds an `event` task into the task list.
- Format: `event DESCRIPTION /from DATETIME /to DATETIME` or `e DESCRIPTION /from DATETIME /to DATETIME`
- Examples:
    - `event meeting /from 24/9/2024 0900 /to 24/9/2024 1800`
    - `e meeting /from 24/9/2024 0900 /to 24/9/2024 1800`

#### Listing all tasks in the task list: `list`
- Displays all tasks in the task list.
- Format: `list`

#### Listing all matching tasks by date: `list`
- Displays all tasks in the task list that are either due or starting on a specified date.
- Format: `list DATE`
- Example:
    - `list 29/9/2024` will list deadlines due on 29/9/2024, events ending on 29/9/2024, and events starting on 29/9/2024.

#### Deleting a task: `delete`
- Deletes a specified task from the task list.
- Format: `delete TASK_INDEX`
- Example:
    - `delete 2` will delete the 2nd task in the task list.

#### Marking a task as done: `mark`
- Marks a specified task as done in the task list.
- Format: `mark TASK_INDEX`
- Example:
    - `mark 2` will mark the 2nd task in the task list as done.

#### Marking a task as undone: `unmark`
- Marks a specified task as undone in the task list.
- Format: `unmark TASK_INDEX`
- Example:
    - `unmark 2` will mark the 2nd task in the task list as undone.

#### Listing all matching tasks by name: `find`
- Finds tasks whose descriptions contain the given keyword.
- Format: `find KEYWORD`
    - Only the description is searched.
    - The search is case-insensitive i.e `book` will match `Book`.
    - Partial words will be matched i.e `book` will match `bookstore`.
- Example:
    - `find book` will return a list of all tasks that contain the word **book** in their description.

#### Exiting the app: `bye`
- Exits the program after a 3-second delay.
- Format: `bye`

###  Data management

#### Saving task data
- Edith will automatically save the user's task data after any command that changes the task list.
- There is no need to save manually.

#### Loading task data
- Edith will automatically load the user's task data when the app is first launched, ensuring persistence of task data between sessions of the chatbot app.
- Edith will automatically create a directory and task data file in the _home folder_ if they don't exist. E.g it is a user's first time launching the app.
- The task data file can be located in `[home_folder]/data/edith.txt`.