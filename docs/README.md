# Regina User Guide

![User Interface](Ui.png)

**Product Introduction:**  
Regina is a task management chatbot designed to help you efficiently track your tasks in a way that seems like 
a conversation with a friend. With Regina, you can create, manage, 
and organize tasks easily through a user-friendly command interface. She supports three main task types:
- **ToDo:** Tasks without a deadline, which can be done anytime.
- **Deadline:** Tasks that need to be completed by a specific time.
- **Event:** Tasks that have both a start and end time.

You can download her jar file from [here](https://github.com/yooplo/ip/releases/tag/A-Release)

## Adding ToDo Tasks

To add a todo task, use the following command format:  
`todo <task_description>`

This will add the task to the list of tasks showing on the left side.

**Example:**  
`todo run`

You will receive a confirmation message as a reply:

```
Got it. I've added this task:
  [T][ ] run
Now you have <number_of_current_tasks> tasks in the list.
Jiayous!
```

## Adding Deadline Tasks

To add a deadline task, use the following command format:  
`deadline <task_description> /by <d/m/yyyy HHmm>`

This will add the task to the list of tasks showing on the left side.

**Example:**  
`deadline Submit report /by 2/12/2024 1800`

You will receive a confirmation message as a reply:

```
Got it. I've added this task:
  [D][ ] submit report (by: Dec 02 2024, 6.00pm)
Now you have <number_of_current_tasks> tasks in the list.
Jiayous!
```

## Adding Event Tasks

To add an event task, use the following command format:  
`event <task_description> /from <d/m/yyyy HHmm> /to <d/m/yyyy HHmm>`

This will add the task to the list of tasks showing on the left side.

**Example:**  
`event project meeting /from 27/8/2024 1800 /to 27/8/2024 2000`

You will receive a confirmation message as a reply:

```
Got it. I've added this task:
  [E][ ] project meeting (from: Aug 27 2024, 6.00pm to: Aug 27 2024, 8.00pm)
Now you have <number_of_current_tasks> tasks in the list.
Jiayous!
```

## Task List with Checkboxes

On the left side of the chatbot window, you will see a list that updates as you add or delete tasks. 
You can mark tasks as done or unmark them by simply clicking the checkboxes, 
rather than typing the commands `mark <task_index>` or `unmark <task_index>`.

## Finding Tasks Using a Keyword

To easily locate specific tasks, you can use the `find` command followed by the desired **task keyword**. This will return a list of tasks from your current list that contain the specified keyword.

**Format:**

`find <keyword>`  

**Example**:
`find project`  

**Result**:
```
1. [D][ ] Submit project (by: Aug 27 2024, 5.00 pm)
2. [E][ ] cs2101 project meeting (from: Aug 27 2024, 6.00 pm to: Aug 27 2024, 7.00 pm)
3. [E][X] cs2103 project meeting (from: Aug 28 2024, 4.00 pm to: Aug 28 2024, 5.00 pm)
```

As shown, only tasks that include the keyword **project** are returned in the list.

## Snoozing Tasks

Only **Event** and **Deadline** type tasks can be snoozed. There are three main types of timing:
- **Days**
- **Hours**
- **Minutes**

**Format:**  
`Snooze <task_index> /by <numeric_value> <timing_type>`

**Example:**  
`snooze 2 /by 2 days`

If you do not specify a specific snooze value, Regina will apply a **default** snooze period of **30 minutes**.

**Example:**  
`snooze 2`

Regina will respond with the updated task details:
```
Pushed back the deadline of <task_description> by <snoozed_value> to <updated_date_and_time>
```

## Delete Tasks

To delete tasks, use the following format:  
`delete <task_index>`

Regina chatbot will then send you a confirmation message:
```
Wah shiok!
Can forget about <task> liao!
List now has <number_of_tasks_left> tasks!
```

Alternatively, to delete all tasks in your list, you can use the command:  
`clear`

You will then receive the reply:
```
Cleared all tasks!
```

## Exiting the Chatbot

To exit the app, simply type the word `bye`.

Regina will then do a countdown starting from `3`, and the window will close
once the countdown reaches `1`.
```
Bye. Hope to see you again soon! 

Closing in 3

Closing in 2

Closing in 1
```


### Happy Task Tracking!
