package bob;

import java.io.File;
import java.time.LocalDateTime;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.ToDo;

/**
 * Represents the main class for Bob chatbot.
 * The Bob class initialises the application, handles user commands and runs the application.
 */
public class Bob {
    /** File path to where saved tasks are stored */
    private static final String FILE_PATH = "data" + File.separator + "Bob.txt";
    private static TaskList tasks;
    private static Storage storage;
    private static Ui ui;
    private static String loadStatusMessage;
    private Command commandType;

    /**
     * Constructs a new instance of the Bob application.
     */
    public Bob() {
        storage = new Storage(FILE_PATH);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
            loadStatusMessage = ui.showLoadingSuccess();
        } catch (BobException e) {
            tasks = new TaskList();
            loadStatusMessage = ui.showError(e);
        }
        assert tasks != null : "tasks should always be initialised";
    }

    /**
     * Returns the startup message, which includes a welcome message and the status of task loading.
     *
     * @return A string representing the startup message.
     */
    public static String startUp() {
        return ui.showMessage(ui.showWelcome(), "\n", loadStatusMessage);
    }

    /**
     * Enum containing the various commands that can be parsed.
     */
    public enum Command {
        BYE,
        LIST,
        RELEVANT,
        FIND,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        UNKNOWN
    }

    /**
     * Returns a response from Bob based on the user's input.
     *
     * @param userInput The user's input message.
     * @return A string containing Bob's response to the user.
     */
    public String getResponse(String userInput) {
        try {
            Command command = Parser.parseCommand(userInput);
            String taskDetails = Parser.getTaskDetails(userInput);
            commandType = command;

            switch (command) {
            case BYE:
                return commandBye();

            case LIST:
                return commandList();

            case RELEVANT:
                return commandRelevant(taskDetails);

            case MARK:
                return commandMark(taskDetails);

            case UNMARK:
                return commandUnmark(taskDetails);

            case TODO:
                return commandTodo(taskDetails);

            case DEADLINE:
                return commandDeadline(taskDetails);

            case EVENT:
                return commandEvent(taskDetails);

            case DELETE:
                return commandDelete(taskDetails);

            case FIND:
                return commandFind(taskDetails);

            case UNKNOWN:
                // Fallthrough

            default:
                throw new BobException("Sorry, I do not understand. Please try something else.");
            }
        } catch (BobException e) {
            return ui.showError(e);
        }
    }

    /**
     * Returns the command type of the last user input processed.
     *
     * @return The command type.
     */
    public Command getCommandType() {
        return commandType;
    }

    /**
     * Handles the "BYE" command to exit the application.
     */
    static String commandBye() {
        return ui.showGoodbye();
    }

    /**
     * Handles the "LIST" command to print the list of tasks.
     */
    static String commandList() {
        if (tasks.isEmpty()) {
            return ui.showNoTasks();
        }
        return ui.showMessage("Your list of tasks:\n" + tasks.printTasks());
    }

    /**
     * Handles the "RELEVANT" command to print tasks occurring on a specific given date.
     *
     * @param dateStr The date for which relevant tasks should be printed using the format "yyyy-MM-dd".
     * @throws BobException If date format is invalid.
     */
    static String commandRelevant(String dateStr) throws BobException {
        return ui.showMessage(tasks.printRelevantTasksByDate(dateStr));
    }

    /**
     * Handles the "FIND" command to find tasks that have description containing a keyword or a phrase.
     *
     * @param keyword The keyword or phrase to find within the task description.
     * @throws BobException If keyword is empty.
     */
    static String commandFind(String keyword) throws BobException {
        return ui.showMessage(tasks.printTasksByKeyword(keyword));
    }

    /**
     * Handles the "MARK" command to mark a task as done.
     *
     * @param taskDetails The task number to mark as done.
     * @throws BobException If the task number is invalid.
     */
    static String commandMark(String taskDetails) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Please provide a task number.");
        }
        try {
            // Get task
            int taskNum = Integer.parseInt(taskDetails);
            bob.task.Task currTask = tasks.getTask(taskNum);

            // Mark task as done and update tasks
            currTask.markAsDone();
            storage.saveTasks(tasks);
            return ui.showMessage("Good Job! Marking this task as done:\n " + currTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BobException("The task number provided is invalid.");
        }
    }

    /**
     * Handles the "UNMARK" command to mark a task as not done.
     *
     * @param taskDetails The task number to mark as not done.
     * @throws BobException If the task number is invalid.
     */
    static String commandUnmark(String taskDetails) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Please provide a task number.");
        }
        try {
            // Get Task
            int taskNum = Integer.parseInt(taskDetails);
            Task currTask = tasks.getTask(taskNum);

            // Mark task as not done and update tasks
            currTask.markAsUndone();
            storage.saveTasks(tasks);
            return ui.showMessage("Okay, marking this task as not done yet:\n " + currTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BobException("The task number provided is invalid.");
        }
    }

    /**
     * Handles the "TODO" command to add a ToDo task.
     *
     * @param taskDetails The description of the ToDo task.
     * @throws BobException If there are missing details.
     */
    static String commandTodo(String taskDetails) throws BobException {
        String format = "Add ToDo task in the following format:\ntodo <description>";
        if (taskDetails.isEmpty()) {
            throw new BobException("Missing details!\n" + format);
        }
        // Create a new task
        ToDo task = new ToDo(taskDetails);

        // Add task and update tasks
        tasks.addTask(task);
        storage.saveTasks(tasks);
        return ui.showMessage("Adding ToDo task:\n " + task
                + "\nTotal number of tasks in your list: " + tasks.getNumTasks());
    }

    /**
     * Handles the "DEADLINE" command to add a Deadline task.
     *
     * @param taskDetails The description and due date of the Deadline task.
     * @throws BobException If there are missing details, wrong format or the due date is invalid.
     */
    static String commandDeadline(String taskDetails) throws BobException {
        String format = "Add Deadline task in the following format:\n"
                + "deadline <description> /by <due date>";
        if (taskDetails.isEmpty()) {
            throw new BobException("Missing details!\n" + format);
        }

        try {
            // Process task details
            String[] params = taskDetails.split(" /");
            String description = params[0];
            String byStr = params[1].split(" ", 2)[1];

            LocalDateTime by = Parser.parseDateTime(byStr);
            if (by.isBefore(LocalDateTime.now())) {
                throw new BobException("Oops! The date you provided is in the past. "
                        + "Kindly provide a future date.");
            }

            // Create a new task
            Deadline task = new Deadline(description, by);

            // Add task and update tasks
            tasks.addTask(task);
            storage.saveTasks(tasks);
            return ui.showMessage("Adding Deadline task:\n " + task
                    + "\nTotal number of tasks in your list: " + tasks.getNumTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("You may have missing details or wrong format!\n" + format);
        }
    }

    /**
     * Handles the "EVENT" command to add an Event task.
     *
     * @param taskDetails The description, start date, and end date of the Event task.
     * @throws BobException If there are missing details, wrong format, or invalid dates.
     */
    static String commandEvent(String taskDetails) throws BobException {
        String format = "Add Event task in the following format:\n"
                + "deadline <description> /from <start date> /to <due date>";
        if (taskDetails.isEmpty()) {
            throw new BobException("Missing details!\n" + format);
        }

        try {
            // Process task details
            String[] params = taskDetails.split(" /");
            String description = params[0];
            String fromStr = params[1].split(" ", 2)[1];
            String toStr = params[2].split(" ", 2)[1];

            LocalDateTime from = Parser.parseDateTime(fromStr);
            LocalDateTime to = Parser.parseDateTime(toStr);
            if (from.isBefore(LocalDateTime.now()) || to.isBefore(LocalDateTime.now())) {
                throw new BobException(
                        "Oops! The date you provided is in the past. "
                                + "Kindly provide a future date.");
            }
            if (to.isBefore(from)) {
                throw new BobException(
                        "The end date cannot be before the start date. Please try again.");
            }

            // Create a new task
            Event task = new Event(description, from, to);

            // Add task and update tasks
            tasks.addTask(task);
            storage.saveTasks(tasks);
            return ui.showMessage("Adding Event task:\n " + task
                    + "\nTotal number of tasks in your list: " + tasks.getNumTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("You may have missing details or wrong format!\n" + format);
        }
    }

    /**
     * Handles the "DELETE" command to delete a task.
     *
     * @param taskDetails TThe task number to delete.
     * @throws BobException If the task number is invalid.
     */
    static String commandDelete(String taskDetails) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Please provide a task number.");
        }
        try {
            // Get task
            int taskNum = Integer.parseInt(taskDetails);
            Task currTask = tasks.getTask(taskNum);

            // Delete task and update tasks
            tasks.delTask(taskNum);
            storage.saveTasks(tasks);
            return ui.showMessage("Noted, removing this task:\n " + currTask
                    + "\nTotal number of tasks in your list: " + tasks.getNumTasks());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BobException("The task number provided is invalid.");
        }
    }
}
