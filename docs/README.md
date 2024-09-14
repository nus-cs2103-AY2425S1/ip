# Infinity Bot User Guide

![](https://infinitytwo.github.io/ip/Ui.png)

The Infinity Bot is a dummy bot with basic features. It can keep track of Todos, Deadlines, Tasks with a special
feature of mass operations! How cool.

### Section 1: Definitions
### Section 2: Commands
### Section 3: Features

# Section 1: Definitions
```<>``` is used to denote blanks to be filled by the user's content. Follow the instructions inside as needed.

```•``` Supported command

```••``` Supported command with Mass Operations

```Mass Operations``` Users can run multiple operations of the same command with the delimiter ```&&```

```->``` Description of a command

# Section 2: Commands
## Commands Available (/help)

        • bye
            -> Shutdown the bot
        •• deadline <task> /by <date in "DD/MM/YYYY HHMM">
            -> Add a Deadline task
        •• delete <index of task>
            -> Delete a task
        •• event <task> /from <period> /to <period>
            -> Add an Event task
        •• find <keyword>
            -> Finds all tasks and shows them with the keyword given
        • help
            -> Show all available commands
        • list
            ->  all tasks
        •• mark <index of task>
            -> Marks a task as done
        • save
            -> Saves tasklist manually
        •• todo <task>
            -> Add a Todo task


# Section 3: Features

### ```deadline``` 
A task with a deadline with an actual Date.

Example: ```deadline CS2103T iP Submission /by 1/1/2025 2359```

### ```event``` 
A task with a user defined start (/from) and end (/to) period.

Example: ```event CS2103T Finals /from 26th Nov 1700 /to 26th Nov 1900```

### ```todo```
A task that is to be done and has no time involvement.

Example: ```todo CS2103T iP```

### Mass Operations

Run multiple operations into one line, though only on the same command. Use ```&&``` to delimit each operation.

Example: ```find CS2103T && CS2101 && Midterms```