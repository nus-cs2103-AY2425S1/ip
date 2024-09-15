# Taskon User Guide

<p align="center">
  <img src="Ui.png" width="600">
</p>

## Meet Taskon 🎉
Welcome to Taskon, your **_ultimate_** task management companion!
Whether you're
- juggling deadlines
- tracking events
- keeping your to-dos organized,
Taskon has got you **covered**. Let's dive into how you can make the most of it!

### ✍️ Adding To-Do Tasks

Got something to do but no specific date or time? Use this command to add a simple to-do:
`todo <description>`

#### Example: 
If you want to add "Buy groceries" to your list: `todo buy groceries`

#### Expected Output:
```
Got it! I've added this task:
  [T][ ] buy groceries
Now you have 1 tasks in your list.
```

### 🗓️ Adding Deadlines

Deadlines are tasks with a due date. Let Taskon keep you on schedule:
`deadline <description> /by <date>`

#### Example:
Let’s say you have a "Submit assignment" deadline for October 1, 2024 2359: `deadline Submit assignment /by 2024-10-01 2359`

#### Expected Output:
```
Got it! I've added this task:
  [D][ ] Submit assignment (by: Oct 1 2024, 11:59 pm)
Now you have 2 tasks in your list.
```

### 🎊 Adding Events

For those tasks that happen within a period of time, you can add events to your calendar:
`event <description> /from <start date> /to <end date>`

#### Example:
You have a "Team meeting" from 2 PM to 4 PM on September 29, 2024: `event Team meeting /from 2024-09-29 1400 /to 2024-09-29 1600`

#### Expected Output:
```
Got it! I've added this task:
  [E][ ] Submit assignment (from: Sep 29 2024, 2:00 pm to: Sep 29 2024, 4:00 pm)
Now you have 3 tasks in your list.
```

### 📋 Listing All Tasks

Want a quick overview of all your tasks? Here’s how you can list them: `list`

#### Expected Output:
```
Here are the tasks in your list:
1. [T][ ] Buy groceries
2. [D][ ] Submit assignment (by: Oct 01 2024, 11:59 pm)
3. [E][ ] Team meeting (from: Sep 29 2024, 2:00 pm to: Sep 29 2024, 4:00 pm)
```
Stay organized by having a clear view of everything on your plate!

### ✅ Marking Tasks as Done

Time to mark that task as complete and feel the satisfaction: `mark <task number>`

#### Example:
To mark the first task on your list as done: `mark 1`

#### Expected Output:
```
Whoo-hoo! Task complete! Time to do the victory screech! I've marked this one as done:
[T][X] Buy groceries
```

### ⬜ Unmarking Tasks as Not Done

Made a mistake or need to undo? No problem: `unmark 1`

#### Example:
To unmark that task (say task 1): `unmark 1`

#### Expected Output:
```
Got it! No rush, I've marked it as not done yet:
[T][ ] Buy groceries
```

### 🗑️ Deleting a Task

Got a task that’s no longer relevant? Let’s clear it out: `delete <task number>`

#### Example:
To delete the second task on your list: `delete 2`

#### Expected Output:
```
Alright, I've removed this task:
[D][ ] Submit assignment (by: Oct 01 2024, 11:59 pm)
Now you have 2 tasks
```

### 🔍 Finding Tasks

Need to search for a specific task? Taskon’s got your back: `find <keyword>`

#### Example:
You’re looking for all tasks that include the word "meeting": `find meeting`

#### Expected Output:
```
Here's what we've got on your to-do list:
1. [E][ ] Team meeting (from: Sep 29 2024, 2:00 pm to: Sep 29 2024, 4:00 pm)
```

### 📅 Viewing Tasks on a Specific Date

Want to see what’s lined up on a certain day? Taskon helps you view tasks by date: `on <date>`

#### Example:
To view tasks scheduled for September 29, 2024: `on 2024-09-29`

#### Expected Output:
```
Tasks on 2024-09-29 :
[E][ ] Team meeting (from: Sep 29 2024, 2:00 pm to: Sep 29 2024, 4:00 pm)
```

### ✏️ Editing Tasks

Need to update task details? You can easily edit tasks with this command:
`edit <task number> <description/deadline/start/end> <new value>`

#### Example:
Let’s change the description of task 1 to "Buy milk and cereal": `edit 1 description Buy milk and cereal`

#### Expected Output:
```
Aye aye, captain! Task successfully edited!
[T][ ] Buy milk and cereal
```

### 👋 Saying Goodbye to Taskon

All done? You can gracefully exit Taskon with: `bye`

#### Expected Output:
```
Aww, barnacles! Bye, and don't forget to have a super-duper day!
```
Taskon will shut down, and your tasks will be saved for the next time you return. 🌟

## Conclusion

With **Taskon**, you’re all set to manage your to-dos, deadlines, events, and more, all in one place.
Stay organized, boost your productivity, and never miss a task again! 🎯

Let **Taskon** be your ultimate task companion! 💪
