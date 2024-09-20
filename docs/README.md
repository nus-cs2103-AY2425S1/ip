# YapMeister User Guide


![Screenshot of the UI](https://github.com/BlazeChron/ip/blob/A-Release/docs/Ui.png?raw=true)

Yapmeister is a **desktop app for managing tasks, optimised for use via a Command Line Interface (CLI)** while still
having the benefits of a Graphical User Interface (GUI). Yapmeister is the app's titular character. He was supposed to
have a personality, but it was dropped because of the _Great Assignment Calamity_[^1].

- Quick Start
- Features
  - [Adding items `todo` `deadline` `event`](#adding-items)
  - [Listing items `list`](#listing-items)
  - Marking items `mark` `unmark`
  - Finding items `find`
  - Deleting items `delete`
  - Exiting `bye`
- FAQ
- Known issues
- Command summary

## Adding items
### Todo item `todo`
Adds a Todo item with no deadline

Format: `todo ITEM`

Example:
- `todo Add personality to Yapmeister`

### Deadline item `deadline`
Adds a Deadline item with a deadline
Format: `deadline ITEM /by DEADLINE`
If `DEADLINE` is in the format `YYYY-MM-DD` format, will be converted to `ISO_WEEK_DATE` format

Examples:
- `deadline end this /by 2020-01-01`
    - Date is converted to `2020-W01-3`
- `deadline end that /by tomorrow`
    - Date is left as `tomorrow`

### Event item `event`
Adds an Event item with a start and end

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

![Screenshot of a mark example usage](https://github.com/BlazeChron/ip/blob/A-Release/docs/mark-example.png?raw=true)

## Finding items `find`
Finds items and lists them

Format: `find [-e] SEARCH_TERM`

`-e` flag will force an exact search for the term

## Deleting items `delete`
## Exiting `bye`
## FAQ
## Known issues
## Command summary

[^1]: Great Assignment Calamity
A natural disaster caused by the mass compression of long term assignment deadlines, claiming the 
grades of at least one person, probably.