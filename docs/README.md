# Luffy Bot User Guide

![](Ui.png)

### Luffy Bot is a interactive chatbot that helps you to keep track of your daily tasks!

## Adding tasks without deadlines

You can command Luffy bot to add tasks without deadlines!

Example: `todo Tidy your room`

```
expected output:
[T][ ] Tidy your room
```

## Adding deadlines

You can command Luffy bot to add tasks with deadlines!

Example: `deadline Do Homework / Saturday 2100HRS`

```
expected output:
[D][ ] Do Homework (by: Saturday 2100HRS)
```

## Adding events

You can command Luffy bot to add events!

Example: `event Project Meeting / Saturday 2100HRS / Saturday 2200 HRS`

```
expected output:
[E][ ] Project Meeting (from: Saturday 2100HRS to: Saturday 2200HRS)
```

## List all your current tasks

You can view all your tasks!

Example: `list`

```
expected output:
1.[T][ ] Tidy your room
2.[D][ ] Do Homework (by: Saturday 2100HRS)
3.[E][ ] Project Meeting (from: Saturday 2100HRS to: Saturday 2200HRS)
```

## Mark tasks as done

You can mark your tasks as done by providing the index!

Example: `mark 2`

```
expected output:
1.[T][ ] Tidy your room
2.[D][X] Do Homework (by: Saturday 2100HRS)
3.[E][ ] Project Meeting (from: Saturday 2100HRS to: Saturday 2200HRS)
```

## Mass mark tasks

You can mark multiple tasks at once!

Example: `mark 1 3`

```
expected output:
1.[T][X] Tidy your room
2.[D][X] Do Homework (by: Saturday 2100HRS)
3.[E][X] Project Meeting (from: Saturday 2100HRS to: Saturday 2200HRS)
```

## Unmark tasks

You can unmark your tasks if they aren't completed!

Example: `unmark 2`

```
expected output:
1.[T][X] Tidy your room
2.[D][ ] Do Homework (by: Saturday 2100HRS)
3.[E][X] Project Meeting (from: Saturday 2100HRS to: Saturday 2200HRS)
```

## Delete tasks

You can delete tasks that you have done!

Example: `delete 1`

```
expected output:
1.[D][ ] Do Homework (by: Saturday 2100HRS)
2.[E][X] Project Meeting (from: Saturday 2100HRS to: Saturday 2200HRS)
```

## Find and filter tasks

You can filter your tasks with keywords!

Example: `find work`

```
expected output:
Here are the matching tasks in your list:
1.[D][ ] Do Homework (by: Saturday 2100HRS)
```
