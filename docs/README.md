# Prince User Guide - A TaskManager cum Servant blended into one!
> "I was homeless. But Prince saved my life, pulled me out of poverty. 
Now I am a CEO of a multi-billion dollar company... All thanks to my loyal servant, Prince." ~ Novak Bojovic

## How do I navigate Prince?

### Adding tasks
Prince allows 3 types of tasks to be added - To Dos, Deadlines, and Events.
The syntax for adding a task is as seen below:
- todo *your task*
- deadline *your task* /by *yyyy-mm-dd xxxx* where *xxxx* is the time in 24hr format
- event *your task* /from *yyyy-mm-dd xxxx* to *yyyy-mm-dd xxxx* where *xxxx* is the time in 24hr format

### Viewing tasks
To view all tasks, simply use the list command as seen below.
- list

### Completing tasks
Use the mark command to mark a task as complete.
- mark *index* (where *index* is the number of your task according to the list)

### Uncompleting tasks
Use the unmark command to mark a task as complete.
- unmark *index* (where *index* is the number of your task according to the list)

### Deleting tasks
Use the delete command to mark a task as complete.
- delete *index* (where *index* is the number of your task according to the list)

### Searching for tasks
Use the find command to search for a task that contains your find query. Do note that the query
can only be **1** word.
- find *something*

Use the findexact command to search for a task that exactly matches your query.
- findexact *your task*
