# CS2103T IP:
# RapGod / RapBot

> "You don't get another chance, life is no Nintendo game."  
> — Eminem

RapGod helps you manage both life and work seamlessly, ensuring you never miss a beat. It's:

- **Text-based**: Easy to use with a simple interface.
- **Easy to learn**: Get started quickly without a steep learning curve.
- **FAST**: Extremely efficient for managing tasks.

Getting started is simple:

1. Run the application.
2. Add your tasks.
3. Let RapGod handle the rest for you! 😉

### Features

- **Echo Bot**: A bot that repeats your input. Perfect for when you need a break and want to de-stress.
- **List Bot**: Manages and displays your tasks with various commands.

#### List Bot Special Commands

- **'LIST'**: Show the full list of tasks.
- **'FIND abc, def'**: Filters tasks with `abc` or `def`.
- **'MARK n'**: Marks the nth task as complete.
- **'UNMARK n'**: Marks the nth task as incomplete.
- **'DELETE n'**: Deletes the nth task.
- **'/BY z'**: Specifies a deadline `z`.
- **'/FROM x /TO y'**: Specifies the start and end of an event from `x` to `y`.
- **'SNOOZE n /by x'**: Changes the deadline of a Deadline Task to `x`.
- **'SNOOZE n /from x /to y'**: Changes the bounds of an Event Task from `x` to `y`.

**Time formats accepted**:
- `dd/MM/yyyy`
- `dd/MM/yyyy HHmm`
- `MMM dd yyyy h:mma`
- `MMM dd yyyy hh:mma`

### Example Task List

Here's an example of what your task list might look like after using the commands:
```
Displaying ListBot:

1. [T] [X] Complete project report
2. [D] [ ] Submit tax forms (by: Sep 30 2024 05:00pm)
3. [E] [X] Team meeting (from: Jul 23 2024 10:00am to: Jul 23 2024 12:00pm)
4. [T] [ ] Buy groceries
5. [D] [ ] Finish reading book (by: Aug 15 2024)
6. [E] [ ] Conference (from: Nov 01 2024 to: Nov 03 2024)
7. [T] [ ] Schedule dentist appointment
```
### To Do

- [x] JavaDoc
- [x] Update **List Bot** with new commands eg 'find'
- [ ] Add unit tests for new features

Any feeback is welcome :)

Looking forward to your review!
