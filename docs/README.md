# Totoro User Guide

![Screenshot of product](Ui.png)

## Product Introduction
**Totoro** is a friendly chatbot designed to help you manage your tasks with ease! Inspired by the lovable character Totoro, this chatbot will assit you in organising your todos, deadlines, events and more!😉 Whether you're handling daily responsibilities or long-term projects, Totoro is here to support you every step of the way❤️


## Adding deadlines
**Action**: To add a deadline task, use the following command
```
deadline DESCRIPTION /by DD/MM/YY HH:mm
```
**Outcome**: Totoro will add the task to your list with the specified deadline

**Examples**:
- `deadline Submit assignment /by 15/09/24 17:00`
- `deadline Homework /by 21/09/24 23:59`

**Expected Output**:
```
A new task? I'm on it!! I will add this task to the list:
[D][ ] Submit assignment (by: 15/09/24 17:00)
Now you have 1 task in your list
```

## Adding events
**Action**: To add an event task, use the following command
```
event DESCRIPTION /from DD/MM/YY HH:mm /to DD/MM/YY HH:mm
```
**Outcome**: Totoro will add the task to your list with the specified timeline

**Examples**:
- `event Meeting /from 15/09/24 17:00 /to 15/09/24 18:00`
- `event Project /from 21/09/24 12:00 /to 29/09/24 23:59`

**Expected Output**:
```
A new task? I'm on it!! I will add this task to the list:
[E][ ] Meeting (from: 15/09/24 17:00 to: 15/09/24 18:00)
Now you have 2 tasks in your list
```

## Adding todo
**Action**: To add a todo task, use the following command
```
todo DESCRIPTION
```
**Outcome**: Totoro will add the task to your list

**Examples**:
- `todo Read book`
- `todo Buy groceries`

**Expected Output**:
```
A new task? I'm on it!! I will add this task to the list:
[T][ ] Read book
Now you have 3 tasks in your list
```

## Marking Tasks
**Action**: To mark a task as done, use the following command
```
mark TASK_NUMBER
```
**Outcome**: Totoro will update the task status to done

**Examples**:
- `mark 1`

**Expected Output**:
```
YAY! Another task completed! I will mark this task as done:
[D][X] Submit assignment (by: 15/09/24 17:00)
```

## Unmarking Tasks
**Action**: To unmark a task, use the following command
```
unmark TASK_NUMBER
```
**Outcome**: Totoro will update the task status to be not done

**Examples**:
- `unmark 1`

**Expected Output**:
```
Aw ok, please remember to complete this task later:
[D][ ] Submit assignment (by: 15/09/24 17:00)
```

## Deleting Tasks
**Action**: To delete a task, use the following command
```
delete TASK_NUMBER
```
**Outcome**: Totoro will delete the task

**Examples**:
- `delete 2`

**Expected Output**:
```
Got it! I will remove this task:
[E][ ] Meeting (from: 15/09/24 17:00 to: 15/09/24 18:00)
Now you have 2 tasks in your list.
```

## Find Tasks
**Action**: To find tasks with the specified keyword, use the following command
```
find KEYWORD
```
**Outcome**: Totoro will find tasks that match the specified keyword

**Examples**:
- `find meeting`
- `find book`

**Expected Output**:
```
Yay yay hope these are the tasks you are looking for:
[E][ ] Meeting (from: 15/09/24 17:00 to: 15/09/24 18:00)
```
## Schedule Tasks
**Action**: To find tasks with the specified date, use the following command
```
schedule DD/MM/YY
```
**Outcome**: Totoro will find tasks that match the specified date

**Examples**:
- `schedule 15/09/24`
- `schedule 21/09/24`

**Expected Output**:
```
I found the tasks scheduled for 15/09/24:
[E][ ] Meeting (from: 15/09/24 17:00 to: 15/09/24 18:00)
```

## List Tasks
**Action**: To display all the tasks in your list, use the following command
```
list
```
**Outcome**: Totoro will display all the tasks in your list

**Expected Output**:
```
Here are the tasks for you:
1. [D][ ] Submit assignment (by: 15/09/24 17:00)
2. [D][ ] Homework (by: 21/09/24 23:59)
3. [E][ ] Meeting (from: 15/09/24 17:00 to: 15/09/24 18:00)
```

## Need help
**Action**: When you need help to find the commands, use the following command
```
help
```
**Outcome**: Totoro will display all the commands and their appropriate format

**Expected Output**:
```
Need a hand?
Follow these Totoro commands:
1. bye: Use when you want to exit
2. help: When you need help on the command
3. list: Displays all the tasks in your list
4. mark <task number>: Marks your task to display as done
5. unmark <task number>: Unmarks your task to display as not done
6. todo <description>: Adds a todo task to your list
7. deadline <description> /by <dd/MM/yyyy HH:mm>: Adds a deadine task to your list
8. event <description> /from <dd/MM/yyyy HH:mm> /to <dd/MM/yyyy HH:mm>: Adds an event task to your list
9. delete <task number>: Deletes the task from your list
10. find <keyword>: Searches for tasks matching your keyword
11. schedule <dd/MM/yy>: Searches for tasks matching your date
```

## Exit
**Action**: To exit the chatbot, use the following command
```
bye
```
**Outcome**: Totoro will exit

**Expected Output**:
```
Bye!! Hope to see you again next time :)
```
