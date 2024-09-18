package voidcat.parser;

import voidcat.storage.Storage;
import voidcat.task.*;
import voidcat.exception.VoidCatException;
import voidcat.ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

/**
 * Represents a parser that handles user commands and executes tasks accordingly.
 * The parser interprets commands entered by the user, delegates to appropriate
 * methods to process them, and generates the relevant response.
 */
public class Parser {

    /**
     * Parses and executes the given full user command.
     * It interprets the command and delegates to the corresponding handler method.
     * Saves tasks after a command.
     *
     * @param fullCommand The full command the user enters.
     * @param tasks The list of tasks the user manages.
     * @param ui The UI component responsible for user interactions.
     * @param storage The storage component responsible for saving and loading tasks.
     * @return A response message based on the user's command.
     * @throws VoidCatException If the command or arguments are invalid.
     * @throws IOException If saving tasks to the file fails.
     * @throws SecurityException If there's an error related to file security.
     * @throws IllegalArgumentException If invalid arguments are provided for commands.
     */
    public String parseAndExecute(String fullCommand, TaskList tasks, Ui ui, Storage storage) throws VoidCatException, IOException, SecurityException, IllegalArgumentException {
        String[] splitInput = fullCommand.split(" ", 2);
        String commandWord = splitInput[0];
        String arguments = splitInput.length > 1 ? splitInput[1] : "";
        String response = "";

        switch (commandWord.toLowerCase()) {
        case "bye":
            break;
        case "list":
            response = tasks.listTasks();
            break;
        case "delete":
            response = handleDeleteCommand(arguments, tasks, ui);
            break;
        case "mark":
            response = handleMarkCommand(arguments, tasks, ui);
            break;
        case "unmark":
            response = handleUnmarkCommand(arguments, tasks, ui);
            break;
        case "todo":
            response = handleAddToDoCommand(arguments, tasks, ui);
            break;
        case "deadline":
            response = handleAddDeadlineCommand(arguments, tasks, ui);
            break;
        case "event":
            response = handleAddEventCommand(arguments, tasks, ui);
            break;
        case "find":
            response = handleFindCommand(arguments, tasks);
            break;
        default:
            throw new VoidCatException("AH!! My apologies, I don't know what that means =T^T=");
        }
        
        storage.save(tasks);
        return response;
    }

    /**
     * Handles the delete command to remove a task.
     *
     * @param arguments The task number to delete.
     * @param tasks The task list to modify.
     * @param ui The UI component to display a message.
     * @return The response message after deleting the task.
     * @throws VoidCatException If the task number is invalid.
     */
    private String handleDeleteCommand(String arguments, TaskList tasks, Ui ui) throws VoidCatException {
        if (arguments.isBlank()) {
            throw new VoidCatException("Hm.. I dont know which to delete! Give me the task number please.");
        } else {
            int taskIndex = parseTaskIndex(arguments, tasks);
            Task removedTask = tasks.deleteTask(taskIndex);
            return ui.showDeleteTaskMessage(removedTask, tasks.size());
        }
    }

    /**
     * Handles the mark command to mark a task.
     *
     * @param arguments The task number to mark.
     * @param tasks The task list to modify.
     * @param ui The UI component to display a message.
     * @return The response message after marking the task.
     * @throws VoidCatException If the task number is invalid.
     */
    private String handleMarkCommand(String arguments, TaskList tasks, Ui ui) throws VoidCatException {
        if (arguments.isBlank()) {
            throw new VoidCatException("Hm.. I dont know which to mark! Give me the task number please.");
        } else {
            int taskIndex = parseTaskIndex(arguments, tasks);
            Task markedTask = tasks.markTaskAsDone(taskIndex);
            return ui.showMarkTaskMessage(markedTask);
        }
    }

    /**
     * Handles the unmark command to mark a task.
     *
     * @param arguments The task number to unmark.
     * @param tasks The task list to modify.
     * @param ui The UI component to display a message.
     * @return The response message after unmarking the task.
     * @throws VoidCatException If the task number is invalid.
     */
    private String handleUnmarkCommand(String arguments, TaskList tasks, Ui ui) throws VoidCatException {
        if (arguments.isBlank()) {
            throw new VoidCatException("Hm.. I dont know which to unmark! Give me the task number please.");
        } else {
            int taskIndex = parseTaskIndex(arguments, tasks);
            Task unmarkedTask = tasks.unmarkTaskAsDone(taskIndex);
            return ui.showUnmarkTaskMessage(unmarkedTask);
        }
    }

    /**
     * Handles the command to add a task to do.
     *
     * @param arguments The task description.
     * @param tasks The task list to modify.
     * @param ui The UI component to display a message.
     * @return The response message after adding the task.
     * @throws VoidCatException If the description is empty.
     */
    private String handleAddToDoCommand(String arguments, TaskList tasks, Ui ui) throws VoidCatException {
        if (arguments.isBlank()) {
            throw new VoidCatException("Meow! The description of a todo can't be empty!");
        } else {
            ToDo newTask = new ToDo(arguments);
            tasks.addTask(newTask);
            return ui.showAddTaskMessage(newTask, tasks.size());
        }
    }

    /**
     * Handles the command to add a task with a deadline.
     *
     * @param arguments The task description and the date time of the deadline.
     * @param tasks The task list to modify.
     * @param ui The UI component to display a message.
     * @return The response message after adding the task.
     * @throws VoidCatException If the description or deadline is empty.
     */
    private String handleAddDeadlineCommand(String arguments, TaskList tasks, Ui ui) throws VoidCatException {
        String[] details = arguments.split(" /by ");
        if (details.length != 2) {
            throw new VoidCatException("AH! The description of a deadline and the deadline can't be empty!\nRemember to put a /by after the description!");
        }
        try {
            Deadline newTask = new Deadline(details[0], details[1]);
            tasks.addTask(newTask);
            return ui.showAddTaskMessage(newTask, tasks.size());
        } catch (DateTimeParseException d) {
            throw new VoidCatException("AH! Check if:\n\t1. Input time is valid\n\t2. Format of the 24h date time is in yyyy-mm-dd hhmm");
        }
    }

    /**
     * Handles the command to add a task with a start and end time period.
     *
     * @param arguments The task description and the date time of the time period.
     * @param tasks The task list to modify.
     * @param ui The UI component to display a message.
     * @return The response message after adding the task.
     * @throws VoidCatException If the description or start and end date time is empty.
     */
    private String handleAddEventCommand(String arguments, TaskList tasks, Ui ui) throws VoidCatException, IllegalArgumentException {
        String[] details = arguments.split(" /from | /to ");
        if (details.length != 3) {
            throw new VoidCatException("AH! The description of an event, and the start and end time can't be empty!\nRemember to put a /from and /to after the description!");
        }
        try {
            LocalDateTime dfrom = LocalDateTime.parse(details[1], DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm"));
            LocalDateTime dto = LocalDateTime.parse(details[2], DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm"));
            if (dfrom.isAfter(dto)) {
                throw new VoidCatException("AH! The /from date time must be before the /after");
            }
            Event newTask = new Event(details[0], details[1], details[2], 0);
            tasks.addTask(newTask);
            return ui.showAddTaskMessage(newTask, tasks.size());
        } catch (DateTimeParseException d) {
            throw new VoidCatException("AH! Check if:\n\t1. Input time is valid\n\t2. Format of the 24h date time is in yyyy-mm-dd hhmm");
        }
    }

    /**
     * Handles the command to find a task using a keyword.
     *
     * @param arguments The keyword to find the task.
     * @param tasks The task list to check from.
     * @return The response message after finding the task.
     * @throws VoidCatException If the keyword is empty.
     */
    private String handleFindCommand(String arguments, TaskList tasks) throws VoidCatException {
        if (arguments.isBlank()) {
            throw new VoidCatException("Please provide a keyword to find.");
        }
        return tasks.findTasks(arguments);
    }

    /**
     * Parses the index input of a task and retrieves the respective task list index.
     * Ensures that the provided index is valid.
     *
     * @param argument The command index input argument.
     * @param tasks The task list to check the index against.
     * @return The task index.
     * @throws VoidCatException If the index is invalid.
     */
    private int parseTaskIndex(String argument, TaskList tasks) throws VoidCatException {
        try {
            int taskListIndex = Integer.parseInt(argument) - 1;
            if (taskListIndex < 0 || taskListIndex >= tasks.size()) {
                throw new VoidCatException("OOPS!!! The task number provided is invalid.");
            }
            return taskListIndex;
        } catch (NumberFormatException e) {
            throw new VoidCatException("OOPS!!! The task number provided is invalid.");
        }
    }
}
