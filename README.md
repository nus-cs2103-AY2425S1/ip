# Edith User Guide
![Edith demo](docs/Ui.png)  
Edith is a personal chatbot designed to help you manage your tasks and expenses efficiently. Whether you're looking to keep track of your to-do list, set deadlines, organize events, or manage your expenses, Edith can assist you with simple commands.  
This chatbot was created for my cs2103t individual project!

## Features

### Task Management
Edith allows you to manage your tasks through a variety of commands. You can add, delete, and track tasks, deadlines, and events with ease.

- **List Commands**:  
  `command` - Shows a list of all supported commands.

- **View To-Do List**:  
  `list` - Displays your current to-do list.

- **Add a To-Do Task**:  
  `todo <task name>` - Adds a new task to your to-do list.  
  Example: `todo Buy groceries`

- **Add a Deadline Task**:  
  `deadline <task name> /by <deadline>` - Adds a deadline to a task.  
  Example: `deadline Submit assignment /by 2024-09-20`

- **Add an Event**:  
  `event <task name> /from <event start> /to <event end>` - Adds an event to your task list with start and end times.  
  Example: `event Team meeting /from 2024-09-21 10:00AM /to 2024-09-21 12:00PM`

- **Mark a Task as Complete**:  
  `mark <task number>` - Marks the task at the given index as complete.  
  Example: `mark 2`

- **Unmark a Task**:  
  `unmark <task number>` - Unmarks the task at the given index.  
  Example: `unmark 2`

- **Find Tasks by Keyword**:  
  `find <keywords>` - Searches for tasks that match the given keyword(s).  
  Example: `find meeting`


### Expense Management
Edith also helps you keep track of your expenses, categorize them, and view an overview.

- **View Expense List**:  
  `expense list` - Displays your current list of expenses.

- **View Expense Overview**:  
  `expense overview` - Shows a summary of your expenses, broken down by tags and totals.

- **Add an Expense**:  
  `expense add <expense name> <expense amount>` - Adds an expense with a specified amount.  
  Example: `expense add Coffee 4.50`

- **Delete an Expense**:  
  `expense delete <expense index>` - Deletes the expense at the given index.  
  Example: `expense delete 1`

- **Tag an Expense**:  
  `expense tag <expense index> <tag>` - Tags an expense at the given index with the specified tag.  
  Example: `expense tag 1 food`


## Examples of Commands and Expected Output

### Adding a To-Do Task
Command: `todo Read a book`  
Expected Output: 
`nice! ive added this task:`  
`[T][ ] Read a book`  
`There are currently 1 tasks in your todo list.`  

### Marking a Task as Done
Command: `mark 1`  
Expected Output:
`yay! i've marked this task as done #productive`  
`[T][ ] Read a book`  

### Viewing Expenses
Command: `expense overview`  
Expected Output:
`here is an overview of your expenses`  
`misc: $2.3`  
`transport: $4.2`  
`food: $3.5`  
`total: $10.0`  


## Conclusion
Edith makes it easy to manage both your tasks and expenses with intuitive and simple commands. Just type in your desired action, and Edith will handle the rest!
