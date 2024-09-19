# Blitz User Guide
![image](https://github.com/user-attachments/assets/49890732-48ff-4b09-90b8-d676f6faa495)

Blitz is a **desktop app for managing tasks** via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Please use the below hyperlinks for quick access:
- [Command Summary](https://gohsl99.github.io/ip/#command-summary)
- [Adding a todo task: todo](https://gohsl99.github.io/ip/#adding-a-todo-task)
- [Adding a deadline task: deadline](https://gohsl99.github.io/ip/#adding-a-deadline-task)
- [Adding a event task: event](https://gohsl99.github.io/ip/#adding-a-event-task)
- [Listing existing tasks: list](https://gohsl99.github.io/ip/#listing-existing-tasks)
- [Marking a task as done: mark](https://gohsl99.github.io/ip/#marking-a-task-as-done)
- [Unmarking a task to undone: unmark](https://gohsl99.github.io/ip/#unmarking-a-task-to-undone)
- [Finding a task: find](https://gohsl99.github.io/ip/#finding-a-task)
- [Deleting a task: delete](https://gohsl99.github.io/ip/#deleting-a-task)
- [Exiting the application: bye](https://gohsl99.github.io/ip/#exiting-the-application)

## Command Summary
<table>
  <thead>
    <tr>
      <th>Action</th>
      <th>Format</th>
      <th>Example</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>todo</td>
      <td>todo [description]</td>
      <td>todo this is my first todo task!</td>
    </tr>
    <tr>
      <td>deadline</td>
      <td>deadline [description] /by [yyyy-mm-dd hhmm]</td>
      <td>deadline this is my first deadline task! /by 2024-07-19 1200</td>
    </tr>
    <tr>
      <td>event</td>
      <td>event [description] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]</td>
      <td>event this is my first event task! /from 2024-07-19 1200 /to 2024-07-20 1200</td>
    </tr>
    <tr>
      <td>list</td>
      <td>list</td>
      <td>list</td>
    </tr>
    <tr>
      <td>mark</td>
      <td>mark [index]</td>
      <td>mark 1</td>
    </tr>
    <tr>
      <td>unmark</td>
      <td>unmark [index]</td>
      <td>unmark 1</td>
    </tr>
    <tr>
      <td>find</td>
      <td>find [keywords]</td>
      <td>find first</td>
    </tr>
    <tr>
      <td>delete</td>
      <td>delete [index]</td>
      <td>delete 1</td>
    </tr>
    <tr>
      <td>bye</td>
      <td>bye</td>
      <td>bye</td>
    </tr>
  </tbody>
</table>

## Adding a todo task

Adds a todo task to the application.

### Syntax 
`todo [description]`

### Example 
`todo this is my first todo task!`

You should see this message in the application:
```
Got it. I've added this task:
[T][ ] this is my first todo task!
Now you have 1 tasks in the list.
```

## Adding a deadline task

Adds a deadline task to the application.

### Syntax 
`deadline [description] /by [yyyy-mm-dd hhmm]`

### Example 
`deadline this is my first deadline task! /by 2024-07-19 1200`

You should see this message in the application:
```
Got it. I've added this task:
[D][ ] this is my first deadline task! (by: 19 Jul 2024 12:00)
Now you have 1 tasks in the list.
```

## Adding a event task

Adds a event task to the application.

### Syntax
`event [description] /from [yyyy-mm-dd hhmm] /to [yyyy-mm-dd hhmm]`

### Example
`event this is my first event task! /from 2024-07-19 1200 /to 2024-07-20 1200`

You should see this message in the application:
```
Got it. I've added this task:
[E][ ] this is my first event task! (from: 19 Jul 2024 12:00 to：20 Jul 2024 12:00)
Now you have 1 tasks in the list.
```

## Listing existing tasks

Lists all the task that have been added to the application.

### Syntax: 
`list`

### Example 1 
Suppose you have not add any task yet and you run `list`, then you should see this message in the application:
```
There is nothing in the list!
```

### Example 2
Suppose you run `todo this is my first todo task!` before and now you run `list`, then you should see this message in the application:
```
Here are the tasks in your list:
1. [T][ ] this is my first todo task!
```

## Marking a task as done

Marks an existing task as done using the index of the task.

### Syntax
`mark [index]`

### Tips
You should use `list` to check the index of the task before you use `mark`.

### Example
Suppose you run `todo this is my first todo task!` before and now you run `mark 1`, then you should see this message in the application:
```
Nice! I've marked this task as done:
[T][X] this is my first todo task!
```

## Unmarking a task to undone

Unmarks an existing task to undone using the index of the task.

### Syntax
`unmark [index]`

### Tips
You should use `list` to check the index of the task before you use `unmark`.

### Example
Suppose you run `todo this is my first todo task!` before and now you run `unmark 1`, then you should see this message in the application:
```
Ok, I've marked this task as not done yet:
[T][ ] this is my first todo task!
```

## Finding a task

Finds all tasks from existing tasks that matched the given keywords.

### Syntax
`find [keywords]`

### Example 
Suppose you run `todo this is my first todo task!` before and now you run `find first`, then you should see this message in the application:
```
Here are the matching tasks in your list:
1. [T][ ] this is my first todo task!
```

## Deleting a task

Deletes an existing task using the index of the task.

### Syntax
`delete [index]`

### Tips
You should use `list` to check the index of the task before you use `delete`.

### Example
Suppose you run `todo this is my first todo task!` before and now you run `delete 1`, then you should see this message in the application:
```
Noted. I've removed this task:
[T][ ] this is my first todo task!
```

## Exiting the application

Exits the application.

### Syntax
`bye`

### Example
`bye`<br/>
You should see the application has exited.
