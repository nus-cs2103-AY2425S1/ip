package parser;

import exceptions.EmptyTaskException;
import exceptions.InvalidInputException;
import exceptions.TaskIndexOutOfBound;
import storage.Storage;
import task.*;
import ui.Ui;

import java.io.IOException;
import java.util.Arrays;

/**
 * The Parser class is responsible for parsing and executing user commands.
 * It interprets the user's input and interacts with the TaskList, Ui, and Storage
 * classes to perform the appropriate actions.
 */
public class Parser {

    /**
     * Parses and executes the user's command.
     *
     * @param command   The command entered by the user (e.g. Todo XXX).
     * @param taskList  The TaskList object that holds all the tasks.
     * @param ui        The Ui object that handles user interaction.
     * @param storage   The Storage object that handles saving and loading tasks from the file.
     * @throws InvalidInputException if the user enters an unrecognized command or provides invalid input.
     * @throws EmptyTaskException    if the user attempts to add a task without providing a description.
     * @throws TaskIndexOutOfBound   if the user provides an index for a task that does not exist.
     */
    public static void parseCommand(String command, TaskList taskList, Ui ui, Storage storage) throws InvalidInputException, EmptyTaskException, TaskIndexOutOfBound {
        String[] slicedStr = command.split(" ");
        String action = slicedStr[0];

        switch (action) {
            case "list":
                ui.showTaskList(taskList.getTasks());
                break;

            case "mark":
                handleMarkCommand(slicedStr, taskList, ui, storage, true);
                break;

            case "unmark":
                handleMarkCommand(slicedStr, taskList, ui, storage, false);
                break;

            case "todo":
                handleTodoCommand(slicedStr, taskList, ui, storage);
                break;

            case "deadline":
                handleDeadlineCommand(slicedStr, taskList, ui, storage);
                break;

            case "event":
                handleEventCommand(slicedStr, taskList, ui, storage);
                break;

            case "delete":
                handleDeleteCommand(slicedStr, taskList, ui, storage);
                break;

            default:
                throw new InvalidInputException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Handles the "mark" and "unmark" commands, marking or unmarking a task as done or not done.
     *
     * @param slicedStr  The array of strings representing the user's command.
     * @param taskList   The TaskList object that holds all the tasks.
     * @param ui         The Ui object that handles user interaction.
     * @param storage    The Storage object that handles saving tasks.
     * @param isMarking  true if marking the task as done; false if unmarking the task.
     * @throws TaskIndexOutOfBound    if the provided task index is out of bounds.
     * @throws InvalidInputException  if the user does not provide a task number.
     */
    private static void handleMarkCommand(String[] slicedStr, TaskList taskList, Ui ui, Storage storage, boolean isMarking) throws TaskIndexOutOfBound, InvalidInputException {
        if (slicedStr.length < 2) {
            throw new InvalidInputException("Please provide a task number to mark or unmark.");
        }

        int taskIndex = Integer.parseInt(slicedStr[1]) - 1;

        if (isMarking) {
            taskList.markTask(taskIndex);
            ui.showMarkTask(taskList.getTask(taskIndex));
        } else {
            taskList.unmarkTask(taskIndex);
            ui.showUnmarkTask(taskList.getTask(taskIndex));
        }

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (IOException e) {
            ui.showErrorMessage("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Handles the "todo" command, adding a new todo task to the task list.
     *
     * @param slicedStr  The array of strings representing the user's command.
     * @param taskList   The TaskList object that holds all the tasks.
     * @param ui         The Ui object that handles user interaction.
     * @param storage    The Storage object that handles saving tasks.
     * @throws EmptyTaskException  if the user does not provide a description for the todo task.
     */
    private static void handleTodoCommand(String[] slicedStr, TaskList taskList, Ui ui, Storage storage) throws EmptyTaskException {
        if (slicedStr.length < 2) {
            throw new EmptyTaskException("todo");
        }
        Todo newTodo = new Todo();
        newTodo.convertStringToTask(slicedStr);
        taskList.addTask(newTodo);
        ui.showAddTask(newTodo, taskList.getTasks().size());

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (IOException e) {
            ui.showErrorMessage("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Handles the "event" command, adding a new event task to the task list.
     *
     * @param slicedStr  The array of strings representing the user's command.
     * @param taskList   The TaskList object that holds all the tasks.
     * @param ui         The Ui object that handles user interaction.
     * @param storage    The Storage object that handles saving tasks.
     * @throws EmptyTaskException  if the user does not provide a description for the event task.
     */
    private static void handleDeadlineCommand(String[] slicedStr, TaskList taskList, Ui ui, Storage storage) throws EmptyTaskException {
        if (slicedStr.length < 2) {
            throw new EmptyTaskException("deadline");
        }

        try {
            Deadline newDeadline = new Deadline();
            newDeadline.convertStringToTask(slicedStr);
            taskList.addTask(newDeadline);
            ui.showAddTask(newDeadline, taskList.getTasks().size());
            storage.saveTasks(taskList.getTasks());
        } catch (IOException e) {
            ui.showErrorMessage("Error saving tasks: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showErrorMessage("Error saving tasks details: " + e.getMessage());
        }
    }

    /**
     * Handles the "event" command, adding a new event task to the task list.
     *
     * @param slicedStr  The array of strings representing the user's command.
     * @param taskList   The TaskList object that holds all the tasks.
     * @param ui         The Ui object that handles user interaction.
     * @param storage    The Storage object that handles saving tasks.
     * @throws EmptyTaskException  if the user does not provide a description for the event task.
     */
    private static void handleEventCommand(String[] slicedStr, TaskList taskList, Ui ui, Storage storage) throws EmptyTaskException {
        if (slicedStr.length < 2) {
            throw new EmptyTaskException("event");
        }
        try {
            Event newEvent = new Event();
            newEvent.convertStringToTask(slicedStr);
            taskList.addTask(newEvent);
            ui.showAddTask(newEvent, taskList.getTasks().size());
            storage.saveTasks(taskList.getTasks());
        } catch (IOException e) {
            ui.showErrorMessage("Error saving tasks: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showErrorMessage("Error saving tasks details. Please enter a valid description or date");
        }
    }

    /**
     * Handles the "delete" command, deleting a task from the task list.
     *
     * @param slicedStr  The array of strings representing the user's command.
     * @param taskList   The TaskList object that holds all the tasks.
     * @param ui         The Ui object that handles user interaction.
     * @param storage    The Storage object that handles saving tasks.
     * @throws TaskIndexOutOfBound    if the provided task index is out of bounds.
     * @throws InvalidInputException  if the user does not provide a task number.
     */
    private static void handleDeleteCommand(String[] slicedStr, TaskList taskList, Ui ui, Storage storage) throws TaskIndexOutOfBound, InvalidInputException {
        if (slicedStr.length < 2) {
                throw new InvalidInputException("Please provide a task number to delete.");
        }

        int taskIndex = Integer.parseInt(slicedStr[1]) - 1;
        Task deletedTask = taskList.getTask(taskIndex);
        taskList.deleteTask(taskIndex);
        ui.showDeleteTask(deletedTask, taskList.getTasks().size());

        try {
            storage.saveTasks(taskList.getTasks());
        } catch (IOException e) {
            ui.showErrorMessage("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Parses saved data from a file into a Task object.
     *
     * @param dataArr  The array of strings representing the saved data for a task.
     * @return The corresponding Task object, or null if the data is invalid.
     */
    public static Task parseSavedData(String[] dataArr) {
        Task task = null;
        switch (dataArr[0]) {
        case "T":
            task = new Todo();
            break;
        case "D":
            task = new Deadline();
            break;
        case "E":
            task = new Event();
            break;
        }
        if (task != null) {
            task.convertSavedDataToTask(dataArr);
        }
        return task;
    }
}
