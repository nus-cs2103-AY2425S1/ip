# Max User Guide


![Ui.png](Ui.png)

**Welcome to Max!**  
Max is your personal task manager, designed to help you organize your to-dos, deadlines, and events with ease. This guide will walk you through the available commands and how to use them effectively.

---

## Table of Contents

1. [Adding Tasks](#adding-tasks)
    - [Todo](#todo)
    - [Deadline](#deadline)
    - [Event](#event)
2. [Managing Tasks](#managing-tasks)
    - [Mark as Done](#mark-as-done)
    - [Unmark as Not Done](#unmark-as-not-done)
    - [Delete a Task](#delete-a-task)
    - [List Tasks](#list-tasks)
    - [Find Tasks by Keyword](#find-tasks-by-keyword)
3. [Tags](#tags)
    - [Add a Tag](#add-a-tag)
    - [Search by Tag](#search-by-tag)
    - [Remove a Tag](#remove-a-tag)
4. [Help](#help)

---

## Adding Tasks

### Todo
To add a simple to-do task:
`todo <description>`

**Example:**  
`todo Buy groceries`

### Deadline
To add a task with a deadline:
`deadline <description> /by <date>`

**Example:**  
`deadline Submit project /by 25/12/2024`

### Event
To add an event with a start and end time:

`event <description> /from <start> /to <end>`

**Example:**  
`event Team meeting /from 10:00 /to 12:00`

---

## Managing Tasks

### Mark as Done
To mark a task as done, use the task index:
`mark <index>`

**Example:**  
`mark 2`

### Unmark as Not Done
To mark a task as not done:
`unmark <index>`

**Example:**  
`unmark 2`

### Delete a Task
To delete a task:
`delete <index>`

**Example:**  
`delete 3`

### List Tasks
To list all tasks:
`list`

### Find Tasks by Keyword
To find tasks that contain a specific keyword:
`find <keyword>`

**Example:**  
`find groceries`

---

## Tags

### Add a Tag
To add a tag to a task:
`tag <index> <tag>`

**Example:**  
`tag 1 urgent`

### Search by Tag
To search tasks by a specific tag:
`searchtag <tag>`

**Example:**  
`searchtag urgent`

### Remove a Tag
To remove a tag from a task:
`untag <index> <tag>`

**Example:**  
`untag 1 urgent`

---

## Help

To display this help guide:
`help`

This guide should give you everything you need to use Max effectively. 
If you're ever unsure of a command, just type `help` to get a quick reminder of what Max can do!



