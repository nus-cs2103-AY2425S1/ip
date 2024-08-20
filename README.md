# Quack User Guide
   ```
   ________                       __    
   \_____  \  __ _______    ____ |  | __
   /  / \  \|  |  \__  \ _/ ___\|  |/ /
   /   \_/.  \  |  // __ \\  \___|    < 
   \_____\ \_/____/(____  /\___  >__|_ \ 
         \__>          \/     \/     \/

   ----------------------------------------
   Hello! I'm Quack
   What can I do for you?
   ```

## What is Quack?

   Quack is a friendly chatbot which helps you keep track of pesky tasks, events or deadlines!

## Setting up Quack
    Prerequisites: JDK 17, update Intellij to the most recent version.

    Open Intellij (if you are not in the welcome screen, click File > Close Project to close the existing project first)
    Open the project into Intellij as follows:
    Click Open.
    Select the project directory, and click OK.
    If there are any further prompts, accept the defaults.
    Configure the project to use JDK 17 (not other versions) as explained in here.
    In the same dialog, set the Project language level field to the SDK default option.
    After that, locate the src/main/java/Quack.java file, right-click it, and choose Run Quack.main() (if the code editor is showing compile errors, try restarting the IDE).

    For VSCode :
    Open the project directory
    Ensure that java extenstions are installed
    After that, locate the src/main/java/Quack.java file and press F5 to run the chatbot.

# Features

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Adding a Task

There are 3 task types, todo, event or deadline. And to add one of these tasks into the list type the following command:
`add <Task type> <Task description>`

Make sure that it is seperated by a space.

For events and deadlines, it will further prompt you for a start and end date.

### Example

After adding a todo task Quack will display this
```
add todo Task 1
--------------------------------------------------
Alright I have added this task into the list: 
[T][ ] Task 1
You now have 1 tasks in your list right now!
--------------------------------------------------
```

After adding a deadline task Quack will display this
```
add deadline Task 1
--------------------------------------------------
When is this task due?
21/08/2024 5PM
Alright I have added this task into the list:
[D][ ] Task 1(Due by: 21/08/2024 5PM)
You now have 1 tasks in your list right now!
--------------------------------------------------
```

After adding a event task Quack will display this
```
add event Task 1
--------------------------------------------------
When does this event start?
Tomorrow
When is this task due?
Next Week
Alright I have added this task into the list:
[E][ ] Task 1(From: Tomorrow To: Next Week)
You now have 2 tasks in your list right now!
--------------------------------------------------
```

## Listing Tasks

You can view the tasks in the list by inputing the following command:
`list`

### Example
```
list
--------------------------------------------------
1. [D][ ] Task 1(Due by: 21/08/2024 5PM)
2. [E][ ] Task 1(From: Tomorrow To: Next Week)
--------------------------------------------------
```

## Unmarking & Marking Tasks

Tasks in your list can be marked or unmarked by inputing the following command:
To mark a task use - `mark <index of the task as displayed in on the list>`
To unmark a task use - `unmark <index of the task as displayed in on the list>`

### Example

For marking a task
```
mark 2
--------------------------------------------------
Nice! I've marked this task as done:
[E][X] Task 1(From: Tomorrow To: Next Week)
--------------------------------------------------
```

For unmarking a task
```
unmark 2
--------------------------------------------------
OK, I've marked this task as not done yet:
[E][ ] Task 1(From: Tomorrow To: Next Week)
--------------------------------------------------
```

## Deleting a task

Unwanted tasks can be removed by inputing the following command:
`delete <index of the task as displayed in on the list>`

### Example
```
delete 1
--------------------------------------------------
Alright I have removed this task into the list:
[D][ ] Task 1(Due by: 21/08/2024 5PM)
You now have 1 tasks in your list right now!
--------------------------------------------------
list 
--------------------------------------------------
1. [E][ ] Task 1(From: Tomorrow To: Next Week)
--------------------------------------------------
```

## Ending the Chatbot

To stop Quack (But why would you!! :( ) key in the following command:
`bye`

### Example
```
bye
--------------------------------------------------
Bye. Hope to see you again soon!
--------------------------------------------------
```

# References

## Website to generate the ASCII Logo

https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwjNkMjnx_6HAxVN6zgGHSk9NLYQFnoECBwQAQ&url=https%3A%2F%2Fpatorjk.com%2Fsoftware%2Ftaag%2F&usg=AOvVaw1rmNDfu2i-RQ4_TslxEwcR&opi=89978449