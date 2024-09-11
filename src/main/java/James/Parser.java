package james;

import java.time.LocalDateTime;
import java.lang.NumberFormatException;
import java.lang.IndexOutOfBoundsException;

/**
 * Handles parsing and executing user commands.
 * <p>
 * The Parser class interprets user input, performs the necessary actions
 * on the TaskList and Storage, and interacts with the Ui
 * to provide feedback to the user.
 * </p>
 */
class Parser {
    private Ui ui;
    private Storage storage;

    /**
     * Creates a new Parser instance.
     *
     * @param ui The Ui instance used for user interactions
     * @param storage The Storage instance used for loading and saving tasks
     */
    public Parser(Ui ui, Storage storage) {
        assert ui != null : "UI failed to load into parser";
        assert storage != null : "storage failed to load into parser";
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parses the user command and executes the appropriate action.
     *
     * @param command The user command to be parsed and executed
     * @param taskList The TaskList instance to modify based on the command
     * @return A string response based on the executed command, such as task details, confirmation messages,
     *         or the exit message when the command is "bye".
     * @throws JamesException If an error occurs during command execution
     */
    public String parseAndExecute(String command, TaskList taskList) throws JamesException {
        assert command != null : "empty input";
        assert taskList != null : "failed to pass in task list to parser";
        String[] words = command.split(" ");
        String action = words[0].toLowerCase();
        StringBuilder stringBuilder = new StringBuilder();

        switch (action) {
        case "bye":
            return handleBye();

        case "list":
            return handleList(taskList);

        case "mark":
            return handleMark(words, taskList, stringBuilder);

        case "unmark":
            return handleUnmark(words, taskList, stringBuilder);

        case "todo":
            return handleTodo(command, taskList, stringBuilder);

        case "deadline":
            return handleDeadline(command, taskList,stringBuilder);

        case "event":
            return handleEvent(command, taskList, stringBuilder);

        case "delete":
            return handleDelete(words, taskList, stringBuilder);

        case "find":
            return handleFind(words,taskList, stringBuilder);

        default:
            throw new CommandNotFoundException("Sorry! I don't understand what you mean by (" + command + "). " +
                    "Please try a different command!");
        }
    }

    /**
     * Handles the "bye" command, which exits the application.
     *
     * @return A string response with the exit message.
     */
    public String handleBye() {
        return ui.showExitMessage();
    }

    /**
     * Handles the "list" command, which lists all tasks in the TaskList.
     *
     * @param taskList The TaskList instance containing all tasks.
     * @return A string response with the list of all tasks.
     */
    public String handleList(TaskList taskList) {
        return taskList.printTasks();
    }

    /**
     * Handles the "mark" command, which marks a specified task as done.
     *
     * @param words The array of command words, including the task number.
     * @param taskList The TaskList instance containing tasks to be modified.
     * @param stringBuilder A StringBuilder instance for building the response string.
     * @return A string response indicating the task has been marked as done.
     * @throws JamesException If the task number is missing or invalid.
     */
    public String handleMark(String[] words, TaskList taskList, StringBuilder stringBuilder) throws JamesException {
        if (words.length < 2) {
            throw new IncorrectIdentifierException("Oops! Looks like you missed out the task number. " +
                    "Please enter the task number after the command");
        }
        try {
            int markTaskNum = Integer.parseInt(words[1]);
            taskList.markTask(markTaskNum - 1);
            stringBuilder.append("Task marked as done:\n");
            stringBuilder.append(taskList.getTask(markTaskNum - 1).printTask());
            storage.saveTasks(taskList.getTasks());
            return stringBuilder.toString();
        } catch (NumberFormatException e) {
            throw new IncorrectIdentifierException("Oops! Please enter a number after the command");
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectIdentifierException("Oops! Please enter a valid task number after the command");
        }
    }

    /**
     * Handles the "unmark" command, which marks a specified task as not done.
     *
     * @param words The array of command words, including the task number.
     * @param taskList The TaskList instance containing tasks to be modified.
     * @param stringBuilder A StringBuilder instance for building the response string.
     * @return A string response indicating the task has been marked as not done.
     * @throws JamesException If the task number is missing or invalid.
     */
    public String handleUnmark(String[] words, TaskList taskList, StringBuilder stringBuilder) throws JamesException {
        if (words.length < 2) {
            throw new IncorrectIdentifierException("Oops! Looks like you missed out the task number. " +
                    "Please enter the task number after the command");
        }
        try {
            int unmarkTaskNum = Integer.parseInt(words[1]);
            taskList.unmarkTask(unmarkTaskNum - 1);
            stringBuilder.append("Task marked as not done:\n");
            stringBuilder.append(taskList.getTask(unmarkTaskNum - 1).printTask());
            storage.saveTasks(taskList.getTasks());
            return stringBuilder.toString();
        } catch (NumberFormatException e) {
            throw new IncorrectIdentifierException("Oops! Please enter a number after the command");
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectIdentifierException("Oops! Please enter a valid task number after the command");
        }
    }

    /**
     * Handles the "todo" command, which adds a new to-do task to the TaskList.
     *
     * @param command The full user command, including the task description.
     * @param taskList The TaskList instance to which the task will be added.
     * @param stringBuilder A StringBuilder instance for building the response string.
     * @return A string response confirming the to-do task has been added.
     * @throws MissingDescriptionException If the task description is missing.
     */
    public String handleTodo(String command, TaskList taskList, StringBuilder stringBuilder)
            throws MissingDescriptionException{
        String todoDescription = command.substring(4).trim();
        Task todoTask = new Todo(todoDescription, false);
        taskList.addTask(todoTask);
        stringBuilder.append("Task added:\n" + todoTask.printTask() + "\n");
        stringBuilder.append(String.format("Now you have %d tasks in the list.", taskList.size()));
        storage.saveTasks(taskList.getTasks());
        return stringBuilder.toString();
    }

    /**
     * Handles the "deadline" command, which adds a new deadline task to the TaskList.
     *
     * @param command The full user command, including the task description and deadline.
     * @param taskList The TaskList instance to which the task will be added.
     * @param stringBuilder A StringBuilder instance for building the response string.
     * @return A string response confirming the deadline task has been added.
     * @throws MissingDescriptionException If the task description or deadline is missing.
     */
    public String handleDeadline(String command, TaskList taskList, StringBuilder stringBuilder)
            throws MissingDescriptionException{
        String deadlineDescription = command.substring(8, command.lastIndexOf("/by")).trim();
        String deadline = command.substring(command.lastIndexOf("/by") + 4).trim();
        Task deadlineTask = new Deadline(deadlineDescription, false, LocalDateTime.parse(deadline));
        taskList.addTask(deadlineTask);
        stringBuilder.append("Task added:\n" + deadlineTask.printTask() + "\n");
        stringBuilder.append(String.format("Now you have %d tasks in the list.", taskList.size()));
        storage.saveTasks(taskList.getTasks());
        return stringBuilder.toString();
    }

    /**
     * Handles the "event" command, which adds a new event task to the TaskList.
     *
     * @param command The full user command, including the task description, start time, and end time.
     * @param taskList The TaskList instance to which the task will be added.
     * @param stringBuilder A StringBuilder instance for building the response string.
     * @return A string response confirming the event task has been added.
     * @throws MissingDescriptionException If the task description, start time, or end time is missing.
     */
    public String handleEvent(String command, TaskList taskList, StringBuilder stringBuilder)
            throws MissingDescriptionException{
        String eventDescription = command.substring(5, command.lastIndexOf("/from")).trim();
        String start = command.substring(command.lastIndexOf("/from") + 6,
                command.lastIndexOf("/to")).trim();
        String end = command.substring(command.lastIndexOf("/to") + 4).trim();
        Task eventTask = new Event(eventDescription, false, LocalDateTime.parse(start),
                LocalDateTime.parse(end));
        taskList.addTask(eventTask);
        stringBuilder.append("Task added:\n" + eventTask.printTask() + "\n");
        stringBuilder.append(String.format("Now you have %d tasks in the list.", taskList.size()));
        storage.saveTasks(taskList.getTasks());
        return stringBuilder.toString();
    }

    /**
     * Handles the "delete" command, which removes a specified task from the TaskList.
     *
     * @param words The array of command words, including the task number.
     * @param taskList The TaskList instance containing tasks to be deleted.
     * @param stringBuilder A StringBuilder instance for building the response string.
     * @return A string response confirming the task has been removed.
     * @throws JamesException If the task number is missing or invalid.
     */
    public String handleDelete(String[] words, TaskList taskList, StringBuilder stringBuilder) throws JamesException {
        if (words.length < 2) {
            throw new IncorrectIdentifierException("Oops! Looks like you missed out the task number. " +
                    "Please enter the task number after the command");
        }
        try {
            int deleteTaskNum = Integer.parseInt(words[1]);
            stringBuilder.append("Task removed:\n" + taskList.getTask(deleteTaskNum - 1).printTask() + "\n");
            taskList.deleteTask(deleteTaskNum - 1);
            stringBuilder.append(String.format("Now you have %d tasks in the list.", taskList.size()));
            storage.saveTasks(taskList.getTasks());
            return stringBuilder.toString();
        } catch (NumberFormatException e) {
            throw new IncorrectIdentifierException("Oops! Please enter a number after the command");
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectIdentifierException("Oops! Please enter a valid task number after the command");
        }
    }

    /**
     * Handles the "find" command, which searches for tasks containing the specified keyword.
     *
     * @param words The array of command words, including the keyword to search for.
     * @param taskList The TaskList instance containing tasks to be searched.
     * @param stringBuilder A StringBuilder instance for building the response string.
     * @return A string response listing the tasks that match the keyword.
     */
    public String handleFind(String[] words, TaskList taskList, StringBuilder stringBuilder) {
        String keyWord = words[1];
        stringBuilder.append("Here are the matching tasks in your list:\n");
        stringBuilder.append(taskList.printMatchingTasks(keyWord));
        return stringBuilder.toString();
    }
}
