# Bob User Guide

Bob is a chat bot for you to manage your tasks.

1. Adding deadlines
- Description: Adds a task with a deadline
- Usage: ```deadline <descrption> /by <yyyy-mm-dd>```
- Example: ```deadline read book /by 2022-12-12```

2. Adding todos
- Description: Adds a todo
- Usage: ```todo <descrption>```
- Example: ```todo read book```

3. Adding events
- Description: Adds an event with start and end dates
- Usage: ```event <descrption> /from <yyyy-mm-dd> /to <yyyy-mm-dd>```
- Example: ```event holiday /from 2022-12-12 /to 2022-12-12```

4. Listing all tasks
- Description: Lists all tasks
- Usage: ```list```

5. Marking a task as done
- Description: Marks a task as done
- Usage: ```mark <task position number>```
- Example: ```mark 2```

6. Marking a task as undone
- Description: Marks a task as undone
- Usage: ```unmark <task position number>```
- Example: ```unmark 2```

7. Deleting a task
- Description: Deletes a task
- Usage: ```delete <task position number>```
- Example: ```delete 2```

8. Find task by keyword
- Description: Finds a task with a word containing the substring
- Usage: ```find <substring>```
- Example: ```find bob```

9. Find task by exact keyword
- Description: Finds a task with a word matching the exact substring
- Usage: ```findexact <substring>```
- Example: ```findexact bob```

10. Exit
- Usage: ```bye```
- Description: Exits the application