# YappingBot
> _"Software is like s-x: it's better when it's free."_ - Linus Torvalds ([Source](https://x.com/Linus__Torvalds/status/296333253571387392), yes he actually said that.)

YappingBot **FREES** your mind of having to remember things you need to do.


![](Ui.png)

---
<details> 
<summary> **Quick Links** </summary>
- [Features implemented](#features-implemented)
- [Usage](#usage)
    - [Runing the bot](#running-the-bot)
    - [Commands](#commands-in-the-bot)
        - [Alias](#alias)
        - [List](#list)
        - [Reset](#reset)
        - [Exit](#exit)
        - [Filter](#filter)
        - [Delete](#delete)
        - [Mark](#mark)
        - [Unmark](#unmark)
        - [Adding Deadline Task](#deadline)
        - [Adding Event Task](#event)
        - [Adding To-do Task](#todo)
- [Tinkering](#tinkering)
</details>

---

- Text-based
- Questionable learning curve
- _SUPER_ FAST to ~~crash~~ use (I'm kidding, it's Java. You only get that* in C)
  (_* - in both speed and stability aspects_)

All you need to do is:
1. Download it from [here](https://github.com/yadobler/ip/releases) (DID YOU CHECK THE SITE LINK?).
2. Double click it (honestly doesn't work for me. You're better off running `java -jar yappingbot.java`).
3. ???
4. Add your tasks!

Supported flags include:
```
--savefile <FILEPATH> define an alternate savefile path
        -s <FILEPATH> same as --savefile
        -c            Run CLI mode (fallback)
```

Did I mention it's **FREE**? (Free of Charge, Free of Dom)

# Features implemented:
- [x] View all added tasks
- [x] Manages _to-do_ tasks
- [x] Manages _deadline_ tasks
- [x] Manages _event_ tasks
- [x] Task list persistence (automatic save to and retrieve from disk)
- [ ] Does your taxes
- [x] Search and filter tasks (iteratively too!)
- [x] GUI
- [x] CLI mode fallback (pass `-c`)
- [x] Change savefile path (pass `-s <filepath>`)
- [X] Add custom aliases
- [X] Add CSS to make things pretty
- [ ] Add more personality
- [ ] Persistant settings (ie for aliases)
- [ ] Autosave
- [ ] Flexible date formatting
- [ ] More coming soon...

---
# Usage

The following commands are availabe:

## Running the bot
If you are running the bot as such:

`java -jar yappingbot.jar`

You can add the following:
- `-c`: Use command line interface instead of GUI
- `-s SAVEPATH` denote a custom savefile path

---

## Commands in the bot:
Commands are typed as such:  

`VERB first-argument /FLAG value /FLAG value`  

Where `verb` refers to the command and `flag` refers to any possible flags. The order of the 
flags are not fixed, as long as the first argument proceeds the verb, and all non-optional 
arguments are provided. Each command has several aliases, and more can be added with the 
[`alias`](#alias) command.

**Example**:

`event I will be off /from 2023-12-25 /to 2024-1-1`

Results in a _Event_ task, titled "I will be off", with the start date as _"25th December 2023"_ 
and end date as _"1st January 2024"_.

---

### Alias
Adds a new alias for a command.
#### Verbs:
- `alias`
#### First argument:
- **Required**
- **No spaces allowed**
- *The existing command*
#### Second argument:
- **Required**
- **No spaces allowed**
- *The new alias that will run command*

---

### List
Lists all the tasks in the current filter
#### Verbs:
- `list`
- `print`
- `l`
#### Example:
`list`
#### Note:
If you have a filter, list will show the tasks matching the filter. Use [`reset`](#Reset) to remove
the filter and view the entire list. A filtered list will have the filter listed before the tasks.

---

### Reset
Resets the current view to show all tasks without filter.
#### Verbs:
- `reset`
- `r`
#### Example:
`reset`

---
### Exit
Exits the program and ends the bot.
#### Verbs:
- `bye`
- `exit`
- `:q`
#### Example:
`bye`
#### Note:
- On the GUI, the input will be disabled. The window can be closed after this.
- The bot saves the tasks to a savefile that is usually where the application was executed. This 
  can be used when launching the bot next time. See [Launching](#running-the-bot).

---

### Filter
Filters the task to show all tasks matching the given search. Searched fields include the dates 
and task titles.
#### Verbs:
- `filter`
- `search`
- `/` (space is required before search argument)
- `find`
#### First argument:
- **Required**
- *The text to be searched*
#### Example:
`find 2024`
#### Note:
- Any commands run after this will be based on this new view
- Use [`reset`](#Reset) to remove the filter and view the entire list.
- To search for multiple matching criteria, repeat the command with new search terms:
```
find Chores
f 2024
```
This will result in a filtered list `'Chores' AND '2024'`

---
### Delete
Deletes a task in the current view
#### Verbs:
- `delete`
- `d`
#### First argument:
- **Required**
- *The index number of the task to be deleted*
#### Example:
`delete 1`
#### Note:
If you have a filter, the index will be based on that filter. Use [`list`](#List) to see the 
currently filtered tasks, and [`reset`](#Reset) to remove the filter and view the entire list.

---

### Mark
Marks a task in the current view as done
#### Verbs:
- `mark`
- `m`
#### First argument:
- **Required**
- *The index number of the task to be marked*
#### Example:
`m 1`
#### Note:
If you have a filter, the index will be based on that filter. Use [`list`](#List) to see the
currently filtered tasks, and [`reset`](#Reset) to remove the filter and view the entire list

---

### Unmark
Unmarks a task in the current view as done
#### Verbs:
- `unmark`
- `um`
#### First argument:
- **Required**
- *The index number of the task to be unmakred*
#### Example:
`m 1`
#### Note:
If you have a filter, the index will be based on that filter. Use [`list`](#List) to see the
currently filtered tasks, and [`reset`](#Reset) to remove the filter and view the entire list

---

### Deadline
Adds a 'Deadline' task
#### Verbs:
- `deadline`
- `dl`
#### First argument:
- **Required**
- *The task name*
#### `/by` flag:
- **Required**
- *Date (in `YYYY-MM-DD`) of task's deadline*
#### Example:
`dl I need to do this! /by 2024-09-20`

---
### Event
Adds a 'Event' task
#### Verbs:
- `event`
- `e`
#### First argument:
- **Required**
- *The task name*
#### `/from` flag:
- **Required**
- *Date (in `YYYY-MM-DD`) of event's start date*
#### `/to` flag:
- **Required**
- *Date (in `YYYY-MM-DD`) of event's end date*
#### Example:
`event I will be off /from 2023-12-25 /to 2024-1-1`

---
### Todo
Adds a 'TO-DO' task
#### Verbs:
- `todo`
- `t`
#### First argument:
- **Required**
- *The task name*
#### Example:
`todo This is a todo task!`

---
# Tinkering
If you're bored and want to stress yourself, look no further!

Clone this, and feel free to run it like this:

**CLI Mode:**
```java
public static void main(String[] args) {
    // Feel free to change this!
    // The original main method checks if an argument is passed and uses it instead.
    String savefile = "./savefile"; 

    YappingBot yp = new YappingBot(savefile);
    yp.start();
}
```

**GUI Mode:** (requires javafx)
```java
public static void main(String[] args) {
    // MainGuiApplication currently looks in 
    // Launcher to retrieve the savefilePath
    //
    // To be updated in further commits
    Launcher.savefilePath = "./savefile";
    MainGuiApplication.launch(MainGuiApplication.class, args);
}
```