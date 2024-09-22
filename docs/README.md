# Ip Man User Guide

![](Ui.png)

Ip Man is a chatbot that helps you keep track of your tasks!

## Features

### 1. Listing a task: `list`
```list```

Lists all tasks added

Example of usage:
```list```

### 2. Adding a todo task: `todo`
```todo <task>```

Adds a todo task to the list

Example of usage:
```todo read book```

### 3. Adding a deadline task: `deadline`
```deadline <task> /by <date>```

Adds a deadline task to the list

Example of usage:
```deadline return book /by 2/12/2019 1800```

### 4. Adding an event task: `event`
```event <task> /from <date> /to <date>```

Adds an event task to the list

Example of usage:
```event project meeting /from 2/12/2019 1800 /to 2/12/2019 2000```

### 5. Adding a tag to a task: `tag`
```tag <task number> <tag>```

Adds a tag to a task

Example of usage:
```tag 1 important```

### 6. Removing a tag from a task: `untag`
```untag <task number> <tag>```

Removes a tag from a task

Example of usage:
```untag 1 important```

### 7. Marking a task as done: `mark`
```mark <task number>```

Marks a task as done

Example of usage:
```mark 1```

### 8. Unmarking a task as done: `unmark`
```unmark <task number>```

Unmarks a task as done

Example of usage:
```unmark 1```

### 9. Deleting a task: `delete`
```delete <task number>```

Deletes a task from the list

Example of usage:
```delete 1```

### 10. Finding a task: `find`
```find <keyword>```

Finds a task with the keyword

Example of usage:
```find book```

### 11. Exiting the program: `bye`
```bye```

Exits the program

Example of usage:
```bye```
