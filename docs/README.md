# Bob User Guide

---

![Screenshot of a window of Bob.](./Ui.png)

Meet **Bob**, the ultimate companion that not only helps you manage tasks, 
but also encourages you to finish them!

With **Bob**, you can:
- Manage different types of tasks (todo, deadline, event, etc.),
- Find tasks based on keywords,
- Tag tasks with different tags _#tags!_
- And more!

See below for the various things that you can do in Bob!

---

## Adding todos

Add a _todo_ task with a short description.

Example: 

```
todo Buy bread
```

Bob will respond with:

```
added: [T][ ] Buy bread
```

---

## Adding deadlines

Add a _deadline_ with a date and time. 
The format for DateTime input is: `DD/MM/YYYY hhmm`

Example: 
```
deadline Finish CS2103T iP /by 26/9 2359
```

Bob will respond with:

```
added: [D][ ] Finish CS2103T iP (by: {26-Sep-2024 2359})
```

---

## Adding events

Add an _event_ with a start and end time. 
The format for DateTime input is: `DD/MM/YYYY hhmm`

Example: 

```
event SDG Hackathon /from 25/9 /to 27/9 2359
```

Bob will respond with:

```
added: [E][ ] SDG Hackathon (from: {25-Sep-2024 0000} to: {27-Sep-2024 2359})
```

---

## Listing tasks

List all the task that you have currently added to your list.

Example:

```
list
```

Bob will respond with:

```
1. [T][ ] Buy bread
2. [D][ ] Finish CS2103T iP (by: {26-Sep-2024 2359})
3. [E][ ] SDG Hackathon (from: {25-Sep-2024 0000} to: {27-Sep-2024 2359})
```

---

## Mark tasks

Mark a task as done. 
The index is the number that you see when you ask Bob to `list` your tasks.

Example:

```
mark 1
```

Bob will respond with:

```
Nice! I've marked this task as done:
[T][X] Buy bread
```

---

## Unmark tasks

The opposite of marking a task as done, mark it as NOT done.

Example:

```
unmark 1
```

Bob will respond with:

```
OK! I've marked this task as not done:
[T][ ] Buy bread
```

---

## Delete tasks

Delete a task from your list.

Example:

```
delete 1
```

Bob will respond with:

```
OK, I've removed this task:
[T][ ] Buy bread
Now you have 2 tasks in the list.
```

---

## Delete ALL tasks

Reset your list by deleting ALL the tasks. Do this at your own risk!

Example:

```
reset
```

Bob will respond with:

```
OK, I've removed all your tasks.
```

---

## Find certain tasks

Find tasks by searching a keyword from their description.

Example:

```
find hackathon
```

Bob will respond with:

```
Here are the matching tasks in your list:
2. [E][ ] SDG Hackathon (from: {25-Sep-2024 0000} to: {27-Sep-2024 2359})
```

---

## Tag tasks

Tag tasks with whatever name you want _#tags!_

Example:

```
tag 1 URGENT
```

Bob will respond with:

```
OK, I've tagged this task with #URGENT:
[D][ ] #URGENT Finish CS2103T iP (by: {26-Sep-2024 2359})
```

---

## Show all tags

Show all tags and the tasks that are tagged.

Example:

```
tag
```

<details>

<summary>Using this list</summary>

1. `[D][ ] #URGENT Finish CS2103T iP (by: {26-Sep-2024 2359})`
2. `[T][ ] #JustAnotherDay Eat dinner`
3. `[T][ ] #JustAnotherDay Wake up`
4. `[E][ ] SDG Hackathon (from: {26-Sep-2024 0000} to: {28-Sep-2024 0000})`
5. `[D][ ] #URGENT Finish CS1101S grading (by: {27-Sep-2024 0000})`
6. `[T][ ] #JustAnotherDay Eat lunch`
7. `[T][ ] #URGENT #JustAnotherDay Relax for a bit`
8. `[T][ ] #URGENT Prepare for midterms`

[Example Data File](./Bob.txt)

</details>

Bob will respond:

```
#URGENT:
  1. [D][ ] #URGENT Finish CS2103T iP (by: {27-Sep-2024 2359})
  5. [D][ ] #URGENT Finish CS1101S grading (by: {27-Sep-2024 0000})
  7. [T][ ] #URGENT #JustAnotherDay Relax for a bit
  8. [T][ ] #URGENT Prepare for midterms
#JustAnotherDay:
  2. [T][ ] #JustAnotherDay Eat dinner
  3. [T][ ] #JustAnotherDay Wake up
  6. [T][ ] #JustAnotherDay Eat lunch
  7. [T][ ] #URGENT #JustAnotherDay Relax for a bit
```

---

## Untag a task

Remove a specific tag from a task, or ALL tags from a task.

Example:

```
untag URGENT 8
```

Bob will respond:

```
OK, I've removed tag #URGENT from this task:
[T][ ] Prepare for midterms
```

#### OR

Example:

```
untag 7
```

Bob will respond:

```
OK, I've removed all tags from this task:
[T][ ] Relax for a bit
```

---

## Exit

Say goodbye to Bob :(

Example:

```
bye
```

Bob will respond:

```
I'll be here if you need me. Catch you later!
```

and exit immediately.

---