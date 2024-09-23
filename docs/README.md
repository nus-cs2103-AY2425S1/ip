# Donk User Guide

![Screenshot of App UI | width=350](./Ui.png)

Donk is an ultra intelligent chatbot that can help you to organize your life. Sort it out now with Donk!

# Features

## Adding ToDos

Format: `todo <DESCRIPTION>`

Adds todo with a description

## Adding Deadline Task

Format: `deadline <DESCRIPTION> /by <DD-MM-YYYY HHmm>`

Adds a task that has a dateline

## Adding Event

Format: `event <DESCRIPTION> /start <DD-MM-YYYY> /end <DD-MM-YYYY>`

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

Exits the program and saves the current list of items
