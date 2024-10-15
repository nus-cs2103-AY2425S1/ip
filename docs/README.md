# Niko Chatbot


![Niko](https://publicmain.github.io/ip/Ui.png?raw=true "Title")

Welcome to the **Niko Chatbot** repository! Niko is a feature-rich and user-friendly chatbot designed to help you manage tasks, set reminders, and keep track of your schedule effortlessly. Whether you're a student, professional, or someone looking to organize daily activities, Niko is here to assist you.


### Add Todo Task:

- **ToDo Task**: `todo [description]`

eg. `todo eat` 
```
Got it! I've added this task:
[T][ ] eat
Now you have 1 tasks in the list, god bless you
```

### Add Deadline Task:

- **Deadline Task**: `deadline [description] /by [due date]`

eg. `deadline eat /by 2024-11-12`
```
Got it. l've added this task:
[D][] eat (by: Nov 12 2024 11:59 PM)
Now you have 59 tasks in the list. god bless you
```
### Add Event Task:

- **Event Task**: `event [description] /from [start time] /to [end time]`

eg. `event eat /from 2024-11-12 /to 2024-11-13`
```
Got it. l've added this task:
[E][]eat (from: Nov 12 2024 11:59 PM to: Nov 13 2024 11:59 PM)
Now you have 60 tasks in the list. god bless you
```
## Supported Date and Time Formats

This project supports the following date and time formats. When adding events or specifying date and time, please use any of the formats listed below.

**Note**: If the time is not specified, the system will automatically set the time to **11:59 PM** by default.

### Date and Time Formats

The following formats support both date and time input:

- **MMM d yyyy h:mm a**
  Example: `Oct 15 2024 2:30 PM`

- **d/M/yyyy HHmm**  
  Example: `15/10/2024 1430`

- **dd/MM/yyyy HHmm**  
  Example: `15/10/2024 1430`

- **yyyy-MM-dd HHmm**  
  Example: `2024-10-15 1430`

- **yyyy/MM/dd HHmm**  
  Example: `2024/10/15 1430`

- **dd-MM-yyyy HHmm**  
  Example: `15-10-2024 1430`

- **dd.MM.yyyy HHmm**  
  Example: `15.10.2024 1430`

- **d/M/yyyy HH:mm**  
  Example: `15/10/2024 14:30`

- **dd/MM/yyyy HH:mm**  
  Example: `15/10/2024 14:30`

- **yyyy-MM-dd HH:mm**  
  Example: `2024-10-15 14:30`

- **yyyy/MM/dd HH:mm**  
  Example: `2024/10/15 14:30`

- **dd-MM-yyyy HH:mm**  
  Example: `15-10-2024 14:30`

- **dd.MM.yyyy HH:mm**  
  Example: `15.10.2024 14:30`

### Date-Only Formats

The following formats support date input only, without specifying time:

- **d/M/yyyy**  
  Example: `15/10/2024`

- **dd/MM/yyyy**  
  Example: `15/10/2024`

- **yyyy-MM-dd**  
  Example: `2024-10-15`

- **yyyy/MM/dd**  
  Example: `2024/10/15`

- **dd-MM-yyyy**  
  Example: `15-10-2024`

- **dd.MM.yyyy**  
  Example: `15.10.2024`

### View all tasks in your list

Type `list` to display all tasks currently in your list

```
Here are the tasks in your list:
1. [T][ ] eat
```

### Mark / Unmark / Delete tasks

Type `mark` followed by the corresponding number of the task in your list to mark it as done

eg. `mark 1`

```
got it, bro, marked  as done:
[T][X] eat
```

Type `unmark` followed by corresponding number of the task in your list to mark it as not done

eg. `unmark 1`

```
emmm, changed your mind ah? okay, leave it to niko
[T][ ] eat
```

Type `delete` followed by the corresponding number of the task in your list to delete it

eg. `delete 1`

```
Noted I've removed this task:
[T][ ] eat
Now you have 0 tasks in the list.
```

### Find tasks

Type `find` followed by a keyword to find the matching tasks in your list

eg. `find eat`

```
Here are the matching tasks in your list:
1. [T][ ] eat
```