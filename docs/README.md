# LeBron James - ChatBot User Guide

LeBron James - ChatBot is your ultimate virtual assistant that combines the greatness of LeBron James with a powerful task manager. Like LeBron on the court, this chatbot is versatile, efficient, and built to assist you in organizing your day-to-day tasks, deadlines, and events with ease. Ready to help you "Take the Shot" and "Make the Play," LeBron ChatBot will ensure you're always in control, both on and off the court.

## Adding Deadlines

In the LeBron James ChatBot, adding a deadline is like planning your game-winning shot. The chatbot helps you set a task with a specific deadline, ensuring you never miss an important moment.

### How to Add a Deadline

Use the `deadline` command to add a task with a specific deadline. When you enter the command, LeBron ChatBot will add the task to your task list, along with the specified due date.

Example Usage:

`deadline Finish CS2103T Project /by 2024-09-30`

Expected Outcome:

```
Gotchu, added the task:
  [D][ ] Finish CS2103T Project (by: Sep 30 2024)
Now you have 7 tasks in the list
```

## Adding Events

Just like orchestrating a championship-winning play, adding events in LeBron ChatBot keeps you ready for all the big moments in life.

### How to Add an Event

Use the `event` command to add an event with a specific time frame. LeBron ChatBot will keep track of your event, so you're always prepared to make an appearance.

Example Usage:

`event NUS Hackathon /from 2024-10-01 /to 2024-10-02`

Expected Outcome:

```
Stay ready! I've added this event to your schedule:
  [E][ ] NUS Hackathon (from: Oct 1 2024 to: Oct 2 2024)
Keep grinding like LeBron!"
```

## Feature: Task Management (Like a LeBron Stat Sheet)

LeBron ChatBot makes managing tasks as easy as watching LeBron hit a three-pointer. It helps you add, delete, mark, and unmark tasks. Your productivity stats are right at your fingertips, just like LeBron's all-time scoring records!

### Task Features:

- **Add a Todo**: Simply type `todo <task description>` to add a basic task.
- **Delete a Task**: Type `delete <task number>` to remove a task from your list.
- **Mark Task as Done**: Type `mark <task number>` to mark a task as complete.
- **Unmark Task as Not Done**: Type `unmark <task number>` to revert a task to incomplete.

Example Usage:

`todo Study for Finals`

`mark 1`

`delete 2`

Expected Outcome:

```
Gotchu, added the task:
[T][ ] Study for Finals

Alright bro, I've marked that task
[T][X] Study for Finals

Alright bro, I've deleted that task
[E][X] drinks (from: Nov 11 2019 to: Nov 12 2019)
```

## Feature: Find (LeBron's Vision on the Court)

Just like LeBron's unparalleled court vision, this feature allows you to quickly locate any task by searching for specific keywords. Use the `find` command to see all tasks that match your search.

Example Usage:

`find Hackathon`

Expected Outcome:

```
Here are the tasks that match 'Hackathon':
  [E][ ] NUS Hackathon (at: Oct 1 2024, 10:00 to 18:00)
Stay ready to dominate!
```


## Feature: List (Your Game Plan Overview)

Like a coach's game plan, `list` gives you an overview of all tasks in your playbook. It displays all the tasks, events, and deadlines you have set.

Example Usage:

`list`

Expected Outcome:

```
Here's your game plan:
1. [T][ ] Study for Finals
2. [D][ ] Finish CS2103T Project (by: Sep 30 2024)
3. [E][ ] NUS Hackathon (at: Oct 1 2024, 10:00 to 18:00)
Let's get to work!"
```


LeBron James - ChatBot keeps you focused and prepared, just like the King on the court. Stay organized, stay efficient, and keep dominating your day with LeBron's help!
