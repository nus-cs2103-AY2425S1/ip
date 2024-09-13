# 🌟 Papadom User Guide 🌟

![Papadom Chatbot UI](Ui.png)

## 📖 Introduction
Papadom is a personal chatbot designed to help you manage your tasks efficiently. Whether it's simple to-dos, deadlines, or events, Papadom keeps track of all your tasks with ease. It's capable of responding to user commands through a simple, intuitive interface. You can interact with Papadom by typing commands, and it will manage your tasks and deadlines for you!

## ✅ Adding todo tasks
You can add a simple todo task without any deadlines.

**Command:** todo [task description]

**Example:** todo buy groceries

**Expected Outcome:**

Got it. I've added this task:

[T][ ] buy groceries

Now you have 1 task in the list.

## 🗓️ Adding deadline tasks

You can add a task that has a deadline.

**Command:** deadline [task description] /by [date] OR [date time]

**Example:** deadline submit project /by 2024-12-10

**Expected Outcome:**

Got it. I've added this task:

[D][ ] submit project (by: Dec 10 2024)

Now you have 2 tasks in the list.


## 📅 Adding Event tasks

You can add an event that starts and ends at specific times.

**Command:** event [task description] /from [start time] /to [end time]

**Example:** event team meeting /from 2024-09-14 14:00 /to 2024-09-14 15:00

**Expected Outcome:**

Got it. I've added this task:

[E][ ] team meeting (from: Sep 14 2024 14:00 to: 15:00)

Now you have 3 tasks in the list.


## 📜 Listing All Tasks

You can list all tasks that have been added so far.

**Command:** list

**Expected Outcome:**

Here are the tasks in your list:
1. [T][ ] buy groceries
2. [D][ ] submit project (by: Dec 10 2024)
3. [E][ ] team meeting (from: Sep 14 2024 14:00 to: 15:00)


## ✔️ Marking a Task as Done

You can mark a specific task as completed.

**Command:** mark [task number]

**Example:** mark 2

**Expected Outcome:**

Nice! I've marked this task as done:

[D][X] submit project (by: Dec 10 2024)


## 🔄 Unmarking a Task

You can mark a previously completed task as incomplete.

**Command:** unmark [task number]

**Example:** unmark 2

**Expected Outcome:**

OK, I've marked this task as not done yet:

[D][ ] submit project (by: Dec 10 2024)

## ❌ Deleting a Task

You can remove a task from your list.

**Command:** delete [task number]

**Example:** delete 1

**Expected Outcome:**

Noted. I've removed this task:

[T][ ] buy groceries

Now you have 2 tasks in the list.


## 🔍 Finding a Task by Keyword

You can search for tasks that contain a specific keyword.

**Command:** find [keyword]

**Example:** find project

**Expected Outcome:**

Here are the matching tasks in your list:

1. [D][ ] submit project (by: Dec 10 2024)

## 👋 Exiting the Application

You can end the session with Papadom and close the app.

**Command:** bye

**Expected Outcome:**

Bye. Hope to see you again soon!

## 🛠️ Troubleshooting
**Command Not Recognized:** Ensure your commands follow the examples exactly, using the proper format.

**Date Format Issues:** Make sure dates follow the format yyyy-mm-dd (e.g., 2024-12-10).