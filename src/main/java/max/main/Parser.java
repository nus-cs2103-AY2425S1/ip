package max.main;

import max.exception.MaxException;
import max.task.Deadline;
import max.task.Event;
import max.task.Task;
import max.task.TaskList;
import max.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Parser class is responsible for interpreting and executing user commands.
 * It processes the input from the user, interacts with the TaskList, and handles
 * task-related operations like adding, deleting, and marking tasks.
 */
public class Parser {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a Parser object with the given TaskList, Ui, and Storage components.
     *
     * @param taskList The list of tasks to be managed.
     * @param ui The UI component used for interacting with the user.
     * @param storage The storage component for saving and loading tasks.
     */
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Default constructor for the Parser class.
     */
    public Parser() {

    }

    /**
     * Parses the input text and performs the corresponding action.
     * It handles various commands such as 'bye', 'list', 'todo', 'deadline', etc.
     *
     * @param text the input command from the user
     * @return true if the command is 'bye', indicating the application should exit, false otherwise
     * @throws MaxException if the input command is invalid
     */
    public boolean parseText(String text) throws MaxException {

        assert taskList != null : "Task list is not initialized.";
        assert ui != null : "UI is not initialized.";
        assert storage != null : "Storage is not initialized.";

        try {
            if (isByeCommand(text)) {
                return handleByeCommand();
            } else if (isSimpleCommand(text)) {
                handleSimpleCommand(text);
            } else if (isIndexCommand(text)) {
                handleIndexCommand(text);
            } else if (isTaskCommand(text)) {
                handleTaskCommand(text);
            } else if (isFindCommand(text)) {
                handleFindCommand(text);
            } else if (isTagCommand(text)) {
                handleTagCommand(text);
            } else {
                throw new MaxException("What does that mean?:( Type 'help' to know what to type.");
            }
            this.storage.saveTasks(taskList.getTasks());
        } catch (MaxException e) {
            ui.printToMax("\t " + e.getMessage());
        }

        return false;

    }

    /**
     * Handles the 'bye' command.
     * It outputs the goodbye message and signals that the application should exit.
     *
     * @return true to indicate the application should exit
     */
    private boolean handleByeCommand() {
        ui.printBye();
        return true;
    }

    /**
     * Checks if the given command is 'bye'.
     *
     * @param text the input command
     * @return true if the command is 'bye', false otherwise
     */
    private static boolean isByeCommand(String text) {
        return text.equals("bye");
    }

    /**
     * Checks if the given command is a simple command ('hi', 'hello', 'list', or 'help').
     *
     * @param text the input command
     * @return true if the command is a simple command, false otherwise
     */
    private boolean isSimpleCommand(String text) {
        return text.equals("hi") || text.equals("hello") || text.equals("list") || text.equals("help");
    }

    /**
     * Executes a simple command such as 'hi', 'hello', 'list', or 'help'.
     *
     * @param text the input command
     */
    private void handleSimpleCommand(String text) {
        switch (text) {
        case "hi":
        case "hello":
            ui.printHello();
            break;
        case "list":
            handleList();
            break;
        case "help":
            handleHelp();
            break;
        }
    }

    /**
     * Checks if the given command is an index-based command ('mark', 'unmark', or 'delete').
     *
     * @param text the input command
     * @return true if the command is an index-based command, false otherwise
     */
    private boolean isIndexCommand(String text) {
        return text.startsWith("mark") || text.startsWith("unmark") || text.startsWith("delete");
    }


    /**
     * Executes an index-based command ('mark', 'unmark', or 'delete').
     * It extracts the index and performs the appropriate action.
     *
     * @param text the input command
     * @throws MaxException if the index is invalid
     */
    private void handleIndexCommand(String text) throws MaxException {
        int index = Integer.parseInt(text.split(" ")[1]) - 1;
        if (text.startsWith("mark")) {
            handleMark(index);
        } else if (text.startsWith("unmark")) {
            handleUnmark(index);
        } else if (text.startsWith("delete")) {
            handleDelete(index);
        }
    }

    /**
     * Checks if the given command is a task-based command ('todo', 'deadline', or 'event').
     *
     * @param text the input command
     * @return true if the command is a task-based command, false otherwise
     */
    private boolean isTaskCommand(String text) {
        return text.startsWith("deadline") || text.startsWith("todo") || text.startsWith("event");
    }

    /**
     * Executes a task-based command ('todo', 'deadline', or 'event').
     *
     * @param text the input command
     * @throws MaxException if the task command is invalid
     */
    private void handleTaskCommand(String text) throws MaxException {
        if (text.startsWith("deadline")) {
            handleDeadline(text);
        } else if (text.startsWith("todo")) {
            handleTodo(text);
        } else if (text.startsWith("event")) {
            handleEvent(text);
        }
    }

    /**
     * Checks if the given command is a find command ('find').
     *
     * @param text the input command
     * @return true if the command is a find command, false otherwise
     */
    private boolean isFindCommand(String text) {
        return text.startsWith("find");
    }

    /**
     * Executes a find command to search for tasks matching the given keyword.
     *
     * @param text the input command
     * @throws MaxException if the find command is invalid
     */
    private void handleFindCommand(String text) throws MaxException {
        String toFind = text.replaceFirst("find", "").trim();
        handleFind(toFind);
    }

    /**
     * Checks if the given command is a tag-based command ('tag', 'untag', or 'searchtag').
     *
     * @param text the input command
     * @return true if the command is a tag-based command, false otherwise
     */
    private boolean isTagCommand(String text) {
        return text.startsWith("tag") || text.startsWith("untag") || text.startsWith("searchtag");
    }

    /**
     * Executes a tag-based command ('tag', 'untag', or 'searchtag').
     *
     * @param text the input command
     * @throws MaxException if the tag command is invalid
     */
    private void handleTagCommand(String text) throws MaxException {
        if (text.startsWith("tag")) {
            handleTag(text);
        } else if (text.startsWith("untag")) {
            handleUntag(text);
        } else if (text.startsWith("searchtag")) {
            handleSearchtag(text);
        }
    }

    /**
     * Handles searching for tasks by a specific tag.
     * The command text should be in the format "searchtag [tag]".
     * The method extracts the tag from the command and searches for tasks with that tag.
     *
     * @param text The command text containing the tag to search for.
     * @throws MaxException If the command text does not contain a tag.
     */
    private void handleSearchtag(String text) throws MaxException {
        String tag = text.replaceFirst("searchtag", "").trim();

        if (tag.isEmpty()) {
            throw new MaxException("Tag cannot be empty. Please provide a tag to search.");
        }

        List<Task> results = taskList.searchByTag(tag);

        ui.printTagSearchResults(results);
    }

    /**
     * Handles adding a tag to a task based on user input.
     * The input text should be in the format "tag [index] [tag]".
     * The method extracts the task index and tag from the input, then adds the tag to the specified task.
     *
     * @param text The command text containing the task index and the tag to add.
     * @throws MaxException If the index cannot be parsed as an integer.
     * @throws MaxException If the task index is out of bounds.
     * @throws MaxException If the input does not contain enough parts or if the tag is empty.
     */
    private void handleTag(String text) throws MaxException {
        String[] parts = text.split(" ", 3);

        if (parts.length < 3) {
            throw new MaxException("Insufficient command. Use the format: tag [index] [tag].");
        }

        int index;
        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new MaxException("Invalid index format. Please provide a valid integer index.");
        }

        String tag = parts[2].trim();
        if (tag.isEmpty()) {
            throw new MaxException("Tag cannot be empty. Please provide a tag to add.");
        }

        Task task = taskList.getTask(index);
        task.addTag(tag);

        ui.printToMax("\t Added tag #" + tag + " to task " + (index + 1));
    }

    /**
     * Handles removing a tag from a task based on user input.
     * The input text should be in the format "untag [index] [tag]".
     * The method extracts the task index and tag from the input, then removes the tag from the specified task.
     *
     * @param text The command text containing the task index and the tag to remove.
     * @throws MaxException If the index cannot be parsed as an integer.
     * @throws MaxException If the task index is out of bounds.
     * @throws MaxException If the input does not contain enough parts or if the tag is empty.
     */
    private void handleUntag(String text) throws MaxException {
        // Parse the command to extract the index and tag
        String[] parts = text.split(" ", 3);

        if (parts.length < 3) {
            throw new MaxException("Insufficient command parts. Use the format: untag [index] [tag].");
        }

        int index;
        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new MaxException("Invalid index format. Please provide a valid integer index.");
        }

        String tag = parts[2].trim();
        if (tag.isEmpty()) {
            throw new MaxException("Tag cannot be empty. Please provide a tag to remove.");
        }

        Task task = taskList.getTask(index);
        task.removeTag(tag);
        ui.printToMax("\t Removed tag #" + tag + " from task " + (index + 1));
    }

    /**
     * Handles the "help" command to display help information.
     */
    private void handleHelp() {
        ui.printHelp();
    }

    /**
     * Handles the "list" command to display all tasks.
     * <p>
     * Calls the Ui component to print the entire task list, showing all current tasks.
     * </p>
     */
    private void handleList() {
        ui.printList(false);
        ui.list(taskList.getTasks());
    }

    /**
     * Handles the "unmark" command to mark a task as not done.
     * <p>
     * Retrieves the task from the task list based on the provided index,
     * marks the task as not done, and prints the updated task status using the Ui component.
     * </p>
     *
     * @param index The index of the task in the task list to be marked as not done.
     */
    private void handleUnmark(int index) {
        Task task = taskList.getTask(index);
        task.markNotDone();
        ui.printMarkNotDone(task);
    }

    /**
     * Handles the "mark" command to mark a task as done.
     * <p>
     * Retrieves the task from the task list based on the provided index,
     * marks the task as done, and prints the updated task status using the Ui component.
     * </p>
     *
     * @param index The index of the task in the task list to be marked as done.
     */
    private void handleMark(int index) {
        Task task = taskList.getTask(index);
        task.markDone();
        ui.printMarkDone(task);
    }

    /**
     * Handles the "delete" command to remove a task from the task list.
     * <p>
     * Retrieves the task from the task list based on the provided index, removes it from the list,
     * and prints a message using the Ui component to confirm the deletion and display the remaining task count.
     * </p>
     *
     * @param index The index of the task in the task list to be deleted.
     * @throws MaxException If there is an issue deleting the task.
     */
    private void handleDelete(int index) throws MaxException {
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
        ui.printDeleteTask(task, taskList.getSize());
    }

    /**
     * Handles the "find" command to search for tasks containing the specified keyword.
     * <p>
     * Filters the tasks in the list based on the provided keyword and displays the matching tasks.
     * If the provided keyword is empty, a {@link MaxException} is thrown.
     * </p>
     *
     * @param text The keyword used to search for tasks. The search is case-sensitive and matches tasks containing this keyword.
     * @throws MaxException If the {@code text} is empty, indicating that no search keyword was provided.
     */
    private void handleFind(String text) throws MaxException {
        if (text.isEmpty()) {
            throw new MaxException("The provided filter for find cannot be empty.");
        }
        ArrayList<Task> filteredTasks = taskList.find(text);
        ui.printList(true);
        ui.list(filteredTasks);
    }

    /**
     * Handles the creation of a new Deadline task.
     *
     * @param text The user input text containing the task description and due date.
     * @throws MaxException If the task description or date format is invalid.
     */
    private void handleDeadline(String text) throws MaxException {
        String[] temp = text.replace("deadline ", "").split(" /by ");
        if (temp.length != 2) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
        checkTask(temp[0].trim());
        checkTask(temp[1].trim());

        LocalDateTime LDT = parseDate(temp[1]);
        Deadline deadline = (LDT != null) ? new Deadline(temp[0], LDT) : new Deadline(temp[0], temp[1]);
        taskList.addTask(deadline);
        ui.printTaskTypeAdded(deadline, taskList.getSize());
    }

    /**
     * Handles the creation of a new Event task.
     *
     * @param text The user input text containing the task description and event period.
     * @throws MaxException If the task description or event period is invalid.
     */
    private void handleEvent(String text) throws MaxException {
        String[] temp = text.replace("event ", "").split(" /from ");
        if (temp.length != 2) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
        checkTask(temp[0].trim());
        checkTask(temp[1].trim());

        Event event = new Event(temp[0], temp[1]);
        taskList.addTask(event);
        ui.printTaskTypeAdded(event, taskList.getSize());
    }

    /**
     * Handles the creation of a new Todo task.
     *
     * @param text The user input text containing the task description.
     * @throws MaxException If the task description is empty.
     */
    private void handleTodo(String text) throws MaxException {
        String temp = text.replace("todo", "").trim();
        checkTask(temp);

        Todo todo = new Todo(temp);
        taskList.addTask(todo);
        ui.printTaskTypeAdded(todo, taskList.getSize());
    }

    /**
     * Parses a date string into a LocalDateTime object.
     *
     * @param date The date string to be parsed.
     * @return A LocalDateTime object representing the parsed date.
     * @throws MaxException If the date format is invalid.
     */
    public LocalDateTime parseDate(String date) throws MaxException {
        DateTimeFormatter converter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(date, converter);
        } catch (DateTimeParseException e) {
            throw new MaxException("Invalid date format! Please use d/M/yyyy HHmm. "
                    + "For example, '2/12/2024 1800'");
        }
    }

    /**
     * Checks if the given task description is empty.
     *
     * @param todo The task description to be checked.
     * @throws MaxException If the task description is empty.
     */
    public void checkTask(String todo) throws MaxException {
        if (todo.isEmpty()) {
            throw new MaxException("Oh no!! The description of the task cannot be empty. :(");
        }
    }
}
