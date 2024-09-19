# Donna User Guide

---

![screenshot](Ui.png)

Donna is a chatbot that will help you manage your tasks with ease. If you're looking for a reliable assistant to 
manage your to-dos, deadlines, and events, Donna has you covered.

## Contents

---

* [Quick Start](#quick-start)
* [Adding Tasks](#adding-tasks)
  * [Todo](#todo)
  * [Deadline](#deadline)
  * [Event](#event)
* [Removing Tasks `delete`](#removing-tasks-delete)
* [Marking Tasks `mark` / `unmark`](#marking-tasks-mark--unmark)
* [Tagging Tasks `tag`](#tagging-tasks-tag)
* [Searching Tasks `find`](#searching-for-tasks-find)
* [Listing Tasks `list`](#listing-tasks-list)
* [FAQs](#frequently-asked-questions)

## Quick Start

---

1. Ensure that you have Java 17 installed.
2. Download the latest jar file from [here](https://github.com/tanwartushar/ip/releases).
3. Copy the file to the folder you want to use as the home folder.
4. Open the terminal, use `cd` to get into the folder you put the jar file in, and use `java -jar donna.jar` to run
the application.


# Features

---

## Adding Tasks

## ToDo

Simple todo tasks with a description

Command Format: `todo <description>`

Example: `todo read books`

Expected Output:
```
Got it. Your task has been added.
You now have me keeping track of everything, so relax.
  [T][] read books
This is the first task in the list.
```

## Deadline

Tasks that have a description and a deadline.

Command Format: `deadline <description> /by <dateTime>`

Date and Time Format: `dd/mm/yyyy HHmm`

Example: `deadline return books /by 18/09/2024 1600`

Expected Output:
```
Got it. Your task has been added.
  [D][] return books (by: Sep 18 2024, 4:00pm)
You now have 2 tasks in the list.
```

## Event

Tasks that have a description, a start time and an end time.

Command Format: `event <description> /from <dateTime> /to <dateTime>`

Date and Time Format: `dd/mm/yyyy HHmm` 

Example: `event visit library /from 18/09/2024 1500 /to 18/09/2024 1800`

Expected Output:
```
Got it. Your task has been added.
  [E][] visit library (from: Sep 18 2024, 3:00pm to: Sep 18 2024, 6:00pm)
You now have 3 tasks in the list.
```

## Removing Tasks `delete`

---

Removes a task (with the specified task number) from the list of tasks.

Command Format: `delete <taskNumber>`

Example: `delete 1`

## Marking Tasks `mark` / `unmark`

---

### Marking Tasks as done `mark`
Marks a task as done (with the specified task number).

Command Format: `mark <taskNumber>`              

Example: `mark 3` 

### Marking Tasks as not done `unmark`
Marks a task as not done (with the specified task number).

Command Format: `unmark <taskNumber>`

Example: `unmark 2`

## Tagging Tasks `tag`

---

Organises tasks by adding tags.

Command format: `tag <taskNumber> #<tagName>`

Example: `tag 1 #work`

## Searching for Tasks `find`

---

Lists out all the tasks that match a specified search query.

Can also be used to search for tagged tasks.

Command Format: `find <searchQuery>` or `find #<tagName>`

Example: `find books` or `find #work`

## Listing Tasks `list`

---

Lists out all the tasks in the list.

Command Format: `list`

## Frequently Asked Questions

---

**Q: How can I save my tasks?**

&nbsp;&nbsp;&nbsp;&nbsp;A: Tasks are automatically saved as you chat with Donna.

**Q: Where are my tasks saved?**

&nbsp;&nbsp;&nbsp;&nbsp;A: Tasks that are added to the list are stored in a text file titled `donna-tasks.txt` which 
can be found in a folder titled `data`. This folder is automatically created in the same directory as the jar file, 
when Donna is run.
