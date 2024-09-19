# Bob User Guide


![alt text](Ui.png)

### Brief description
> The Bob Chatbot allows you to set tasks
 
## Adding todos

> Adds a task

Example: `todo [description]`


Expected output:
```
T [] example
```

## Adding deadlines

> Adds a task with a deadline

Example: `deadline [description] /by [date]`


Expected output:
```
D [] example (by: Month day year time)
```

## Adding events

> Adds a task with a start and end date

Example: `deadline [description] /from [date] /to [date]`


Expected output:
```
E [] example (from: Month day year time to: Month day year time)
```

## Mark Task

> Marks a task

Example: `mark [index]`


Expected output:
```
T [] example --> T [x] example
```

## UnMark Task

> Unmarks a task

Example: `unmark [index]`


Expected output:
```
T [x] example --> T [] example
```


## List Task

> Unmarks a task

Example: `list`


Expected output:
```
1. T [] example
2. T [] example2
3. T [] example3
4. T [] example4
```