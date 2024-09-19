# Potong User Guide

![Screenshot of Potong's user interface](Ui.png)

Potong is a Personal Assistant Chatbot that helps you keep track of your tasks.

## Quick Start

1. Ensure that you have java `17` or above installed on your Computer.
2. Download the latest `.jar` file from [here](https://github.com/BunnyHoppp/ip/releases).
3. Copy the file to a folder you want to store Potong.
4. Open a command terminal, `cd` into the folder with your .jar file, and use `java -jar Potong.jar` command to run it.

## Features

### Add Tasks :star_struck:
There are 3 types of tasks to add.

- **ToDos**: tasks without any date/time attached to it *eg. read book*
  - Format: todo [task] *todo read book*
- **Deadlines**: tasks that need to be done before a specific date *eg. submit project before 14 Oct 2024*
  - Format: deadline [task] /by [yyyy-mm-dd]
- **Events**: tasks that start at a specific date/time and ends at a specific date/time *eg. project meeting from 4 Oct 3pm to 4 Oct 5pm*
  - Format: event [task] /from [start] /to [end]

### Delete Tasks :hugs:
- Delete your tasks that you don't need

Format: delete [index]

### Show Tasks :yum:
- See all your tasks in a list

Format: list

### Mark Tasks :heavy_check_mark:
- Mark or unmark your tasks as done or not

Mark Format: mark [index] 

Unmark Format: unmark [index]

### Find Tasks :mag:
- Find your tasks with a certain keyword (all tasks with this keyword will be shown)

Format: find [keyword]

### Tag tasks :label:
- Give your tasks a tag! #FUN

Format: tag [index] [tag]

### Exit
- Save and leave the application

Format: bye

**WE HOPE YOU ENJOY USING POTONG** ![potong](Icon.png)