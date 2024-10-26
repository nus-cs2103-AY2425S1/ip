# Bao User Guide
> The Next Generation of Personal Assistants
![img.png](img.png)

Bao is here to serve you!

Bao is,
- Free 🤑
- ~~Easy~~ SUPER easy to use

## Bao's Capabilities

- [x] Track tasks (to-dos, deadlines and events!)
- [x] Search up tasks on-going at a specific date
- [x] Mark tasks as completed

Example: `todo study for midterms`

```
Bao got it! Bao is now tracking: T | 0 | study for midterms
```

### Add Task
- `todo [task description]`: Creates and tracks a to-do task
- `deadline [task description] /by [date time]`: Creates a deadline task
- `event [task description] /from [date time] /to [date time]`: Creates an event task

#### Example Inputs and Usage
Date time input is in (DD-MM-YYYY HHmm) format
- `todo clean room`
- `deadline tp task /by 30-09-2024 1800`
- `event cohesion /from 30-09-2024 1600 /to 30-09-2024 2200`

### List Tasks
- `list`: Displays all tasks in the list

#### Example Inputs and Usage
- `list`

### Occurring On 
- `on [date time]`: Displays all tasks in the list occurring on the given date

#### Example Inputs and Usage
- `on 30-09-2024`

### Mark/Unmark Task
- `mark [index]`: Marks task at index as completed
- `unmark [index]`: Unmarks task at index

#### Example Inputs and Usage
- `mark 1`
- `unmark 2`

### Delete Task
- `delete [index]`: Deletes task at index

#### Example Inputs and Usage
- `delete 1`

### Tag/Untag Task
- `tag [index] [description]`: Tags task at index with the description
- `untag [index] [description]`: Untags task at index with the description

#### Example Inputs and Usage
- `tag 1 important`
- `untag 2 important`

### Find Task
- `find [keyword]`: Finds tasks with description containing the keyword

#### Example Inputs and Usage
- `find clean`

## Download Bao
To employ Bao,
1. Download Bao [here](https://drive.google.com/file/d/1RcH4vkE2aUOl2KG6ioEPivgtgHRb5lTF/view?usp=sharing)
2. Unzip file
3. Run `_java -jar bao.jar_` in the command line
4. Start using!