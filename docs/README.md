# Bee
A chatbot that takes care of your todo list and task tracking!

### Adding todos
- Add a todo with just a task name.
```
todo <task name>
```
you get: `[T][ ] task1`

> Format of date and time:
> `yyyy-MM-dd HHmm (24Hr)` or `yyyy-MM-dd hh:mm am (12Hr)`

### Adding deadlines
- Add a task with a deadline. 
- The due date has to follow the date-time format specified.
```
deadline <task name> /by <time>
```
you get: `[D][ ] task2 (by: 2024-06-22 04:00 pm)`

### Adding events
- Add a task that is an event starting and ending within a specified time frame.
- Both date-times are mandatory.
- Start date-time cannot be earlier than end time.
- Date-times have to follow the date-time format specified.
```
event <task name> /from <time> /to <time>
```
you get: `[E][ ] task3 (from: 2024-06-22 04:00 pm to: 2024-06-22 06:00 pm)`

### Listing all tasks with their indices
- Lists all tasks currently recorded with their index, type and status (completed or not).
- Used to view task indices.
```
list
```

### Check and Uncheck tasks
- Mark tasks as complete or incomplete by index.
- Reflected by 'X' in '[ ]'.
- All three types of tasks: todo, deadline, event can be marked and unmarked.
```
// mark as completed
mark <task index>

// mark as incompleted
unmark <task index>
```
not marked as done: `[E][ ] task3 (from: 2024-06-22 04:00 pm to: 2024-06-22 06:00 pm)`
checked as done: `[E][X] task3 (from: 2024-06-22 04:00 pm to: 2024-06-22 06:00 pm)`

### Deleting tasks
- Deletes a task by index.
- Can be used on all three types of tasks.
```
delete <task index>
```

### Searching for tasks
- Search can be done just using a part of the task name.
- Partial name used needs to be a contiguous substring that matches part of the task nae.
- For example, `find sk1` will find task named ta**sk1**23.
```
find <partial name>
```

## Getting help in the app
type `help` into the chatbot and you will receive the list of commands:

     There are three types of task, you can add them by typing:
         todo <task name>
         deadline <task name> /by <time>
         event <task name> /from <time> /to <time>
     You can view all your tasks and their respective indices by:
         list
     You edit your tasks by:
         mark <task index>
         unmark <task index>
         delete <task index>
     You search your tasks by:
         find <partial name>
    
     Format your time in:
         yyyy-MM-dd HHmm (24Hr)
             or yyyy-MM-dd hh:mm am (12Hr).
         You can replace - with / as well;