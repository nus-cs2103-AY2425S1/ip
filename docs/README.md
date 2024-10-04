# Donk User Guide

![Screenshot of App UI](./Ui.png)

Donk is an ultra intelligent chatbot that can help you to organize your life. Sort it out now with Donk!

# Installation

Make sure you have the Java FX version of Zulu17, which can be found [here](https://www.azul.com/downloads/?package=jdk#zulu)

From [releases](https://github.com/AppleJem/ip/releases), downloaded the latest version of Donk.jar.

Then, run it using the command `java -jar donk.jar`

# Features

## Adding ToDos

Format: `todo <DESCRIPTION>`

Adds todo with a description

## Adding Deadline Task

Format: `deadline <DESCRIPTION> /by <DD-MM-YYYY HHmm>`

Adds a task that has a dateline

## Adding Event

Format: `event <DESCRIPTION> /start <DD-MM-YYYY hhmm> /end <DD-MM-YYYY hhmm>`

Adds an event which has a start and end date

## List all items

Format: `list`

Lists all items added so far

Optional parameter: `sorted` (input: `list sorted`)

List the items sorted by their dates, for events, the start dates are used. All todos (which have no dates) will be listed first

## Search for specific item

Format: `find <QUERY STRING>`

example: `find hw`

Lists all items which contain the query string. 

## Mark and Unmark items

Format: `mark/unmark <INDEX>`

example: `mark 1`, `unmark 9`

Mark and unmark specific items as done or undone. This will be reflected in the list. Index of items can be seen using `list` command

## Exiting and saving

format: `bye`

Exits the program and saves the current list of items, so you items are saved the next time you log in!
