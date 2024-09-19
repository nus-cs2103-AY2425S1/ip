# Neon User Guide

![Ui.png](Ui.png)

Looking to edit your lists in a more **fantastic** way? *Neon.java* is here to help!

## Adding Tasks

By writing a brief description of your tasks, the bot will be able to save the important info for you!

Example: `todo dance all night`

The task will automatically get saved with all the details you wish to add!

```
list of tasks:
1. [T][ ] dance all night
```

### Todos

All you need to add todos onto your list is to write `todo` followed by any other details you wish to add!

e.g. `todo dance all day`

### Deadlines
Similar to todos, you just need to write `deadline` followed by 
some description and `/by` to help remind you of the deadline!

e.g. `deadline CS2103T assignment /by 20th Sept`

### Events

Instead of `/by` in deadlines, you can write down `/from` what time the event starts
`/to` what time it ends!

e.g. `event CS hackathon /from 30th Feb /to 32nd Mar`

## List!
And now, whenever you want to see what exciting activitis
are coming up, you can just type `list` and Neon will tell you!

```
printing list:
1. [T][ ] dance all night
2. [D][ ] CS2103T assignment (by: 20th Sept)
3. [E][ ] CS hackathon (from: 30th Feb to: 32nd Mar)
```
You can of course edit it according to what you want too!
Some extra list editing features include:
- `delete i` to delete task number _i_
- `mark i` to mark task number _i_ as completed
- `unmark i` in case you made a mistake marking



## Extra Features

### Find
You can even find specific tasks using `find` followd by the task description! e.g. `find dance`
will give you:

```
list of tasks:
[T][ ] dance all night
```
### Undo
You can also `undo` your most recent commands!
(it will not affect `find` or `list` commands)