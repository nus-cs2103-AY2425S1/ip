# Max User Guide


![Ui.png](Ui.png)

**Welcome to Max!**  
Max is your personal task manager, designed to help you organize your to-dos, deadlines, and events with ease.
Whether you're managing simple to-do tasks, tracking deadlines, or scheduling important events of the day, Max is here to streamline your workflow.

With Max, you can easily add tasks, categorize them by urgency or type, and stay on track with reminders for dates for deadlines and times for events. 
The app is built to help you efficiently manage your day-to-day activities, so you never miss a task, deadline, or event again.


This guide will walk you through the available commands and how to use them effectively.

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
You can create 3 types of tasks: straightforward to-dos, deadline tasks with due dates, and events that have a start and end time.

### Todo
To add a simple to-do task:
`todo <description>`

**Example:**  
`todo Buy groceries`

`todo Do laundry`

### Deadline
To add a task with a deadline, specify the due date using the `/by` flag:
`deadline <description> /by <date>`

**Example:**  
`deadline Submit project /by 25/12/2024`

`deadline Fill out visa application /by 13/10/2025`

### Event
To add an event with a start and end time, specify the start and end times using `/from` and `/to`:

`event <description> /from <start> /to <end>`

**Example:**  
`event Team Meeting /from 10:00 /to 12:00`

`event Final Examination /from 16:00 /to 18:00`


---

## Managing Tasks
Once tasks are added, you can add effectively manage them. 

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

This will display a full overview of all tasks, their statuses, and any associated details.

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
`tag 1 urgent` (Adds the tag "urgent" to the first task)

`tag 3 work` (Adds the tag "work" to the third task)

### Search by Tag
To search tasks by a specific tag:
`searchtag <tag>`

**Example:**  
`searchtag urgent` (Finds all tasks tagged with "urgent")


### Remove a Tag
To remove a tag from a task:
`untag <index> <tag>`

**Example:**  
`untag 1 urgent` (Removes the "urgent" tag from the first task)

---

## Help

To display this help guide:
`help`

This guide should give you everything you need to use Max effectively. 
If you're ever unsure of a command, just type `help` to get a quick reminder of what Max can do!

---

## Tips

- **Leverage Tags Effectively:** Tags are a powerful tool for organizing your tasks, allowing you to easily sort and prioritize them. For instance, you can use tags such as "important," "work," or "personal" to categorize your tasks.
- **Consistently Review and Mark Tasks as Completed:** Keep track of your progress by regularly checking your tasks and marking them as "done" once they've been finished.
- **Take Advantage of the Search Feature:** Max's search functionality allows you to quickly find tasks by keywords or tags, simplifying the process of managing large task lists.
- **Breakdown Large Tasks:** If a task seems overwhelming, break it into smaller, manageable subtasks. For example, instead of a single task like complete project, create individual subtasks like write introduction, design layout, and review final draft.
---

Acknowledgements:

ChatGPT was used for code completion, test case improvement, javadocs, and ensuring correct grammar. 

