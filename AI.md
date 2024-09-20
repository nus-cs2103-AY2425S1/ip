## AI Usage:
- Used Chatgpt to generate and improve some JavaDocs header comments.
    - Prompt:
  ```
  Help me generate a JavaDoc header comment for this method.
  ```
    - Output:
  ```
  JavaDoc header comments for the method.
  ```
    - Observations:
  ```
  While helpful, ChatGPT’s JavaDoc comments sometimes do not follow CS2103T's required format.

  A common issue encountered:
  In method header comments, the first sentence should begin with action words like "Adds...", "Removes...", or "Updates..." rather than "Add" or "Adding."
  For instance, in the `addTask()` method, ChatGPT's comment might incorrectly start with "Adding a task to the list."

  ```
- Used Copilot to autocomplete some code. 
    - Observation:
   ```
  Copilot effectively reduced time spent on boilerplate code.

  Example:
  When working on the `DeleteCommand` class, Copilot autocompleted the `execute()` method after seeing similar patterns in `MarkCommand` and `UnmarkCommand`. It suggested the correct removal of tasks from the task manager and returning feedback to the user.

  However, Copilot occasionally misunderstood context. For instance, when handling exceptions in `DeadlineCommand`, it suggested a generic error message, which needed modification to reflect the specific command context (e.g., using "Invalid date format" instead of a vague "Error occurred").

   ```