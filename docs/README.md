# Strand User Guide

![Representative screenshot of Strand](Ui.png)

Strand is a chatbot which helps users track their tasks.

## Listing all tasks: `list`
Shows all current tasks with their priority, completion status and dates (if relevant).

Format: `list`

Expected output:
```
    1.[T][ ] read book
    2.[D][ ] return book (by: Dec 02 2019, 18:00)
    3.[E][ ] project meeting (from: Dec 02 2020, 18:00 to: Dec 02 3030, 00:00)
    4.[D][ ] do homework (by: Sept 09 9090, 00:00)
```

## Adding a TODO task: `todo`
Adds a task with a description to the list.

Format: `todo <description>`

Example: `todo read book`

Expected output:
```
    (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ ✧ﾟ･: Task added:
      [T][ ] read book
    Now you have 2 tasks in the list.
```

## Adding a DEADLINE task: `deadline`
Adds a task with a description and a deadline to the list.

Format: `deadline <description> /by <deadline>`

Example: `deadline return book /by 2/12/2019 1800`

Expected output:
```
    (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ ✧ﾟ･: Task added:
      [D][ ] return book (by: Dec 02 2019, 18:00)
    Now you have 3 tasks in the list.
```

## Adding an EVENT task: `event`
Adds a task with a description, a start date and an end date to the list.

Format: `event <description> /by <deadline>`

Example: `event project meeting /from 2/12/2020 1800 /to 2/12/3030`

Expected output:
```
    (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ ✧ﾟ･: Task added:
      [E][ ] project meeting (from: Dec 02 2020, 18:00 to: Dec 02 3030, 00:00)
    Now you have 4 tasks in the list.
```

## Deleting a task: `delete`
Deletes a task from the list.

Format: `delete <index>`

Example: `delete 1`

Expected output:
```
    (☞ﾟ∀ﾟ)☞ Task removed:
      [E][ ] project meeting (from: Dec 02 2020, 18:00 to: Dec 02 3030, 00:00)
    Now you have 3 tasks in the list.
```
## Mark a task as done: `mark`
Mark a task as done.

Format: `mark <index>`

Example: `delete 1`

Expected output:
```
    ( ﾟヮﾟ) You finished a task?! Congrats! I've marked this task as done:
      [D][X] return book (by: Dec 02 2019, 18:00)
```

## Mark a task as not done: `unmark`
Mark a task as not done.

Format: `unmark <index>`

Example: `delete 1`

Expected output:
```
    ಠ_ಠ ...OK, I've marked this task as not done yet:
      [D][ ] return book (by: Dec 02 2019, 18:00)
```

## Assign a priority to a task: `mark`
Mark a task with a given priority of `HIGH`, `MEDIUM` or `LOW`.

Format: `mark <index> <priority>`

Example: `mark 1 LOW`

Expected output:
```
    :> I've marked this task with priority LOW:
      [T][ ] read book #PRIORITY:LOW
```

## Exiting program: `bye`
Exit the program.

Format: `bye`

Example: `bye`

Expected output:
```
    Adios. Hope to see you again soon! ヾ(＾ ∇ ＾)
```

## Saving data
The list of tasks is saved in the hard drive after any changes are made to it.

## Command summary

| Command       | Format, Example                                                                                        |
|---------------|--------------------------------------------------------------------------------------------------------|
| **todo**      | todo DESCRIPTION<br/>e.g. todo read book                                                               |
| **deadline**  | deadline DESCRIPTION /by DEADLINE <br/>deadline do homework /by 17/09/2024                             |
| **event**     | event DESCRIPTION /from START_DATE /to END_DATE<br/>event attend party /from 18/09/2024 /to 18/10/2024 |
| **delete**    | delete INDEX<br/>e.g. delete 1                                                                         |
| **mark**      | mark INDEX or mark INDEX PRIORITY<br/>e.g. mark 1; mark 1 high                                         |
| **unmark**    | unmark INDEX<br/>e.g. unmark 1                                                                         |
| **list**      | list                                                                                                   |
| **bye**       | bye                                                                                                    |
