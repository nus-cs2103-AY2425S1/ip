# Gutti

> The smallest feline is a masterpiece - Leonardo da Vinci [Source](https://www.laphamsquarterly.org/roundtable/old-master-cats#:~:text=One%20great%20artist%20who%20did,a%20feature%20of%20a%20painting.)

![Screenshot of app](Ui.png)
# Features implemented:
- [X] Manage to-do tasks
- [X] Manage deadlines tasks
- [X] Manage event tasks
- [X] List all tasks
- [X] Mark tasks as done
- [X] Unmark tasks
- [X] Delete tasks from list
- [X] Find tasks using keywords
- [X] Show number of tasks completed in the last 7 days
- [X] GUI

# Usage
## Todo tasks
You are able to create to-do tasks via the `todo` command.
### Command format
```
todo <description>
```
### Example format
```
todo Laundry
```
### Expected output
```
Here are the tasks in your list:
1.[T][]Laundry
```
## Deadline tasks
You are able to create tasks with deadline via the `deadline` command.
### Command format
```
deadline <description> /by <date/time> 
```
### date/time format
- dd/MM/yyyy HHmm
- MMM dd yyyy h:mma
- d/MM/yyyy HHmm
- yyyy-MM-dd HHmm
### Example format
```
deadline CS2103T assignment /by 18/09/2024 2127
```
### Expected output
```
Here are the tasks in your list:
1.[T][]Laundry
2.[D][]CS2103T assignment (by: Sep 18 2024 9:27pm)
```
## Event tasks
You are able to create event tasks via the `event` command.
### Command format
```
deadline <description> /from <date/time> /to <date/time>
```
### Example format
```
event F1 date with my broskis /from 18/09/2024 1800 /to 18/09/2024 2300
````
### Expected output
```
Here are the tasks in your list:
1.[T][]Laundry
2.[D][]CS2103T assignment (by: Sep 18 2024 9:27PM)
3.[E][]F1 date with my broskis (from: Sep 18 2024 6:00PM to: Sep 18 2024 11:00PM) 
```
## List Command
You are able to list all the tasks you have currently via the `list` command.
### Command format
```
list
```
### Example output
```
Here are the tasks in your list:
1.[T][]Laundry
2.[D][]CS2103T assignment (by: Sep 18 2024 9:27PM)
3.[E][]F1 date with my broskis (from: Sep 18 2024 6:00PM to: Sep 18 2024 11:00PM) 
```
## Mark tasks
You are able to mark tasks as completed via the `mark` command.
### Command format
```
mark <index>
```
### Example format
```
mark 1
```
### Example output
```
Nice! I've marked this task as done:
[T][X]Laundry
Here are the tasks in your list:
1.[T][X]Laundry
2.[D][]CS2103T assignment (by: Sep 18 2024 9:27PM)
3.[E][]F1 date with my broskis (from: Sep 18 2024 6:00PM to: Sep 18 2024 11:00PM)
```
## Unmark tasks
You are able to unmark tasks previously marked as done via the `unmark` command.
### Command format
```
unmark <index>
```
### Example format
```
unmark 1
```
### Example output
```
OK,I've marked this task as not done yet:
[T][]Laundry
Here are the tasks in your list:
1.[T][]Laundry
2.[D][]CS2103T assignment (by: Sep 18 2024 9:27PM)
3.[E][]F1 date with my broskis (from: Sep 18 2024 6:00PM to: Sep 18 2024 11:00PM)
```
## Delete tasks
You are able to delete tasks from the list via the `delete` command.
### Command format
```
delete <index>
```
### Example format
```
delete 1
```
### Example output
```
Meow. I've removed this task:
[T][]Laundry
Now you have 2 tasks in the list.
```
## Find command
You are able to find certain tasks using specific keywords
### Command format
```
find <wordToFind>
```
### Example format
```
find broskis
```
### Example output
```
Here are the matching tasks in your list:
1.[E][]F1 date with my broskis (from: Sep 18 2024 6:00PM to: Sep 18 2024 11:00PM)
```
## Stats command
You are able to find number of tasks completed in last 7 days via the `stats` command.
### Command format
```
stats
```
Assume we have marked the task as done 3 days ago
> CS2103T assignment

## Example output
```
You have completed 1 task in the past week!
```

### Automatic saving and loading of data
Gutti automatically does the saving and loading of tasks in your lists for you.

### Exit Command
You can exit the app via the `bye` command.
>But because Gutti is angry at you for saying bye to her she won't respond to you and will silently leave after 5 seconds.















