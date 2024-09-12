package donna;

import java.util.List;

import donna.parse.ParsedCommand;
import donna.task.Deadline;
import donna.task.Event;
import donna.task.Task;
import donna.task.TaskList;
import donna.task.ToDo;

/**
 * Handles the execution of a parsed command.
 */
public class CommandHandler {
    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Constructs a CommandHandler instance that facilitates interaction
     * between the UI, task list and storage.
     *
     * @param ui The UI instance for Donna
     * @param tasks The TaskList for storing Donna's tasks
     * @param storage Storage instance responsible for storing and loading
     */
    public CommandHandler(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Processes a parsed command and executes it.
     *
     * @param result The parsed command containing user's inputs
     * @return The string response that will be displayed by the UI
     * @throws DonnaException If the commands are invalid
     */
    public String handleCommand(ParsedCommand result) throws DonnaException {
        String commandType = result.getCommandType();

        switch (commandType) {
        case "exit":
            return handleExit();
        case "list":
            return ui.getTaskList(tasks);
        case "mark":
            return handleMark(result.getArgument1());
        case "unmark":
            return handleUnmark(result.getArgument1());
        case "delete":
            return handleDelete(result.getArgument1());
        case "add":
            return handleAdd(result.getArgument1(), result.getArgument2());
        case "find":
            List<Task> foundTasks = tasks.searchTasks(result.getArgument1());
            return ui.findResults(foundTasks);
        default:
            throw DonnaException.invalidTaskType(commandType);
        }
    }

    /**
     * Handles the application's exit
     *
     * @return Donna's response confirming the marking of a task as done.
     * @throws DonnaException If the argument is not a valid task number.
     */
    private String handleExit() {
        this.storage.saveTasks(tasks);
        return this.ui.getGoodbyeMessage();
    }

    /**
     * Handles the marking of a task as done.
     *
     * @param taskNum S.No of the task to mark (index from 1).
     * @return Donna's response confirming the marking of a task as done.
     * @throws DonnaException If the argument is not a valid task number.
     */
    private String handleMark(String taskNum) throws DonnaException {
        try {
            int taskIdx = Integer.parseInt(taskNum) - 1;
            Task task = tasks.markTask(taskIdx, true);
            storage.saveTasks(tasks);
            return ui.getTaskMarkedMessage(task, true);
        } catch (NumberFormatException e) {
            return ui.getErrorMessage(DonnaException.invalidTaskNumber().getMessage());
        }
    }

    /**
     * Handles the marking of a task as not done.
     *
     * @param taskNum Index of the task to unmark (index from 1).
     * @return Donna's response confirming the marking of a task as not done.
     * @throws DonnaException If the argument is not a valid task number.
     */
    private String handleUnmark(String taskNum) throws DonnaException {
        try {
            int taskIdx = Integer.parseInt(taskNum) - 1;
            Task task = tasks.markTask(taskIdx, false);
            storage.saveTasks(tasks);
            return ui.getTaskMarkedMessage(task, false);
        } catch (NumberFormatException e) {
            return ui.getErrorMessage(DonnaException.invalidTaskNumber().getMessage());
        }
    }

    /**
     * Handles the deletion of a task.
     *
     * @param taskNum Index of the task to delete (index from 1).
     * @return Donna's response confirming the deletion of a task.
     * @throws DonnaException If the argument is not a valid task number.
     */
    private String handleDelete(String taskNum) throws DonnaException {
        try {
            int taskIndex = Integer.parseInt(taskNum) - 1;
            Task task = tasks.deleteTask(taskIndex);
            storage.saveTasks(tasks);
            return ui.getTaskDeletedMessage(task, tasks.getTaskCount());
        } catch (NumberFormatException e) {
            return ui.getErrorMessage(DonnaException.invalidTaskNumber().getMessage());
        }
    }

    /**
     * Handles the addition of a task to the list.
     *
     * @param type Type of the task (e.g., "todo", "deadline", "event").
     * @param description Description of the task.
     * @return Donna's response confirming the addition of a task.
     * @throws DonnaException If the type or description is invalid.
     */
    private String handleAdd(String type, String description) throws DonnaException {
        Task newTask;

        switch (type) {
        case "todo":
            newTask = new ToDo(description);
            break;
        case "deadline":
            String[] deadlineParts = description.split(" /by ", 2);
            if (deadlineParts.length != 2) {
                throw DonnaException.emptyDescription(type);
            }
            newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
            break;
        case "event":
            String[] eventParts = description.split(" /from ", 2);
            if (eventParts.length != 2) {
                throw DonnaException.emptyDescription(type);
            }
            String[] eventTimes = eventParts[1].split(" /to ", 2);
            if (eventTimes.length != 2) {
                throw DonnaException.emptyEventTime();
            }
            newTask = new Event(eventParts[0], eventTimes[0], eventTimes[1]);
            break;
        default:
            throw DonnaException.invalidTaskType(type);
        }
        tasks.addTask(newTask);
        storage.saveTasks(tasks);
        return ui.getTaskAddedMessage(newTask, tasks.getTaskCount());
    }

}
