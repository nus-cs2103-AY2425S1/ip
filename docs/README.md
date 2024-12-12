# Xizi Chatbot - Task Manager Bot

<div style="background-color:#f0f8ff; padding:15px; border-radius:10px;">
  <h2 style="color:#2c3e50;">Overview</h2>
  <p>Welcome to the <strong>Xizi Chatbot</strong>! Xizi is a Java-based personal assistant chatbot that helps you manage tasks such as todos, deadlines, and events. With Xizi, you can tag tasks, mark them as done, search by keyword or tag, and much more!</p>
</div>

## How to use Xizi bot?
Navigate to the Releases page, download the latest JAR and run `java -jar xizi.jar` in the terminal to start the bot.
Once the bot is running, you can interact with it by typing commands in the console. Below are the commands and their usage:

## Commands
1. `list`
   Description: Displays all tasks in your list.
   Usage:
   `list`
2. `todo <task_description>`
   Description: Adds a new 'todo' task.
   Example:
   `todo read a book`
3. `deadline <task_description> /by <deadline>`
   Description: Adds a new task with a deadline.
   Example:
   `deadline submit report /by 20/08/2024 1800`
4. `event <task_description> /from <start_time> /to <end_time>`
   Description: Adds a new event task with a specified start and end time.
   Example:
   `event project meeting /from 15/08/2024 1400 /to 15/08/2024 1600`
5. `mark <task_number>`
   Description: Marks the specified task as completed.
   Example:
   `mark 3`
6. `unmark <task_number>`
   Description: Unmarks the specified task as not completed.
   `unmark 3`
7. `delete <task_number>`
   Description: Deletes the specified task.
   Example:
   `delete 3`
8. `bye`
   Description: Exits the program. 
   Example:
   `bye`
9. `help`
   Description: Displays the help message with all available commands.
   `help`
10. `list on <date> <time>`
    Description: Displays all tasks scheduled for a specific date and time.
    Example:
    `list on 15/08/2024 1400`
11. `find <keyword>`
    Description: Displays all tasks that contain the given keyword.
    Example:
    `find report`
12. `delete done`
    Description: Deletes all completed tasks from the task list.
    Usage:
    `delete done`
13. `tag <task_number> #<tag>`
    Description: Assigns a tag to the specified task. The '#' symbol is optional.
    Example:
    `tag 2 #urgent`
14. `remove tag <task_number> #<tag>`
    Description: Removes a tag from the specified task. The # symbol is optional.
    Example:
    `remove tag 2 #urgent`
15. `search tag #<tag>`
    Description: Searches for all tasks with the specified tag. The # symbol is optional.
    Example:
    `search tag #fun`

## Additional Notes
- Date and time should be provided in the format d/M/yyyy HHmm (e.g., 20/08/2024 1800).
- The task number refers to the task’s position in the list, as shown by the list command.
- The tag and remove tag commands allow you to categorize tasks for easier search and management.

## Exiting the bot
You can exit the bot at any time by typing `bye`. This will gracefully close the application.