# MrTracker User Guide

> “Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity-quotes/))

MrTracker frees your mind of having to remember things you need to do. It's,

- Text-based
- Easy to learn
- ~~FAST~~_SUPER_ FAST to use!

![Ui.png](Ui.png)


What are you waiting for?? Go and check it out!

## Steps to start using:

All you need to do is:

1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).

   In the same dialog, set the **Project language level** field to the `SDK default` option.
2. Download the `.jar` file from [here](https://github.com/Clarenceeey/ip/releases)
3. Copy the file to the folder you want to use as the home folder for MrTracker.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar mrTracker.jar` command to run the application.
5. Let it manage your tasks for you :)

# Features

> ## Notes about command format:
> 
> - Words in `UPPERCASE` are pararmeters that are to be supplied by the user
>  
>  i.e. in `find PROMPT`, `PROMPT` is a parameter which can be used like `find tutorial`.
> 
> 
> - Items in square brackets are optional 
> 
>  i.e. in `todo NAME [#TAG]...`, it can be used as `todo CS2103T tutorial` or `todo CS2103T tutorial #important`.
> 
> 
> - Items with `...` can be used multiple times including zero
> 
>  i.e. `todo NAME [#TAG]...` can be used as `todo CS2103T tutorial` (zero times)
> or `todo CS2103T tutorial #important #graded` (two times).

## 1. Adding tasks
Prefix the command with the type of task you want to add. The types of tasks available are:

1. ToDo
2. DeadLine
3. Event

After adding the task, you will see the reply from MrTracker, stating that the task has been added.
It even gives you a preview on how the task will look like!

It is important to note that the order of arguments are fixed!

### ToDo tasks

Format `todo NAME [#TAG]...` into the text field and press enter.

Notice the `#` symbol, it's used to add **tags** to the task.\

Example usage: `todo CS2106 tutorial #OperatingSystems`

If you currently have no tasks, this will be the first task.

Response: 
```
I have added the task:
    [T][] CS2106 tutorial 
    Tags: #OperatingSystems
You now have 1 number of task(s)
```

### DeadLine tasks
Change the prefix from `todo` to `deadline` to create a deadline task.

`/by` dates must be specified for deadline tasks.
The dates have to be specified in the following manner: `yyyy-mm-dd`.

Format: `deadline NAME [#TAG]... /by DATE`

### Event tasks
Finally, event tasks require a `/from` and a `/to` date.

Format: `event NAME [#TAG]... /from DATE /to DATE`

## 2. Deleting tasks

Deletes the task at the specified index. The task number is 1-indexed, so typing only positive integers are accepted _i.e. 1, 2, 3..._

Format: `delete INDEX`

Example usage: If previously added task was the first task, call `delete 1` to get the response.

Response: 
```
The task:
    [T][] CS2106 tutorial 
    Tags: #OperatingSystems
has been removed!
You now have 0 tasks left.
```

## 3. Listing tasks

Lists all current tasks

Format: `list`

## 4. Marking and unmarking tasks

Sets a task as marked or unmarked

Format: `mark INDEX` or `unmark INDEX`


Response to marking:
```
Congrats on finishing CS2106 tutorial! It is now marked!
```

Response to unmarking:
```
Success, task CS2106 tutorial is now unmarked!
```

## 5. Tagging and untagging tasks

### Tagging

Tag an existing task by calling tag, followed by the index of the task you want to tag. Then, key in the tags. 
MrTracker will display how the task looks like after it has been tagged.

Tags must be prefixed by a `#`. They cannot contain whitespace and cannot be empty. 

Format: `tag INDEX [#TAG]...`

### Removing tags

Similarly, remove tags by calling remove tags, then the index of the task you want to remove the tags from. 
Key in the tags, which must be prefixed by a `#`. If no tags are given, all tags for that task will be removed.

Format: `remove tags INDEX [#TAG]...`

## 6. Find

Type find, followed by a prompt. MrTracker will filter all task names and display tasks that contain that task name, in
the order that they were inserted in.

Format: `find PROMPT`



