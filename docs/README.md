# Colress User Guide

![Screenshot of the Colress Interface](Ui.png)

Colress frees your mind of having to remember things you need to do. It's, 
- text-based
- easy to learn
- *super* quick to use
- **FREE!!!**

## Running Colress
1. Download the .jar file here
2. Navigate to the folder where the jar file is
3. Open your terminal
4. Run the following command: `java -jar colress.jar`
5. Give some commands to Colress!

## General Guide to Giving Commands to Colress
- When inputting dates, the dates should be in the form yyyy-mm-dd.
  - For example, to input the date 20th September 2024, the input should be 2024-09-20.
- When inputting times, the times should be in the form hh:mm in 24-hour format.
  - For example, to input the time 4pm, the input should be 16:00.
- Colress only supports multiline commands. Colress prompts for any necessary additional input after each input
  - In the example commands given below, each line represents a separate input.

## Add Command

The Add Command adds tasks to a list. This list is stored as a text file on your machine.

Currently, Colress supports 3 types of tasks:
- To-Do tasks (no deadline, no time span)
- Deadline tasks (has deadline, no time span)
- Event tasks (has deadline, has time span)

Example: 
```
add
event
lecture
2024-09-20
16:00
18:00
```

adds a lecture event task that falls on the 20th September 2024 between 4pm and 6pm to a list.

Once added, a task is assigned a task number and numerous actions can be performed on it.

## Check Command

The Check Command marks tasks in the list as done.

Example:
```
check
1 2
```
marks the first and second tasks in the list of tasks as done.

## Uncheck Command

The Uncheck Command marks tasks in the list as not done.

Example:
```
uncheck
1 2
```
marks the first and second tasks in the list of tasks as not done.

## Delete Command

The Delete Command deletes tasks from the list.

Example:
```
delete
1 2
```
deletes the first and second tasks from the list of tasks.

## List Command

The List Command lists all the tasks in the list.

Example:
```
list
```
lists all the tasks in the list, including their completion status and their associated dates and times.

## Date Command

The Date Command lists all the tasks in the list that have a deadline or fall on a specified date.

Example:
```
date
2024-09-20
```
lists all the tasks in the list that have a deadline or fall on 20th September 2024.

## Find Command

The Find Command lists all the tasks in the list that have the specified keyword in their descriptions.

Example:
```
find
lecture
```
lists all the tasks in the list that have the keyword lecture in their descriptions.

