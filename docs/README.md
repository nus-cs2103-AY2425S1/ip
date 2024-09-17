# 🤖 Sentinel User Guide
![Sentinel Image](Ui.png)

Sentinel is a desktop app for **managing your list of tasks**. Be it todos, deadlines or events, 
Sentinel can handle it all! 

Sentinel can also function as a **client tracker**, enabling you 
to track the information of your customers such as their name and their phone numbers.
---
# 📋 Feature List
* ## ✅ Task Manager
  * [Adding todos](#adding-todos-todo)
  * [Adding deadlines](#adding-deadlines-deadline)
  * [Adding events](#adding-events-event)
  * [Finding tasks](#finding-tasks-find)
  * [Deleting tasks](#deleting-tasks-delete)
  * [Listing all tasks](#listing-all-tasks-list)
  * [Mark done](#mark-done-mark)
  * [Mark undone](#mark-undone-unmark)
* ## 🧍 Customer Manager
  * [Adding customers](#adding-customers-customer)
  * [Deleting customers](#deleting-customers-deletec)
  * [Finding customers by name](#finding-customer-by-name-findc)
  * [Listing all customers](#list-all-customers-listc)
* ## [Miscellaneous ⚠️](#miscellaneous-commands)
---
# ✅ Task Manager
## Adding todos `todo`
Adds a todo to the list of tasks.

Format: `todo NAME`\
Example: `todo do iP`

```
expected output
Added the following todo: 
[T][ ] do iP
```

## Adding deadlines `deadline`

Adds a deadline to the list of tasks.

Format: `deadline NAME /by DEADLINE`\
Example: `deadline do iP /by 12/12/2009`
```
expected output
Added the following deadline: 
[D][ ] do iP (by: Dec 12 2009)
```

## Adding events `event`

Adds an event to the list of tasks.

Format: `event NAME /from STARTDATE /to ENDDATE`\
Example: `event do iP /from 12/12/2009 /to 13/12/2009`
```
expected output
Added the following event: 
[E][ ] do iP (from: Dec 12 2009 to: Dec 13 2009)
```

## Finding tasks `find`

Displays a list of tasks matching the name inputted. You can now mark or unmark 
tasks based on this list number.

Format: `find NAME`\
Example: `find iP`
```
expected output
Here are the list of your matching tasks
1. [T][ ] do iP

 You have 1 tasks matching that name.

 You are now indexing the search list.
 Type list to back to indexing the original list.
```

## Deleting tasks `delete`

Deletes the task inputted.

Format: `delete TASKNUMBER`\
Example: `delete 1`
```
expected output
Deleted the following task:
[T][ ] do iP
```

## Listing all tasks `list`

Lists all the tasks currently in the task list.

Format: `list`\
Example: `list`
```
expected output
Here are the list of your tasks
1. [T][ ] do iP

 You have 1 tasks in total.
```

## Mark done `mark`

Marks a task as done.

Format: `mark TASKNUMBER`\
Example: `mark 1`
```
expected output
Marked the following task as done:
 [T][X] do iP
```

## Mark undone `unmark`

Marks a task as undone.

Format: `unmark TASKNUMBER`\
Example: `unmark 1`
```
expected output
Unmarked the following task:
 [T][ ] do iP
```
---
# 🧍 Customer Manager
## Adding customers `customer`
Adds a customer to the list of customers.

Format: `customer NAME /num PHONENUMBER`\
Example: `customer John /num 10101010`

```
expected output
Added the following customer:
John #10101010
```

## Deleting customers `deletec`
Deletes a customer from the list of customers.

Format: `deletec CUSTOMERNUMBER`\
Example: `deletec 1`
```
expected output
Deleted the following customer:
John #10101010
```

## Finding customer by name `findc`
Returns a list of customers matching the name inputted.

Format: `findc CUSTOMERNAME`\
Example: `findc John`
```
expected output
Here are the list of your matching customers
1. John #10101010

 You have 1 customers matching that name.
```

## List all customers `listc`
Lists all customers currently in the customer list.

Format: `listc`\
Example: `listc`
```
expected output
Here are the list of your customers
1. John #10101010

 You have 1 customers in total.
```
---
# Miscellaneous Commands
## Exiting the program `bye`
Closes the program and saves your data.

> [!IMPORTANT]
> Your data will only be saved if you exit with this command.

Format: `bye`\
Example: `bye`
```
expected output
It was a pleasure conversing with you. Goodbye!
```


