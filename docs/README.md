# OuiOuiBaguette

**OuiOuiBaguette** is a chatbot designed to help you manage and track various tasks. With an intuitive command-line interface and a friendly French persona, OuiOuiBaguette allows you to effortlessly handle tasks, deadlines, events, and more.

![Screenshot of OuiOuiBaguette showing the list, todo, and mark commands](Ui.png)

### Example Commands
Here are a few examples to get started:
- `list` : Lists all tasks.
- `todo <task>` : Adds a new To-Do item.
- `bye` : Exits the program.


## Features

### 1. Managing ToDos, Events, and Deadlines
OuiOuiBaguette helps you manage three types of tasks:
- **ToDos**: Tasks without any date/time attached to it.
- **Deadlines**: Tasks that need to be done before a specific date.
- **Events**: Tasks that start at a specific date and ends at a specific date.

_Note: Dates must follow the format YYYY-MM-DD._

#### Commands:
- **Add ToDo**:  
  ```
  todo <description>
  ```  
  Example:  
  ```
  todo Visit new theme park
  ```
- **Add Deadline**:  
  ```
  deadline <description> /by <date>
  ```  
  Example:  
  ```
  deadline Submit report /by 2024-11-10
  ```
- **Add Event**:  
  ```
  event <description> /from <start_date> /to <end_date>
  ```  
  Example:  
  ```
  event Team meeting /from 2024-02-10 /to 2024-02-12
  ```

### 2. Marking Tasks as Done/Not Done
You can mark tasks as completed or revert them back to unfinished status.

_Note: Index is 1-based._

#### Commands:
- **Mark Task as Done**:  
  ```
  mark <task_number>
  ```  
  Example:  
  ```
  mark 2
  ```
- **Unmark Task**:  
  ```
  unmark <task_number>
  ```  
  Example:  
  ```
  unmark 2
  ```

### 3. Deleting Tasks
Delete tasks from your list when they are no longer needed.

_Note: Index is 1-based._

#### Command:
- **Delete Task**:  
  ```
  delete <task_number>
  ```  
  Example:  
  ```
  delete 3
  ```

### 4. Finding Tasks
Search for tasks by description using a keyword.

#### Command:
- **Find Task**:  
  ```
  find <keyword>
  ```  
  Example:  
  ```
  find book
  ```

### 5. Listing All Tasks
View a list of all tasks in your current list.

#### Command:
- **List Tasks**:  
  ```
  list
  ```

### 6. Exiting the Chatbot
Exit the chatbot application. The GUI will close in 3 seconds.

#### Command:
- **Exit**:  
  ```
  bye
  ```


## Command Summary

| Action            | Format                                        |
| ----------------- | --------------------------------------------- |
| Add ToDo          | `todo <description>`                          |
| Add Deadline      | `deadline <description> /by <date>`           |
| Add Event         | `event <description> /from <date> /to <date>` |
| Mark as Done      | `mark <task_number>`                          |
| Unmark            | `unmark <task_number>`                        |
| Delete Task       | `delete <task_number>`                        |
| Find Task         | `find <keyword>`                              |
| List All Tasks    | `list`                                        |
| Exit              | `bye`                                         |


## FAQ
- **Q: How do I save my tasks?**  
  A: All your tasks are saved onto your disk automatically. No manual saving is required.

- **Q: What happens if I accidentally close the application?**  
  A: Your tasks are saved onto your disk automatically and will be available the next time you open the application.

- **Q: Do I need to close my window when exiting?**  
A: The window will close automatically in 3 seconds after entering the command `bye`.


## Acknowledgements
Used ChatGPT for improving documentation, user guide, and code quality.

FrenchPerson.png by [Freepik](https://www.freepik.com/free-photo/handsome-man-eating-french-baguettes_14923072.htm#fromView=search&page=1&position=2&uuid=fafbe9e1-9c04-4564-929d-c5827f82ff19).
