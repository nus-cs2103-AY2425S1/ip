# JackBean User Guide

![](Ui.png)

JackBean is a task tracker that helps you keep track of *3 different types* of tasks:
- Todos
- Deadlines
- Events

## Available Commands
1. list - to list all tasks and see task numbers
2. todo <description> - to add a todo task
3. deadline <description> /by <date> - to add a deadline task
4. event <description> /from <date> /to <date> - to add an event task
5. mark <task number> - to mark a task as done
6. unmark <task number> - to mark a task as undone
7. bye - to exit the program
8. help - to see this message again
9. delete <task number> - to delete a task
10. find <keyword> - to find tasks that match the keyword
11. maybe some easter eggs in the future? :) (eg. leo, lose to you)

### Adding deadlines

JackBean can help you to keep track of deadlines. To add a deadline, use the following command:
<br>`deadline <description> /by <date> - to add a deadline task`
<br>For example: `deadline submit cs2103t iP /by 2024-09-20`
<br><br> JackBean will respond with:
```
Got it homie! I've added your deadline, LESGOOOOO:
[D][ ] submit cs2103t iP (by: Sep 20 2024)
Homie, you have 1 task(s) in the list now.
```

### Finding tasks

Too many tasks? Don't worry, JackBean can easily search for a specific task for you.
To find a task, use the following command:
<br>`find <keyword> - to find tasks that match the keyword`
<br>For example: `find 2103t`
<br><br> JackBean will respond with:
```
Yo homie!Here are the tasks in your list that match your keyword:
1. [D][ ] submit cs2103t iP (by: Sep 20 2024)
```

## To run JackBean
1. Download the code from [my repo](github.com/bryanjhc/ip).
2. Compile the Codes
3. Run `jackbean.gui.Launcher.main()` or use `gradlew run`
4. Type `help` in the input
5. Use any command from the list of commands given!