package repsmax;

import java.util.List;

/**
 * Parses user input and executes corresponding commands.
 * The <code>Parser</code> class is responsible for interpreting user input, managing tasks,
 * and interacting with the user interface and storage.
 */
public class Parser {

    // Constants for command keywords
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_FIND = "find";

    /**
     * Parses the user's input and executes the corresponding command.
     *
     * @param userInput The input string entered by the user.
     * @param tasks     The list of tasks to be managed.
     * @param ui        The user interface to interact with the user.
     * @param storage   The storage handler for saving and loading tasks.
     */
    public void parse(String userInput, TaskList tasks, Ui ui, Storage storage) {
        String[] splitInput = userInput.split(" ", 2);
        String command = splitInput[0];

        switch (command) {
            case COMMAND_LIST:
                handleListCommand(tasks, ui);
                break;

            case COMMAND_MARK:
                handleMarkCommand(splitInput, tasks, ui, storage);
                break;

            case COMMAND_UNMARK:
                handleUnmarkCommand(splitInput, tasks, ui, storage);
                break;

            case COMMAND_TODO:
                handleTodoCommand(splitInput, tasks, ui, storage);
                break;

            case COMMAND_DEADLINE:
                handleDeadlineCommand(splitInput, tasks, ui, storage);
                break;

            case COMMAND_EVENT:
                handleEventCommand(splitInput, tasks, ui, storage);
                break;

            case COMMAND_DELETE:
                handleDeleteCommand(splitInput, tasks, ui, storage);
                break;

            case COMMAND_BYE:
                handleByeCommand(tasks, ui, storage);
                break;

            case COMMAND_FIND:
                handleFindCommand(splitInput, tasks, ui);
                break;

            default:
                ui.showError("Unknown command: " + userInput);
                break;
        }
    }

    /**
     * Handles the "list" command, displaying all tasks.
     *
     * @param tasks The list of tasks to display.
     * @param ui    The user interface to interact with the user.
     */
    private void handleListCommand(TaskList tasks, Ui ui) {
        ui.showLine();
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessage((i + 1) + "." + tasks.get(i));
        }
        ui.showLine();
    }

    /**
     * Handles the "mark" command, marking a task as done.
     *
     * @param splitInput The user's input split into command and arguments.
     * @param tasks      The list of tasks to be managed.
     * @param ui         The user interface to interact with the user.
     * @param storage    The storage handler for saving the updated tasks.
     */
    private void handleMarkCommand(String[] splitInput, TaskList tasks, Ui ui, Storage storage) {
        try {
            int markIndex = Integer.parseInt(splitInput[1]) - 1;
            if (markIndex >= 0 && markIndex < tasks.size()) {
                tasks.get(markIndex).setDone();
                ui.showMessage("Nice! I've marked this task as done:\n" + tasks.get(markIndex));
                storage.save(tasks);
            } else {
                ui.showError("OOPS!!! The task number is out of range.");
            }
        } catch (NumberFormatException e) {
            ui.showError("OOPS!!! The task number must be an integer.");
        }
    }

    /**
     * Handles the "unmark" command, marking a task as not done.
     *
     * @param splitInput The user's input split into command and arguments.
     * @param tasks      The list of tasks to be managed.
     * @param ui         The user interface to interact with the user.
     * @param storage    The storage handler for saving the updated tasks.
     */
    private void handleUnmarkCommand(String[] splitInput, TaskList tasks, Ui ui, Storage storage) {
        try {
            int unmarkIndex = Integer.parseInt(splitInput[1]) - 1;
            if (unmarkIndex >= 0 && unmarkIndex < tasks.size()) {
                tasks.get(unmarkIndex).setUndone();
                ui.showMessage("OK, I've marked this task as not done yet:\n" + tasks.get(unmarkIndex));
                storage.save(tasks);
            } else {
                ui.showError("OOPS!!! The task number is out of range.");
            }
        } catch (NumberFormatException e) {
            ui.showError("OOPS!!! The task number must be an integer.");
        }
    }

    /**
     * Handles the "todo" command, adding a new ToDo task.
     *
     * @param splitInput The user's input split into command and arguments.
     * @param tasks      The list of tasks to be managed.
     * @param ui         The user interface to interact with the user.
     * @param storage    The storage handler for saving the updated tasks.
     */
    private void handleTodoCommand(String[] splitInput, TaskList tasks, Ui ui, Storage storage) {
        if (splitInput.length > 1) {
            String description = splitInput[1];
            tasks.add(new Todo(description));
            ui.showMessage("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1));
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            storage.save(tasks);
        } else {
            ui.showError("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Handles the "deadline" command, adding a new Deadline task.
     *
     * @param splitInput The user's input split into command and arguments.
     * @param tasks      The list of tasks to be managed.
     * @param ui         The user interface to interact with the user.
     * @param storage    The storage handler for saving the updated tasks.
     */
    private void handleDeadlineCommand(String[] splitInput, TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] parts = splitInput[1].split("/by ", 2);
            String description = parts[0];
            String by = parts[1];
            tasks.add(new Deadline(description, by));
            ui.showMessage("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1));
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            storage.save(tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showError("OOPS!!! The deadline command must include '/by <date/time>'.");
        }
    }

    /**
     * Handles the "event" command, adding a new Event task.
     *
     * @param splitInput The user's input split into command and arguments.
     * @param tasks      The list of tasks to be managed.
     * @param ui         The user interface to interact with the user.
     * @param storage    The storage handler for saving the updated tasks.
     */
    private void handleEventCommand(String[] splitInput, TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] parts = splitInput[1].split("/from ", 2);
            String[] fromTo = parts[1].split("/to ", 2);
            String description = parts[0];
            String from = fromTo[0];
            String to = fromTo[1];
            tasks.add(new Event(description, from, to));
            ui.showMessage("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1));
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            storage.save(tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showError("OOPS!!! The event command must include '/from <start date/time>' and '/to <end date/time>'.");
        }
    }

    /**
     * Handles the "delete" command, removing a task from the list.
     *
     * @param splitInput The user's input split into command and arguments.
     * @param tasks      The list of tasks to be managed.
     * @param ui         The user interface to interact with the user.
     * @param storage    The storage handler for saving the updated tasks.
     */
    private void handleDeleteCommand(String[] splitInput, TaskList tasks, Ui ui, Storage storage) {
        try {
            int deleteIndex = Integer.parseInt(splitInput[1]) - 1;
            if (deleteIndex >= 0 && deleteIndex < tasks.size()) {
                ui.showMessage("Noted. I've removed this task:\n" + tasks.remove(deleteIndex));
                ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
                storage.save(tasks);
            } else {
                ui.showError("OOPS!!! The task number is out of range.");
            }
        } catch (NumberFormatException e) {
            ui.showError("OOPS!!! The task number must be an integer.");
        }
    }

    /**
     * Handles the "bye" command, saving the current tasks and exiting the program.
     *
     * @param tasks   The list of tasks to be saved.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage handler for saving the tasks.
     */
    private void handleByeCommand(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
        storage.save(tasks);
        System.exit(0);
    }

    /**
     * Handles the "find" command, searching for tasks with the given keyword.
     *
     * @param splitInput The user's input split into command and arguments.
     * @param tasks      The list of tasks to be searched.
     * @param ui         The user interface to interact with the user.
     */
    private void handleFindCommand(String[] splitInput, TaskList tasks, Ui ui) {
        if (splitInput.length > 1) {
            String keyword = splitInput[1];
            List<Task> results = tasks.find(keyword);
            ui.showSearchResults(results);
        } else {
            ui.showError("OOPS!!! The find command must include a keyword.");
        }
    }
}

