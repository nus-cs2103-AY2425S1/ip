# Jude User Guide

![Ui](/Ui.png)

Jude the chatbot, a personal assistant that will help you manage your tasks. 

## Prerequisite
Go to the [latest release page](https://github.com/Judy1x4/ip/releases), and download jude.jar file in an empty folder in your local computer. 

Then, open a terminal to navigate to the jar location, then run the `java -jar "JAR_FILE_NAME"` command.

## Adding tasks

* You can add three different types of tasks:


  - [ ] to-do
  - [ ] event 
  - [ ] deadline


* Each task type can be added with the formats below:

  - `to-do [description]`
  - `deadline [description] /by [date_and_time]`
  - `event [description] /from [date_and_time] /to [date_and_time]`

    where date_and_time has a format `d/M/yyyy HHmm`


* Example input: 

```
to-do study 
deadline homework /by 1/10/2024 1600
event Lecture /from 29/9/2020 1600 /to 29/9/2020 1800
```

## Commands
> Refer to Detailed Explanations section below for more detailed explanation of some commands.

`list`: shows the current tasklist status.

`sort`: sorts the tasklist by earliest occurring.

`bye`: ends the chat.

`delete [index]`: removes the task with its index of current tasklist.

`mark [index]`: marks a task as done with its index of current tasklist.

`unmark [index]`: undoes marking a task as done with its index of current tasklist.

`find [keyword]`: shows all tasks with descriptions containing the keyword. 


## Detailed Explanations

`sort`: The priority is such that Deadline task is determined with its deadline, Event task is determined with the beginning timing, and To-do tasks are considered the lowest priority.   

For example,
```
1. [T][] take a screenshot
2. [D][] homework (by: Sep 29 2020 12:00)
3. [E][] Lecture (from: Sep 29 2020 16:00 to: Sep 29 2020 18:00)
```
will be sorted to
```
1. [D][] homework (by: Sep 29 2020 12:00)
2. [E][] Lecture (from: Sep 29 2020 16:00 to: Sep 29 2020 18:00)
3. [T][] take a screenshot
```
this.
