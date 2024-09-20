# darkpool user guide

think of me as Deadpool but without the katanas—here to help you slice through your tasks like a pro, with a lot of sarcasm and a touch of chaos. Whether it's a todo, deadline, event, or even something for the far-off future, I’ve got you covered (and entertained). Oh, and don't worry—I'll save your task list. You're welcome.

## Product
<img src="https://github.com/Arnaxx54/ip/blob/master/docs/Ui.png" alt="Alt text" width="500" height="300">

## Features List

### 1. Add Tasks
Four types of tasks, because why limit your chaos to just one?
- **todo**: Basic, no-frills task. Like the "I’ll totally do this tomorrow" type.
- **deadline**: For when you want to feel the pressure of procrastination and a looming date.
- **event**: A task with both a start and end date. Like that meeting you’ll sleep through.
- **after**: Because you want to do it **after** a certain date, when you're "totally free."

**Task Format**: `[Task Type] [Description] (Date, if applicable)`

#### Example Usage:
- **Adding a Todo Task:**
  ```
  todo Conquer the world
  ```
  **Expected Output:**
  ```
  i have dumped this nonsense on the list 
  [T][ ] Conquer the world
  now you are stuck with 1 goddamn tasks
  ```

- **Adding a Deadline Task:**
  ```
  deadline Destroy all evil /by 30-09-2024 23:59
  ```
  **Expected Output:**
  ```
  i have dumped this nonsense on the list 
  [D][ ] Destroy all evil (by:30-09-2024 23:59)
  now you are stuck with 2 goddamn tasks
  ```

- **Adding an Event Task:**
  ```
  event Save the universe /from 10-09-2024 09:00 /to 11-09-2024 18:00
  ```
  **Expected Output:**
  ```
  i have dumped this nonsense on the list 
  [E][ ] Save the universe (from:10-09-2024 09:00 to:11-09-2024 18:00)
  now you are stuck with 3 goddamn tasks
  ```

- **Adding an After Task:**
  ```
  after Chill out /from 01-10-2024 12:30
  ```
  **Expected Output:**
  ```
  i have dumped this nonsense on the list 
  [A][ ] Chill out (from:01-10-2024 12:30)
  now you are stuck with 4 goddamn tasks
  ```

---

### 2. List All Tasks
Oh, you want a list? Sure, let me fetch that for you. No capes needed.

#### Example Usage:
```
list
```
**Expected Output:**
```
1. [T][ ] Conquer the world
2. [D][ ] Destroy all evil (by:30-09-2024 23:59)
3. [E][ ] Save the universe (from:10-09-2024 09:00 to:11-09-2024 18:00)
4. [A][ ] Chill out (from:01-10-2024 12:30)
```

---

### 3. Mark Tasks as Done
Want to check something off? Congrats, you’re actually doing something!

#### Example Usage:
```
mark 2
```
**Expected Output:**
```
why do i have to mark this mess
[D][X] Destroy all evil (by:30-09-2024 23:59)
```

---

### 4. Unmark Tasks
Changed your mind? Well, aren’t we indecisive today...

#### Example Usage:
```
unmark 2
```
**Expected Output:**
```
why do i have to unmark this mess
[D][ ] Destroy all evil (by:30-09-2024 23:59)
```

---

### 5. Delete Tasks
Feel like erasing history? Who am I to judge?

#### Example Usage:
```
delete 1
```
**Expected Output:**
```
why do i have to delete this mess
[T][ ] Conquer the world
```

---

### 6. Find Tasks by Keyword
Looking for something? Of course, you are—because losing track of tasks is your thing.

#### Example Usage:
```
find universe
```
**Expected Output:**
```
fine! here are the matching tasks
2. [E][ ] Save the universe (from:10-09-2024 09:00 to:11-09-2024 18:00)
```
---

### 8. Exit Application
Sick of me already? You’ll be back. Just type `bye`, and I’ll save your tasks for next time.

#### Example Usage:
```
bye
```
**Expected Output:**
```
contact me again and you'll regret it.
```

---

## Exception Handling

You’re going to make mistakes, it’s cool. Luckily for you, I handle errors like a pro—while throwing in some witty remarks because, well, why not?

### Unknown Command
If you go off-script:
```
what???
```

### Invalid Task Number
Attempting to mark, unmark, or delete a task that doesn’t exist:
```
do you know how to count? the task number is out of range
```

### Missing Task Number
Forgetting important details? Tsk tsk.
```
bro wheres the number
```

---

## Summary of Commands

| Command            | Description                                                 |
|--------------------|-------------------------------------------------------------|
| `todo`             | Add a todo task (e.g., `todo Save the world`)               |
| `deadline`         | Add a deadline task (e.g., `deadline task /by date`)        |
| `event`            | Add an event (e.g., `event Party /from date /to date`)      |
| `after`            | Add a task after a date (e.g., `after task /from date`)     |
| `list`             | List all tasks (because you love long lists)                |
| `mark [number]`    | Mark a task as done (e.g., `mark 1`)                        |
| `unmark [number]`  | Unmark a task (e.g., `unmark 1`)                            |
| `delete [number]`  | Delete a task (like it never existed)                       |
| `find [keyword]`   | Find tasks by keyword (because you're Sherlock now)         |
| `bye`              | Exit the app (Don’t worry, I’ll be here when you come back) |

---

darkpool: where your tasks get done (maybe) and you get entertained (definitely). Happy procrastinating—I mean, task managing!