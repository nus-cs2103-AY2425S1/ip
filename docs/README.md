# Dash

Dash frees your mind of having to remember things you need to do. It's,
- text-based
- easy to learn
- ~~FAST~~ *SUPER FAST* to use

All you need to do is,

1. download it from [here](https://github.com/lim-jt/ip).
2. double-click it.
3. add your tasks.
4. let it manage your tasks for you 😉

And it is **FREE**!

## Features
> **Notes about the command format:**
> - Words in **UPPER_CASE** are the parameters to be supplied by the user.
*e.g. in ```add n/NAME```, ```NAME``` is a parameter which can be used as add ```n/John Doe```.*
> - If an invalid command was entered, Dash will alert the user with a response
*e.g. ```Error: Index is out of bounds```*

### Listing all tasks ```list``` / ```l```
Displays a list of all tasks.

Format: ```list``` / ```l```

### Adding a To-Do task: ```todo``` / ```t```
Adds a to-do task to the list.

Format: ```todo TO_DO```

Example: ```todo homework```
### Adding an Event task: ```event``` / ```e```
Adds a event task to the list.

Format: ```event EVENT /from DD-MM-YYYY HHmm/to DD-MM-YYYY HHmm```

Example: ```event exam /from 11-12-2024 1100/to 11-12-2024 1200```
- Date must follow the DD-MM-YYYY HHmm format.
- Time should be inputted in 24 hours format.
- Do note the missing whitespace before /to.
### Adding a Deadline task: ```deadline``` / ```d```
Adds a deadline task to the list.

Format: ```deadline DEADLINE /by DD-MM-YYYY HHmm```

Example: ```deadline return book /by 10-11-2024 2359```

- Date must follow the DD-MM-YYYY HHmm format.
- Time should be inputted in 24 hours format.

### Marking a task as complete: ```mark``` / ```m```
Marks a task as complete.

Format: ```mark INDEX```
- Index refers to index numbers in the task list.

### Marking a task as incomplete: ```unmark``` / ```um```
Marks a task as incomplete.

Format: ```unmark INDEX```
- Index refers to index numbers in the task list.

### Finding a task: ```find``` / ```f```
Finds all tasks that contains the keyword.

Format: ```find KEYWORD```

### Deleting a task: ```delete``` / ```del```
Deletes a task from the list.

Format: ```delete INDEX```
- Index refers to index numbers in the task list.

### Exiting the program: ```bye``` / ```b```
Exits the program.

Format: ```bye```