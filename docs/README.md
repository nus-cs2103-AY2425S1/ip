# Bee
A chatbot that takes care of your todo list and task tracking!

### Adding todos
`todo <task name>`

### Adding deadlines
`deadline <task name> /by <time>`

### Adding events
`event <task name> /from <time> /to <time>`

### Listing all tasks with their indices
`list`

### Check and Uncheck tasks
`mark <task index>`

to uncheck: `unmark <task index>`

### Deleting tasks
`delete <task index>`

### Searching for tasks
`find <partial name>`

#### Format of date and time
`yyyy-MM-dd HHmm (24Hr)`
`yyyy-MM-dd hh:mm am (12Hr)`

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