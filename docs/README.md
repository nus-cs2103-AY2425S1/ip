# Gray User Guide

![An example image of the UI](Ui.png)

## Chatbot Gray 👋

```java
public class Gray {
   public static void main(String[] args) {
      System.out.println("Hello! I am Chatbot Gray!);
   }  
}
```

> Hello! I am Chatbot Gray!

## Features
1. Add tasks
2. Delete tasks
3. List tasks
4. Save tasks
5. Mark tasks
6. Search tasks

## Types of tasks supported
- `Todo`
- `Deadline`
- `Event`

## User commands
1. `bye`: ends the conversation and exit from the application.
2. `list`: list all tasks.
3. `todo <description>`: add a todo task with the specified description.
4. `deadline <description> /by <date>`: add a deadline task with the specified description and deadline.
5. `event <description> /from <start date> /to <end date>`: add an event task with the specified description, start date and end date.
6. `(mark|unmark) <index>`: marks or unmarks a task with the specified index (in the task list) as completed.
7. `delete <index>`: delete a task with the specified index (in the task list)
8. `find <query>`: search and list tasks with the specified query contained in description.
9. `help`: display the help menu.

## Example use cases

### Adding deadlines

#### Example:
```
deadline return book /by 2024-06-06 1230
```

#### Description
Adds a deadline to "return book" by 6th June 2024 12:30PM.

#### Output (on success)
```
Got it. I've added this task:
    [D][ ] return book
Now you have <n> tasks in the list.
```

### Marking tasks

#### Given some tasks
```
1. [T][ ] read book
2. [D][ ] return book (by: Jun 06 2024 0000)
3. [E][ ] project meeting (from: Jun 06 2024 0000 to: Jun 06 2024 0000)
4. [T][ ] join sports club
5. [T][ ] borrow book
```

#### Example
```
mark 2
```

#### Description
Marks the 2nd task in the list

#### Output (on success)
```
Nice! I've marked this task as done
    [D][X] return book (by: Jun 06 2024 0000)
```

### Searching tasks

#### Example
```
find book
```

#### Description
Search tasks that has description that contains the string query (in this case, "book")

#### Output (on success)
```
Here are the matching tasks in your list:
    1. [T][ ] read book
    2. [D][ ] return book (by: Jun 06 2024 0000)
    3. [T][ ] borrow book
    4. [D][ ] return book (by: May 05 2024 1200)
```

#### Output (on failure)
```
No matches found
```

## Credits
Avatar images are from Canva.