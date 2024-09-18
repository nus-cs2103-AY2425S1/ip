# Optimus User Guide

![Optimus User Interface](/docs/Ui.png)

## Introduction

**Optimus** is a task management app which allows users to keep track of tasks such as to-dos, deadlines, and events.

## Adding deadlines

Users can add tasks with a specified deadline with format "deadline TASK_DESCRIPTION /by YYYY-MM-DD".

For example
- deadline submit assignment /by 2024-08-15

Expected output
![expected output](docs/deadline_expected_output.png)

## Adding to-dos

sers can add tasks with to-do with format "todo TASK_DESCRIPTION".

For example
- todo borrow book

Expected output
![expected output](docs/todo_expected_output.png)

## Adding events

sers can add tasks with to-do with format "event TASK_DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD".

For example
- event project presentation /from 2024-10-10 /to 2024-10-11

Expected output
![expected output](docs/event_expected_output.png)

## Mark task as done

Users can choose index of task to mark as done with format "mark INDEX".

For example
- mark 1

Expected output
![expected output](docs/mark_task_as_done_output.png)

## List all tasks

Users can list all tasks with index with command "list".

Expected output
![expected output](docs/list_output.png)

## Delete task

User can delete task with command "delete INDEX". Find out the index using "list" command.

For example
- delete 1

## Close application

Close application by typing "bye" and entering.