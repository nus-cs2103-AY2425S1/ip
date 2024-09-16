# Genesis User Guide


Genesis is an interactive and modern chatbot that is your one-stop solution to create tasks, 
track deadlines and remember events. 

## Startup

1. Ensure you have Java 17 or above installed in your Computer.

2. Download the latest .jar file.

3. Copy the file to the folder you want to use as the home folder for your AddressBook.

4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar addressbook.jar command to run the application.
A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

![Alt Text](/Users/ethangoh/ip/docs/Ui.png)


5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.
Some example commands you can try:

list : Lists all tasks.

todo eat lunch: adds a Task of type "todo" reminidng you to eat lunch

delete 3 : Deletes the 3rd task shown in the task list.

bye : Exits the app.


## Features

### Todo
Adds a "todo" task to your task list, letting you know that you have a task to complete.

Format: `todo TASKNAME`

Examples: 
* `todo eat lunch`
* `todo do homework`

### Deadline
Adds a "deadline" task to your task list, letting you know that you have a certain task to complete by a certain date/time.

Format: `deadline TASKNAME /by DATE(yyyy-mm-dd)`

Examples:
* `deadline do CS2103T homework /by 2024-10-10`
* `deadline submit CS2100 assignment /by 2024-09-29`

### Event
Adds an "event" task to your task list, letting you know that there will be event taking place, specifying its start date and end date.

Format: `event TASKNAME /from DATE(yyyy-mm-dd) /to DATE(yyyy-mm-dd)`

Examples:
* `event japan holiday /from 2024-11-10 /to 2024-11-17`
* `event festival /from 2025-04-10 /to 2024-04-17`

### Find
Finds all tasks that contain the string provided as input.

Format: `find STRING`

* The search is case-sensitive, i.e `find eat` and `find EAT` will yield different results
* Even words that are not full will be matched

Examples:
* `find eat`
* `find homework`

### Mark and Unmark
Mark or unmark your tasks to signify them as done/undone.

Format: `mark INDEX unmark NAME`

Examples:
* `mark 1` - marks the first item in the list
* `unmark 7` - unmarks the seventh item in the list

### List
Lists all tasks in your task list.

Format: list

### Delete
Delete a specific task from your task list.

Format: `delete INDEX`

Examples:
* `delete 3` - deletes the third item in the list
* `delete 8` - deletes the eighth item in the list

## Known Issues
* Inputting the date in any other format exclusing (yyyy-mm-dd) will throw an error. Please input the date in the required format.