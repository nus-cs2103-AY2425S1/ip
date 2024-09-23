# Future You User Guide
![](Ui.png)
## Features 

1) Adding of tasks (todo/deadline/event)
2) Reading of all tasks in Task List
3) Mark/unmarking of tasks (complete or incomplete)
4) Deletion of tasks
5) Searching of tasks with task name
6) Sorting of tasks
7) Always remembering your task list

### 1) Adding of Tasks
Future You allows for 3 types of tasks to be added:
- Todos
- Deadlines
- Events

The format to add any one of the task is:
```
todo <task-name>
deadline <task-name> /by <YYYY-MM-DD HH:mm>
event <task-name> /from <YYYY-MM-DD HH:mm> /to <YYYY-MM-DD HH:mm>
```

Examples: 
- `todo Homework`
- `deadline ASG1 /by 2024-09-20`
- `event Class bonding /from 2024-09-15 /to 2024-09-20`

## 2) Reading of all tasks in Task List

Future You can list all tasks in your task list.

Format:`list`

## 3) Mark/unmarking of tasks

Future You can mark any tasks you added as completed or as not completed.  
Format: `mark <task number>`
Examples:
- `mark 1`
- `mark 3`


## 4) Deletion of tasks

Future You also allows you to delete any task in your task list.

Format: `delete <task number>`

## 5) Searching of tasks with task name

Future You can allow you to find all tasks with the name matching (full/partial) your input.

Format:`find <Keyword>`

Examples:
```
find ASG
find homework
```
## 6) Sorting Your Tasks
Future You can sort the three different task types in the following order Tasks, Deadlines, and Events. 
Tasks are sorted lexicographically by name, Deadlines by their due date, and Events by their end date
Format: `sort`

## 7) Always remembering your task list
Future You will keep a copy of your task list on your device so you never need to worry about losing any data!

## 8) Exiting the program
To exist Future You just type `bye`

## Acknowledgements

1. [ASCII Art](https://www.asciiart.eu/text-to-ascii-art)
2. [Java Input](https://www.w3schools.com/java/java_user_input.asp )
3. [Split by `|`](https://stackoverflow.com/questions/16311651/java-string-split-by)
4. [Sorting Tasks Reference 1](https://www.geeksforgeeks.org/collections-sort-java-examples/)
5. [Sorting Tasks Reference 2](https://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property)
6. [Sorting Tasks Reference 3](https://stackoverflow.com/questions/62492114/how-does-comparator-works-internally)
5. [Future You Icon](https://soundcloud.com/jo_el_mont/turn-it-up-jersey-club-type-beat)
6. [User Icon](https://emojiisland.com/products/slightly-smiling-face-emoji-icon)