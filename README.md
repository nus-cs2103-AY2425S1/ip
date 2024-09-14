# <img src="DavidLogo.png" alt="David Logo" width="25"/> David User Guide

![Screenshot of David chatbot](/Ui.png)


David is a task list tracker, enhanced and optimised for users who prefer
typing.

The features David provides include:
- [x] Add tasks (Todo tasks, Deadline tasks and event tasks)
- [x] Delete tasks
- [x] Local caching of tasks
- [x] Sort of tasks
- [x] Find tasks
- [x] Mark and unmark of tasks

## Command summary
<table>
  <tr>
    <th>Command</th><th>Arguments</th><th>Action</th>
  </tr>
  <tr>
    <td>

`todo`
</td>
<td>

`TASK_NAME`
</td>
<td>Adds todo task</td>

<tr>
    <td>

`deadline`
</td>
<td>

`/by YYYY-MM-DD HHHH`
</td>
<td>Adds deadline task</td>
  </tr>

  <tr>
    <td>

`event`
</td>
<td>

`/from YYYY-MM-DD HHHH`

`/to YYYY-MM-DD HHHH`
</td>
<td>Adds event task</td>
  </tr>

  <tr>
    <td>

`mark` and `unmark`
</td>
<td>

`INDEX`

</td>
<td>Marks and unmarks a task</td>
  </tr>


  <tr>
    <td>

`delete`
</td>
<td>

`INDEX`

</td>
<td>Deletes a task</td>
  </tr>


  <tr>
    <td>

`list`
</td>
<td> - 
</td>
<td>Lists all task</td>
  </tr>


  <tr>
    <td>

`find`
</td>
<td> 

`TASK_NAME`
</td>
<td>Finds all task corresponding to the specified string</td>
  </tr>


  <tr>
    <td>

`sort`
</td>
<td> 

`ORDER_BY`
</td>
<td>Sorts tasks in the order specified</td>
  </tr>
</table>


## Features
> [!NOTE]
> - Words in `UPPER_CASE` are required parameters supplied by the user.
> - Words in `lower_case` are action specifiers for commands


### Add todos
Adds a todo task to the list of tasks.

Format: `todo TASK_NAME`

Example: `todo eat`


### Add deadlines
Adds a deadline task to the list of tasks.

Format: `deadline TASK_NAME /by YYYY-MM-DD HHHH`

Deadlines should be appended with an input in the format of "YYYY-MM-DD HHHH"
where
- "Y" reprsents the year
- "M" represents the month
- "D" represents the day of the month
- "H" represents the 24-hour time

Example: `deadline cs2100 assignment /by 2024-12-12 1230`

### Add events
Adds an event task to the list of tasks.

Format: `event TASK_NAME /from YYYY-MM-DD HHHH /to YYYY-MM-DD HHHH`

`/from` and `/to` should be appended with an input in the format of "YYYY-MM-DD HHHH"
where
- "Y" reprsents the year
- "M" represents the month
- "D" represents the day of the month
- "H" represents the 24-hour time

Example: `event dance /from 2024-12-12 1230 /to 2024-12-12 1330`

### List tasks
Lists all tasks that have been added

Format: `list`

Example: `list`

### Mark and unmark tasks
Marks or unmarks tasks selected

Format: `mark INDEX` or `unmark INDEX`


Example:
```
mark 1
unmark 1
```

### Find tasks
Returns all tasks that matches the given event name.

Format: `find TASK_NAME`

Example: `find eat`

### Delete tasks
Deletes the specified task corresponding to the number in the list

Format: `delete INDEX`

Required arguments: `i` task number to delete

Example: `delete 1`

### Sort tasks
Sorts all tasks in the given order.

Format: `sort ORDER`

Parameters accepted:
- `asc` - ascending order
- `desc` - descending order

Example: `sort asc`


