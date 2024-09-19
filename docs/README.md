# Friday User Guide

![Friday](Ui.png)

Friday is a task management application that helps you keep track of your tasks, deadlines, and events. It allows you to add, delete, mark, unmark, list, filter, and sort tasks efficiently.

## Adding Todos

To add a todo task:

Example: `todo Read book`

Expected outcome: 
```
Copied that, Mr Stark. I've successfully added the following task:
   [T] [ ] Read book
You now have 8 tasks in your list.
```

## Adding deadlines

To add a deadline task:

Example: `deadline Submit report /by 2023-10-10 1800`

Expected outcome: 
```
Copied that, Mr Stark. I've successfully added the following task:
   [D] [ ] Submit report
           (by: Oct 10 2023, 6:00 PM)
You now have 9 tasks in your list.
```

## Adding Events

To add an event task:

Example: `event Team meeting /from 2023-10-10 1000 /to 2023-10-10 1200`

Expected outcome: 
```
Copied that, Mr Stark. I've successfully added the following task:
   [E] [ ] Team meeting
           (from: Oct 10 2023, 10:00 AM
           to: Oct 10 2023, 12:00 PM)
You now have 10 tasks in your list.
```

## Deleting Tasks

To delete a task:

Example: `delete 1`

Expected outcome:
```
Roger that, Mr Stark. I've removed the following task:
   [T] [ ] read book
You now have 9 remaining tasks.
```

## Marking Tasks as Done

To mark a task as done:

Example: `mark 1`

Expected outcome: 
```
Noted, Mr Stark. Task marked as completed:
   [T] [X] read book
```

## Unmarking Tasks

To unmark a task:

Example: `unmark 1`

Expected outcome: 
```
Noted, Mr Stark. Task marked as not completed:
   [T] [ ] read book
```

## Listing All Tasks

To list all tasks:

Example: `list`

Expected outcome:
```
Noted, Mr Stark. Here are the tasks in your list:
    1.  [D] [X] return book
            (by: Jun 06 2024, 11:59 PM)
    2.  [E] [ ] project meeting
            (from: Aug 06 2024, 2:00 PM
            to: Aug 06 2024, 4:00 PM)
    3.  [T] [X] join sports club
    4.  [T] [X] borrow book
    5.  [D] [ ] watch lecture recording
            (by: Sep 12 2024, 11:58 PM)
    6.  [D] [X] CS2103T ip
            (by: Sep 20 2024, 11:59 PM)
    7.  [T] [ ] Read book
    8.  [D] [ ] Submit report
            (by: Oct 10 2023, 6:00 PM)
    9.  [E] [ ] Team meeting
            (from: Oct 10 2023, 10:00 AM
            to: Oct 10 2023, 12:00 PM)
```

## Finding Tasks by Date

To find tasks by a specific date:

Example: `on 2023-10-10`

Expected outcome: 
```
Noted, Mr Stark. Here are your tasks for Oct 10 2023:
    1.  [D] [ ] Submit report
            (by: Oct 10 2023, 6:00 PM)
    2.  [E] [ ] Team meeting
            (from: Oct 10 2023, 10:00 AM
            to: Oct 10 2023, 12:00 PM)
```

## Finding Tasks by Keyword

To find tasks by a keyword:

Example: `find book`

Expected outcome: 
```
Noted, Mr Stark. Here are the tasks that match your query:
    1.  [D] [X] return book
            (by: Jun 06 2024, 11:59 PM)
    2.  [T] [X] borrow book
    3.  [T] [ ] Read book
```

## Sorting Tasks by Date

To sort tasks by date:

Example: `sort`

Expected outcome: 
```
Noted, Mr Stark. Here are your tasks sorted by date:
    1.  [E] [ ] Team meeting
            (from: Oct 10 2023, 10:00 AM
            to: Oct 10 2023, 12:00 PM)
    2.  [D] [ ] Submit report
            (by: Oct 10 2023, 6:00 PM)
    3.  [D] [X] return book
            (by: Jun 06 2024, 11:59 PM)
    4.  [E] [ ] project meeting
            (from: Aug 06 2024, 2:00 PM
            to: Aug 06 2024, 4:00 PM)
    5.  [D] [ ] watch lecture recording
            (by: Sep 12 2024, 11:58 PM)
    6.  [D] [X] CS2103T ip
            (by: Sep 20 2024, 11:59 PM)
```