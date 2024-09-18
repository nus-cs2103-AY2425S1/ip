# Duke Korolev User Guide
<img src="Ui.png">
  
## Product Intro
> You can do it quickly, but badly, or you can do it slowly, but well. After a while, everyone will forget that it was fast, but will remember that it was bad. And vice versa.  - Sergei Korolev (<a href="https://en.wikiquote.org/wiki/Sergei_Korolev">source</a>)

DukeKorolev is a ~~robot~~ **chatbot** that help you manage tasks in the daily lives.
The software is named after the famous Soviet engineer *Sergei Korolev*. The bot is

- text-based
- user friendly
- extremely fast
- free to use 👍

You can create, view and delete tasks with three different types - todo, event and deadline. You can also better 
manage your task lists by tagging tasks and changing status of completion of tasks. Furthermore, you
can display the statistics of current 

## Quick start
1. Ensure you have `java 17` or above installed in your PC.
2. Download the latest `.jar` file from <a href="/">here</a>
3. Copy the file to the folder you want to use as the home folder for your DukeKorolev
4. Open command terminal, `cd` into the selected folder with jar file
5. run command `java -jar korolev.DukeKorolev.jar`. A GUI similar to the image above will be shown in the screen

## Features
### Add deadline task
Add a type of deadline task (a task with 1 deadline) to the task list.
  
Format of input: `deadline [task description] /by [end time with format YYYY-MM-DD (HH:mm)]`  
Example: ``  

```
expected output
```

### Add todo task
Add a type of todo task (a task without any time) to the task list.   
Format of input: `todo [task description]`  
Example: ``  
```
expected output
```
   
### Add event task
Add a type of event task (a task with start and end time) to the task list.  
Format of input: `event [task description] /from [start time with format YYYY-MM-DD (HH:mm)] 
/to [end time with format YYYY-MM-DD (HH:mm)]`  
Example: ``  
```
expected output
```

### Mark a task as done
Mark an incomplete task in the task list as done.   
Format of input: `mark [task ID]`  
Example: ``

### Add tag to a task 
Add a tag to any task.   
Format of input: `tag [task ID] [task description]`  
Example:  ``

### Find a list of tasks by name
Search for a list of tasks which contains the keyword.  
Format of input: `find [keyword]`  
Example: ``

### Delete a task
Delete a task from the list of tasks.  
Format of input: `delete [task ID]`
Example: ``

### Display statistics of the task list
Display the number of complete and incomplete tasks.  
Format of input: `stats`
Example: ``

## Command summary
|Command  |Format | Example |
|---------|-------|---------|
|test1    |test2  | test3   |