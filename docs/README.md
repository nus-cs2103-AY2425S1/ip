# Winner User Guide
___
## Product Image
![Product image](Ui.png)

## Product Introduction
Winner is your personal task chatbot that helps to manage your tasks efficiently by taking in supported commands which 
will be provided in this guide.

### Getting started
___
- Ensure that you have Java 17 and above installed in your device.
- Download the chatbot here.
- Run the chatbot.

#### Running the Chatbot
- Open terminal in your device.
- Navigate to the folder that you store `winner.jar`.
- Run this command:
```
java -jar winner.jar
```
- Enter command `help` to get a help page providing a list of commands you can use.
- Enjoy using Winner chatbot :D

### Commands
___
#### Adding ToDo tasks
Use the `todo (task description)` command to add a task to your list.

Example: `todo buy bread`

Expected output:
```
I have added this task into the list for you and 
that brings your total number of tasks to 1
     [T] [ ] buy bread
     
You can use the command "list" to view your list of tasks :D
```
___
#### Adding Deadline tasks
Use the `deadline (task description) by (date - dd/mm/yyyy) at (time - 24 hour format)` command to add a task with 
deadline to your list.

Example: `deadline buy bread by 20/09/2024 at 2359`

Expected output:
```
I have added this task into the list for you and 
that brings your total number of tasks to 2
     [D] [ ] buy bread (by: Friday, 20/09/2024 at 2359)
     
You can use the command "list" to view your list of tasks :D
```
___
#### Adding Event tasks
Use the `event (task description) from (start) to (end)` command to add an event with a start day/time and an end 
day/time to your list.

Example: `event bread sales from Monday 10am to Friday 10pm`

Expected output:
```
I have added this task into the list for you and 
that brings your total number of tasks to 3
     [E] [ ] bread sales (monday 10am - friday 10pm)
     
You can use the command "list" to view your list of tasks :D
```
___
#### List your tasks
Use the `list` command to display a list of all your current tasks.

Example: `list`

Expected output:
```
Here are the tasks you have in your list:
1. [T] [ ] buy bread
2. [D] [ ] buy bread (by: Friday, 20/09/2024 at 2359)
3. [E] [ ] bread sales (monday 10am - friday 10pm)
```
___
#### Marking completed tasks
Use the `mark (task number)` command to mark a completed task as done.

Example: `mark 1`

Expected output:
```
Yay! One task down!
I have marked the following task as DONE :)
     [T] [X] buy bread
```
___
#### Unmarking completed tasks
Use the `unmark (task number)` command to unmark a completed task.

Example: `unmark 1`

Expected output:
```
Oh no! I shall mark the following task as UNDONE :(
You can do it!
     [T] [ ] buy bread
```
___
#### Deleting tasks
Use the `delete (task number)` command to remove a task from your list.

Example: `delete 1`

Expected output:
```
Alright! I have removed this task for you.
     [T] [ ] buy bread
```
___
#### Finding tasks
Use the `find (keyword)` command to list down all tasks containing the keyword.

Example: `find sales`

Expected output:
```
Here are the tasks you are searching for:
1. [E] [ ] bread sales (monday 10am - friday 10pm)
```
___
#### Saying goodbye
Use the `bye`/`goodbye` command to say goodbye!

Example: `bye`

Expected output:
```
Hope I have been of service!
Thank you and see you again soon :D
Remember!!! A WINNER NEVER LOSES!!!
```
___
### Features
___
#### Help feature
User can get a help page receiving a list of all commands they can use.
#### Finding tasks feature
User can search for all relevant tasks with a keyword.