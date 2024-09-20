User Guide for King Chatbot
Features
:information_source: Notes about the command format:

Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in add n/NAME, NAME is a parameter which can be used as add n/John Doe.
Items in square brackets are optional.
e.g. n/NAME [t/TAG] can be used as n/John Doe t/friend or as n/John Doe.
Items with …​ after them can be used multiple times including zero times.
e.g. [t/TAG]…​ can be used as (i.e. 0 times), t/friend, t/friend t/family, etc.
Parameters can be in any order.
e.g. if the command specifies n/NAME p/PHONE_NUMBER, p/PHONE_NUMBER n/NAME is also acceptable.
Extraneous parameters for commands that do not take in parameters (such as help, list, bye, and clear) will be ignored.
e.g. if the command specifies help 123, it will be interpreted as help.
Commands
Viewing help: help
Shows a message explaining how to access the help page.

Format: help

Listing all tasks: list
Shows a list of all tasks in the task manager.

Format: list

Adding tasks: todo, deadline, event
Adds a task to the task manager.

Format:
todo [TASK_DESCRIPTION]
deadline [TASK_DESCRIPTION] /by [DATE_TIME]
event [TASK_DESCRIPTION] /at [DATE_TIME]
Marking tasks: mark
Marks a specified task as completed.

Format: mark INDEX

Example: mark 1 marks the first task in the list as completed.
Unmarking tasks: unmark
Unmarks a specified task as not completed.

Format: unmark INDEX

Example: unmark 1 unmarks the first task in the list.
Finding tasks: find
Finds tasks whose descriptions contain any of the given keywords.

Format: find KEYWORD [MORE_KEYWORDS]

Example: find meeting returns all tasks that contain "meeting".
Deleting tasks: delete
Deletes a specified task from the task manager.

Format: delete INDEX

Example: delete 2 deletes the second task in the list.
Reminders: remind
Shows reminders for tasks that are due soon.

Format: remind [DAYS]

Example: remind 3 shows tasks due in the next 3 days.
Exiting the program: bye
Exits the program.

Format: bye

Saving the Data
Task data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

Editing the Data File
Task data is saved automatically as a JSON file located at [JAR file location]/data/tasks.json. Advanced users can update data directly by editing that data file.

:exclamation: Caution: If changes to the data file make its format invalid, the program will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.