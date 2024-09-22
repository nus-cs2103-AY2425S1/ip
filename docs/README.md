# Sammy User Guide

![img.png](img.png)

Welcome to **Sammy**! Sammy is a personal chatbot that helps you keep track of your daily tasks. You can add
different types of tasks, mark them as done, or tag them for easy reference. Sammy can also help you manage deadlines 
and events.

## Features

### Command Format
- Words in `UPPER_CASE` are the parameters to be supplied by the user.
    - Example: In `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter to be provided by the user.
- Items in square brackets `[ ]` are optional.
    - Example: `deadline TASK_DESCRIPTION /by DATE` can be used with an optional date.
- Parameters can be in any order.
    - Example: `event TASK_DESCRIPTION /from DATE /to DATE` is valid even if `/to DATE` and `/from DATE` are switched.
- Extraneous parameters for commands that do not take any parameters (e.g., `list`, `bye`) will be ignored.

---

## 1. Adding a Todo Task: todo
Adds a simple task to your task list.

Format: todo `TASK_DESCRIPTION`

Example: 
- `todo read a book`

## Adding deadlines

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Feature ABC

// Feature details


## Feature XYZ

// Feature details