# YapMeister User Guide


![Screenshot of the UI](https://github.com/BlazeChron/ip/blob/A-Release/docs/Ui.png?raw=true)

Yapmeister is a **desktop app for managing tasks, optimised for use via a Command Line Interface (CLI)** while still
having the benefits of a Graphical User Interface (GUI). Yapmeister is the app's titular character. He was supposed to
have a personality, but it was dropped because of the _Great Assignment Calamity_[^1].

- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding items `todo` `deadline` `event`](#adding-items)
  - [Listing items `list`](#listing-items-list)
  - [Marking items `mark` `unmark`](#marking-items-mark-unmark)
  - [Finding items `find`](#finding-items-find)
  - [Deleting items `delete`](#deleting-items-delete)
  - [Exiting `bye`](#exiting-bye)
- [FAQ](#faq)
- [Command summary](#command-summary)

# Quick Start
1. Ensure you have Java `17` installed on your Computer.
2. Download the latest .jar file from [here](https://github.com/BlazeChron/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the _home folder_ for YapMeister.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar yapmeister.jar` command to run the application.
5. Refer to [Features](#features) for a list of commands. Or [Command summary](#command-summary).

# Features

## Adding items
### Todo item `todo`
Adds a Todo item with no deadline

Format: `todo ITEM`

Example:
- `todo Add personality to Yapmeister`

### Deadline item `deadline`
Adds a Deadline item with a deadline.

Format: `deadline ITEM /by DEADLINE`

If `DEADLINE` is in the format `YYYY-MM-DD` format, will be converted to `ISO_WEEK_DATE` format

Examples:
- `deadline end this /by 2020-01-01`
    - Date is converted to `2020-W01-3`
- `deadline end that /by tomorrow`
    - Date is left as `tomorrow`

### Event item `event`
Adds an Event item with a start and end.

Format: `event ITEM /from START /to END`

If either `START` or `END` are in the format `YYYY-MM-DD` format, will be converted to `ISO_WEEK_DATE` format (See `deadline`)

Examples:
- `event mentally check out /from 8am /to 8pm`

## Listing items `list`
Shows a list of all items.

## Marking items `mark` `unmark`
Marks/Unmarks an item in the list as done or undone.

Format: `mark INDEX` `unmark INDEX`

`INDEX` follows the indexing in `list`

![Screenshot of a mark example usage](https://github.com/BlazeChron/ip/blob/master/docs/mark-example.png?raw=true)

## Finding items `find`
Finds items and lists them.

Format: `find [-e] SEARCH_TERM`

`-e` flag will force an exact search for the terms

## Deleting items `delete`
Deletes an item in the list at the index.

Format: `delete INDEX`

`INDEX` follows the indexing in `list`

## Exiting `bye`
Exits the program

Format: `bye`

## FAQ
> Are the commands case-sensitive?

Yes

## Command summary

| Action       | Format, Example                                      |
|--------------|------------------------------------------------------|
| Add Todo     | `todo ITEM`<br/>`todo Add personality to Yapmeister` |
| Add Deadline | `deadline ITEM /by DEADLINE`<br/>`deadline end this /by 2020-01-01`                         |
| Add Event    | `event ITEM /from START /to END`<br/>`event mentally check out /from 8am /to 8pm`                     |
| List items   | `list`                                               |
| Mark/Unmark  | `mark INDEX` `unmark INDEX`                          |
| Find items   | `find [-e] SEARCH_TERM`                              |
| Delete item  | `delete INDEX`                                       |
| Exit         | `bye`                                                |

[^1]: Great Assignment Calamity: A natural disaster caused by the mass compression of long term assignment deadlines, claiming the grades of at least one person, probably.