# Reo User Guide

![Product Screenshot](./Ui.png)

Meet Reo, the smart and intuitive chatbot designed to help you stay organized and on top of your tasks. 
Whether you're managing your daily to-dos, tracking deadlines, managing your schedule or your contacts, 
Reo makes it effortless. With its user-friendly interface, 
you can interact with Reo just like you would with a personal assistant—simply type your tasks, 
and Reo will help you manage them efficiently.

## View task list
`list`
Displays the entire list of tasks.

Example: `list`
```
1. [T] [] Tutorial 3
1. [T] [] Tutorial 4
```

## Adding to-do items
`todo [name]`
Add a to-do item with a name, but no deadline, to your task list.

Example: `todo Tutorial 3`
```
I've added this todo:
[T] [] Tutorial 3
Now, you have 3 task(s) in your list.
```

## Adding deadlines
`deadline [name] /by [yyyy-mm-dd]`
Add a task with a name and deadline to your task list.
The deadline must be in the format yyyy-mm-dd.

Example: `deadline Tutorial 3 /by 2024-09-08`
```
I've added this deadline:
[D] [] Tutorial 3 (by: Sep 08 2024)
Now, you have 4 task(s) in your list.
```

## Adding event items
`event [name] /from [start] /to [end]`
Add an item with a name, starting time, and ending time, to your task list.
The starting time and ending time can be in any string format.

Example: `event Tutorial 3 /from 1pm /to 3pm`
```
I've added this event:
[E] [] Tutorial 3 (from: 1pm to: 3pm)
Now, you have 5 task(s) in your list.
```

## Deleting item(s)
`delete [at least 1 list index]`
Delete specified items from the task list, by index (1-indexed).
You can list any number of valid indexes in any order.

Example: `delete 3 2`
```
I've deleted these tasks:
1. [E] [] Lab 2 (from: 1pm to: 3pm)
2. [T] [] Tutorial 3
```

## Mark item
`mark [index]`
Mark a specified item as completed, by index (1-indexed).

Example: `mark 2`
```
Good job! I've marked this item as done:
[E] [] Lab 2 (from: 1pm to: 3pm)
```

## Unmark item
`unmark [index]`
Mark a specified item as completed, by index (1-indexed).

Example: `unmark 2`
```
Good job! I've marked this item as done:
[E] [] Lab 2 (from: 1pm to: 3pm)
```

## Finding item
`find [keyword]`
Search for any task item, by name. Reo will return all partial or exact matches found.

Example: `find lab`
```
Here are the matching tasks in your list:
1. [T] [] lab 1
2. [T] [] lab 2
3. [T] [] lab 3
```

## Adding contact
`person [name] /n [phone number] /a [address]`
Add contact information for 1 person, with a name, phone number and address.

Example: `person tim /n 98989898 /a 5 Git Drive`
```
I've added this contact:
tim | 98989898 | 5 Git Drive
```

## View contact list
`contacts`
Displays the entire list of contacts.

Example: `contacts`
```
1. tim | 98989898 | 5 Git Drive
2. john | 89898989 | 6 Git Drive
```


## Deleting contact(s)
`deletecontact [at least 1 list index]`
Delete specified contacts from the contact list, by index (1-indexed).
You can list any number of valid indexes in any order.

Example: `deletecontact 1 2`
```
I've deleted these contacts:
tim | 98989898 | 5 Git Drive
john | 89898989 | 6 Git Drive
```

## Exiting program
`bye`
Exit the chatbot immediately. All changes will still be saved.