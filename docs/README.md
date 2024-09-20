# Wenjie Bot User Guide

![UI.png](UI.png)

Wenjie bot is a useful bot that helps you with your everyday tasks, ensuring you never forget to do certain work, 
all the while giving you a visually entertaining GUI so that you will never get bored!

## Adding ToDo Task
Add a todo task to the bot's list of task that isn't time restricted

**__Command__**: `todo <task>`

- Example Input; `todo Go Home`
- Example Output:
  ```
  Nya~~ I've added this task:
  [T][ ] Go Home
  Now you have 3 tasks in the list babe.
  ```

## Adding Event Task
Add an Event task to the bot's list of task that has a start and end time denoted by the "from" and "to" keywords

Uses the date time format of dd/mm/yyyy HHmm

**__Command__**: `event <task> /from <dd/mm/yyyy HHmm> /to <dd/mm/yyyy HHmm>`

- Example Input: `event celebrate birthday /from 2/12/2019 1800 /to 2/12/2019 2000`
- Example Output: 
  ```
  Nya~~ I've added this task:
  [E][ ] celebrate birthday (from: Mon, 02 Dec 2019 18:00 to: Mon, 02 Dec 2019 20:00)
  Now you have 4 tasks in the list babe.
  ```


## Adding Deadline Task

Add a Deadline task to the bot's list of task that has a start and end time denoted by the "by" keywords

Uses the date time format of dd/mm/yyyy HHmm

**__Command__**: `deadline <task> /by <dd/mm/yyyy HHmm>`

- Example Input: `deadline return book /by 2/12/2019 1800`

- Example Output:
  ```
    Nya~~ I've added this task:
    [D][ ] return book (by: Mon, 02 Dec 2019 18:00)
    Now you have 5 tasks in the list babe.
  ```

## List
You can display the lists of tasks to easily view what tasks you have already added to the bot
**__Command__**: `list`

- Example Input: `list`

- Example Output:
  ```
  1. [E][ ] celebrate birthday   (from: Mon, 02 Dec 2019 18:00 to: Mon, 02 Dec 2019 20:00)
  2. [D][ ] return book (by: Thu, 04 Apr 2024 17:00)
  3. [T][ ] Go Home
  4. [E][ ] celebrate birthday (from: Mon, 02 Dec 2019 18:00 to: Mon, 02 Dec 2019 20:00)
  5. [D][ ] return book (by: Mon, 02 Dec 2019 18:00)

  ```

## Mark A Task
You can mark a task as done by using the Mark Command on the corresponding tasks in the list

**__Command__**: `mark <task index>`

- Example Input: `mark 1`
- Example Output:
  ```
  UWU! I've marked this task as done:
  [E][X] celebrate birthday   (from: Mon, 02 Dec 2019 18:00 to: Mon, 02 Dec 2019 20:00)

  ```

## Unmark A Task
You can do the opposite and mark a task as not done by using the Unmark Command on the corresponding tasks in the list

**__Command__**: `unmark <task index>`

- Example Input: `unmark 1`
- Example Output:
  ```
  Yes master~~, I've marked this task as not done yet:
  [E][ ] celebrate birthday   (from: Mon, 02 Dec 2019 18:00 to: Mon, 02 Dec 2019 20:00)

  ```

## Delete a Task
If you want to remove a task from the list, you can use the Delete command to do so by using specifiying the 
corresponding task number

**__Command__**: `delete <task index>`

- Example Input: `delete 1`
- Example Output:
  ```
  Noted my dearest master. I've removed this task:
  [E][ ] celebrate birthday   (from: Mon, 02 Dec 2019 18:00 to: Mon, 02 Dec 2019 20:00)
  Now you have 4 tasks in the list. uwu~ rawr xd

  ```
  
## Snooze your tasks
If you wish to change the times previously set for certain tasks, you can use this command to easily do so by
inputting the new updated timing depending on the type of task

For Event tasks:

**__Command__**: `snooze <task no> /from <dd/mm/yyyy HHmm> /to <dd/mm/yyyy HHmm>`

For Deadline tasks:

**__Command__**: `snooze <task no> /by <dd/mm/yyyy HHmm>`

- Example Input: `snooze 1 /by 4/5/2021 1300`
- Example Output:
  ```
  Nyess master~~, I've snoozed this task:
  [D][ ] return book (by: Tue, 04 May 2021 13:00)

  ```

## Quit the Bot
After you are done and wish to quit the bot, and save the data into your hard drive, so that you
may see the same tasks from before when opening the bot again, you can use this command to quit

**__Command__**: `bye`

- Example Input: `bye`
- Example Output:
  ```
  Paiseh bro I zao liao, see you around ah bro.

  ```
  

