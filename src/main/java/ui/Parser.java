package ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import storage.Storage;
import task.*;

import java.time.format.DateTimeParseException;

/**
 * Parses user inputs and handles commands accordingly in a command-line interface.
 */
public class Parser {

    public static Storage storage;

    /**
     * Parses the input command and delegates to the appropriate handler.
     *
     * @param input The full command input by the user.
     * @param storage The storage instance to interact with file operations.
     * @return The response if valid user input.
     * @throws BotException If an unrecognized command is input.
     */
    public static String parse(String input, Storage storage) {
        Parser.storage = storage;
        int spaceIndex = input.indexOf(" ");
        String command = (spaceIndex != -1) ? input.substring(0, spaceIndex).toLowerCase() : input.toLowerCase();
        String arguments = (spaceIndex != -1) ? input.substring(spaceIndex + 1).trim() : "";

        String response;

        try {
            response = switch (command) {
                case "mark", "unmark" -> handleMarkUnmark(command, arguments);
                case "todo" -> handleToDo(arguments);
                case "deadline" -> handleDeadline(arguments);
                case "event" -> handleEvent(arguments);
                case "list" -> handleList();
                case "delete" -> handleDelete(arguments);
                case "bye" -> handleBye();
                case "find" -> handleFind(arguments);
                case "remind" -> handleRemind();
                case "clear" -> handleClear();
                default -> throw new BotException("I'm sorry, I don't recognize that command.");
            };
        } catch (BotException e) {
            response = e.getMessage();
        }
        Parser.storage.saveData();
        return response;
    }

    /**
     * Handles the "clear" command by clearing saved tasks.
     *
     * @return A message confirming the clearing of saved data.
     */
    private static String handleClear() {
        String msg = "";
        storage.clearSave();
        msg += "Your data has been cleared. All tasks have been removed.";
        return msg;
    }

    /**
     * Handles the "remind" command to display tasks within the next two days.
     *
     * @return A message listing upcoming tasks.
     */
    protected static String handleRemind() {
        String msg = "";
        TaskList upcoming = TaskList.getUpcomingTasks();
        msg += String.format("You have %s tasks in the next 2 days.\n", upcoming.getNumTasks());
        msg += upcoming.printList();
        return msg;
    }

    /**
     * Handles the "find" command to search tasks by a term.
     *
     * @param arguments The search term provided by the user.
     * @return A message listing tasks containing the term.
     * @throws BotException If no search term is provided.
     */
    private static String handleFind(String arguments) throws BotException {
        if (arguments.isEmpty()) {
            throw new BotException("Please provide a search term");
        }
        TaskList searchResultList = TaskList.mainTaskList.tasksContainingTerm(arguments);
        String msg = searchResultList.printList();
        if (searchResultList.getNumTasks() == 1) {
            msg += String.format("There is 1 task containing the word \"%s\".\n", arguments);
        } else {
            msg += String.format("There are %s tasks containing the word \"%s\".\n", searchResultList.getNumTasks(), arguments);
        }
        return msg;
    }

    /**
     * Handles the "mark" and "unmark" commands to mark or unmark tasks as completed.
     *
     * @param command The command ("mark" or "unmark").
     * @param arguments The task index to mark/unmark.
     * @return A message confirming the action.
     * @throws BotException If no task index is provided or the task does not exist.
     */
    private static String handleMarkUnmark(String command, String arguments) throws BotException {
        if (arguments.isEmpty()) {
            throw new BotException("Please provide a task number.");
        }
        int taskIndex = Integer.parseInt(arguments) - 1;
        if (taskIndex < 0 || taskIndex >= TaskList.mainTaskList.getNumTasks()) {
            throw new BotException("That task does not exist!");
        }

        String msg;
        if (command.equals("mark")) {
            TaskList.mainTaskList.markTask(taskIndex);
            msg = "Nicely done! Keep it up!\n";
        } else {
            TaskList.mainTaskList.unmarkTask(taskIndex);
            msg = "Sure, I'll uncheck that task!\n";
        }
        msg += Parser.handleList();

        return msg;
    }

    /**
     * Handles the "todo" command to create a new ToDo task.
     *
     * @param arguments The description of the ToDo task.
     * @return A message confirming the task has been added.
     * @throws BotException If no description is provided.
     */
    private static String handleToDo(String arguments) throws BotException {
        if (arguments.isEmpty()) {
            throw new BotException("Please provide a task description.");
        }
        new ToDo(arguments);
        return UI.taskAddedMsg();
    }

    /**
     * Handles the "deadline" command to create a new Deadline task.
     *
     * @param arguments The description and deadline of the task.
     * @return A message confirming the task has been added.
     * @throws BotException If the input is malformed or the deadline cannot be parsed.
     */
    private static String handleDeadline(String arguments) throws BotException {
        int byIndex = arguments.indexOf(" /by ");
        if (byIndex == -1) {
            throw new BotException("Please format your instructions correctly. E.g., deadline [task]" +
                    " /by [MM-dd-yyyy HHmm]");
        }
        String taskDescription = arguments.substring(0, byIndex);
        String deadline = arguments.substring(byIndex + 5);
        try {
            new Deadline(taskDescription, deadline);
            return UI.taskAddedMsg();
        } catch (DateTimeParseException e) {
            throw new BotException("I'm sorry, I had trouble understanding the deadline. " +
                    "Please ensure you have formatted it correctly. [MM-dd-yyyy HHmm]");
        }
    }

    /**
     * Handles the "event" command to create a new Event task.
     *
     * @param arguments The description, start time, and end time of the event.
     * @return A message confirming the task has been added.
     * @throws BotException If the input is malformed or the dates cannot be parsed.
     */
    private static String handleEvent(String arguments) throws BotException {
        int fromIndex = arguments.indexOf(" /from ");
        int toIndex = arguments.indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1) {
            throw new BotException("Please format your instructions correctly. E.g., event [task] " +
                    "/from [MM-dd-yyyy HHmm] /to [MM-dd-yyyy HHmm]");
        }
        String taskDescription = arguments.substring(0, fromIndex);
        String eventStart = arguments.substring(fromIndex + 7, toIndex);
        String eventEnd = arguments.substring(toIndex + 5);
        try {
            new Event(taskDescription, eventStart, eventEnd);
            return UI.taskAddedMsg();
        } catch (DateTimeParseException e) {
            throw new BotException("I had trouble understanding your starting and/or ending time. " +
                    "Please ensure you have formatted it correctly. [MM-dd-yyyy HHmm]");
        }
    }

    /**
     * Handles the "list" command to list all tasks.
     *
     * @return A message listing all tasks in the task list.
     */
    private static String handleList() {
        return TaskList.mainTaskList.printList() +
                String.format("You have %s tasks in your list.\n", TaskList.mainTaskList.getNumTasks());
    }

    /**
     * Handles the "delete" command to delete a task from the task list.
     *
     * @param arguments The task index to delete.
     * @return A message confirming the task has been deleted.
     * @throws BotException If no task index is provided or the task does not exist.
     */
    protected static String handleDelete(String arguments) throws BotException {
        if (arguments.isEmpty()) {
            throw new BotException("Please provide a task number.");
        }
        int taskIndex = Integer.parseInt(arguments) - 1;
        if (taskIndex < 0 || taskIndex >= TaskList.mainTaskList.getNumTasks()) {
            throw new BotException("That task does not exist!");
        }
        TaskList.mainTaskList.deleteTask(taskIndex);
        return "Got it! I've removed that task for you.\n" + Parser.handleList();
    }

    /**
     * Handles the "bye" command, saving data and exiting the application.
     *
     * @return A goodbye message and saves the task data.
     */
    private static String handleBye() {

        // closes the application if user typed "bye"
        //@@author James_D -reused
        // source:
        // https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx
        // reused the method to close the javafx window after a delay
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
        //@@author

        return "Bye. Hope to see you again soon!\n";
    }
}
