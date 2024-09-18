# ChatterBox User Guide

![GUI Screenshot](Ui.png)


ChatterBox is a user-friendly chat application designed to help users efficiently manage their tasks anf keep track of items they own. With its intuitive interface, ChatterBox allows users to easily add, organise, and retreive task-related information and merchandise details. Whether you’re looking to streamline your to-do lists or maintain an inventory of personal items, ChatterBox offers a seamless and interactive way to stay organized and productive.

# Tasks

ChatterBox allows you to add various types of tasks to your to-do list. Below are the three types of tasks you can add and how to do so.

## ToDO
The `ToDO` task is used to add a new item or action to your list. This type of task is ideal for tracking individual actions or items that need attention or completion.

Command Format: `todo DESCRIPTION`

Example: `todo buy groceries`

Expected output
```
buy groceries is added to your list
[T][] buy groceries
Now you have 1 tasks in your list.
```
## Deadline
The `Deadline` task allows you to set a specific date and time by which a task must be completed. This helps in managing tasks that have strict deadlines.

Command Format: `deadline DESCRIPTION /by DATE_TIME`

Example: `deadline return books /by 2024-09-23 23:59 `

Expected output
```
return books is added to your list
[D][] return books (by: Sept 23 2024 11:59 pm)
Now you have 2 tasks in your list.
```
## Event
The `Event`  is used to schedule an event with a start and end time. This is useful for tracking events or appointments.

Command Format: `event DESCRIPTION /from DATE_TIME /to DATE_TIME`

Example: `event team meeting /from 2024-09-30 14:00 /to 2024-09-30 16:00`
 
Expected output
```
team meeting is added to your list
[E][] team meeting (from: Sept 30 2024 02:00 pm to: Sept 30 2024 04:00 pm)
Now you have 3 tasks in your list.
```

# Merchandise
ChatterBox allows you to manage and keep track of your personal items or merchandise. You can add new items to your inventory, update details, and keep an organized list of your possessions.

The `add merchandise`  feature lets you add a new item to your inventory. This is useful for tracking items you own, such as gadgets, clothing, or other personal belongings.

Command Format: `add merchandise, ID, NAME, DESCRIPTION`

Example: `add merchandise, 2103, iPhone 14 Pro,  128GB and Purple`

Expected output
```
iPhone 14 Pro is added to your list

ID: 2103
Name: iPhone 14 Pro
Description: 128GB and Purple
Now you have 1 merchandise in your list.
```

# Features
## Task Management
- **Add Tasks:** Add new tasks of the three different types (ToDo, Deadline, Event) to your to-do list with specific details.
    - Command Format: `deadline DESCRIPTION /by DATE_TIME`
    - Example: `deadline return books /by 2024-09-23 23:59 `


- **Delete Tasks:** Remove tasks from your list.
    - Command Format: `delete TASK_NUMBER`
    - Example: `delete 2`


- **Mark Tasks as Done:** Mark tasks as completed.
    - Command Format: `mark TASK_NUMBER`
    - Example: `mark 5`


- **Mark Tasks as Not Done:** Unmarks task as completed.
    - Command Format: `unmark TASK_NUMBER`
    - Example: `unmark 3`


- **View Tasks:** View all tasks in your list, with options to filter by status (e.g., completed, pending).
    - Command Format: `delete TASK_NUMBER`
    - Example: `delete 2`


- **Find Task:** Searches for tasks based on keywords.
    - Command Format: `find KEYWORD`
    - Example: `find books`

## Merchandise Management
- **Add Merchandise:** Adds a new item to the merchandise list with details.
    - Command Format: `add merchandise, ID, NAME, DESCRIPTION`
    - Example: `add merchandise, 2103, iPhone 14 Pro,  128GB and Purple`


- **Modify Merchandise Description:** Updates the description of an existing merchandise item.
    - Command Format: `modify merchandise, ID, NEW_DESCRIPTION`
    - Example: `modify merchandise, 10010, used to eat food`



- **Modify Merchandise Name:** Updates the name of an existing merchandise item.
    - Command Format: `modify merchandise, ID, NEW_NAME`
    - Example: `modify merchandise, 00340, Chopsticks`


- **Display Merchandise:** Shows the list of merchandise items.
    - Command Format: `merchandise`
    - Example: `merchandise`


- **Search Merchandise:** Searches for merchandise items based on the keyword.
    - Command Format: `search merchandise, KEYWORD`
    - Example: `search merchandise, Laptop`


- **Remove Merchandise:** Deletes a merchandise item from the list
    - Command Format: `remove MERCHANDISE_NUMBER`
    - Example: `remove 10`

## Error Handling
- **Graceful Error Messages:** Provides user-friendly error messages for unrecognized commands or issues.
- **Fallback Responses:** Guides users to correct actions if an unknown command is entered.


## Acknowledgments

I would like to express my gratitude to the following resources and individuals for their invaluable contributions and support during the development of ChatterBox:

1. **SDE Website**: For providing the inspiration and guidance for designing the graphical user interface (GUI). The resources and examples from the website were instrumental in shaping the application's look and feel.

2. **ChatGPT**: For offering guidance on Git instructions, assisting with Javadocs, and helping with various README sections. The support provided was crucial in managing version control, documenting the project, and improving development practices.

3. **Refactored Code for Merchandise Increment**: For adapting code from the tasks portion to enhance the merchandise feature.

4. **AI Tools**: For addressing specific doubts and providing clarity on various technical aspects throughout the development process. The insights gained were valuable for solving complex problems.

5. **Gemini**: For generating the images used in the GUI for ChatterBox and the user.

Thank you to everyone who contributed to making ChatterBox a successful project!

