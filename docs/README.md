# Bee
A chatbot that takes care of your todo list and task tracking!

### Adding todos
```
todo <task name>
```
you get: [T][ ] task1

> Format of date and time:
> `yyyy-MM-dd HHmm (24Hr)` or `yyyy-MM-dd hh:mm am (12Hr)`

### Adding deadlines
```
deadline <task name> /by <time>
```
you get: [D][ ] task2 (by: 2024-06-22 04:00 pm)

### Adding events
```
event <task name> /from <time> /to <time>
```
you get: [E][ ] task3 (from: 2024-06-22 04:00 pm to: 2024-06-22 06:00 pm)

### Listing all tasks with their indices
`list`

### Check and Uncheck tasks
`mark <task index>`

to uncheck: `unmark <task index>`
```
// not marked as done
[E][ ] task3 (from: 2024-06-22 04:00 pm to: 2024-06-22 06:00 pm)

// checked as done
[E][X] task3 (from: 2024-06-22 04:00 pm to: 2024-06-22 06:00 pm)
```

### Deleting tasks
`delete <task index>`

### Searching for tasks
```
find <partial name>
```
for example, `find sk1` will find task named ta**sk1**23

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