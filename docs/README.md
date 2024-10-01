# Sigma User Guide
>For Sigmas, by Sigmas.


Here's a snippet of what Sigma looks like:
![Ui.png](Ui.png)

## Adding ToDos

You can give Sigma a Todo task to remember using the `/todo` command.


Example: `/todo check email`

Here's what the output would look like:

```
added todo task:
[T][ ] check email
```

## Adding deadlines

You can give Sigma a deadline to remember using the `/deadline` command.


Example: `/deadline do CS2103T quiz /by 2024-09-20 14:00:00`

Here's what the output would look like:

```
added deadline task:
[D][ ] do CS2103T quiz (by: Sep 20 2024 14:00:00)
```
## Adding deadlines

Similarly, you can log events using `/event`.

Example: `/event youthxhack /from /to`

Here's what the output would look like:

```
added event task:
[E][ ] do CS2103T quiz (by: Sep 20 2024 14:00:00)
```

## Finding a task

Too many tasks in the list? Seamlessly find a specific task using `find` followed
by the keyword(s) of your task description. Sigma will display all tasks that partially
match the given keyword(s).

## All your tasks at a glance

Don't worry about forgetting what you have added to your list of tasks,
simply enter `list` and Sigma will display a pretty list of all your current tasks.

Use `mark` followed by the task number to mark any task as completed.
Don't worry, Sigma will remember this task until you `delete` it off the list. 
You can also `unmark` a task as undone.

## Erm, what the Sigma?

Sigma says this when it receives a command it does not recognise. Fear not, just re-enter 
the amended command and it will be as if nothing happened.

## Goodbye, Sigma!

When you're done checking out your tasks for the day, use the `bye` command to 
exit Sigma. **It's important to use `bye` because it saves all outstanding tasks to the disk.**
If you don't, your tasks may not be there the next time you launch Sigma!