package bob;

import java.io.File;
import java.time.LocalDateTime;

import bob.task.Task;
import bob.task.ToDo;
import bob.task.Deadline;
import bob.task.Event;

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

    /**
     * Constructs a new instance of the Bob application.
     *
     * @param filePath path to the file where the saved tasks are stored.
     */
    public Bob(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
            ui.showLoadingSuccess();
        } catch (BobException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Enum containing the various commands that can be parsed.
     */
    public enum Command {
        BYE,
        LIST,
        RELEVANT,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        UNKNOWN
    }

    /**
     * The main entry point for the Bob chatbot application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        try {
            new Bob(FILE_PATH).run();
        } catch (BobException e) {
            ui.showError(e);
        }
    }

    /**
     * Processes user commands until user inputs "bye"
     *
     * @throws BobException If command is unknown.
     */
    public void run() throws BobException {
        ui.showWelcome();

        while (true) {
            try {
                // Read user input
                String userInput = ui.readCommand();

                // Process user input
                Command command = Parser.parseCommand(userInput);
                String taskDetails = Parser.getTaskDetails(userInput);

                switch (command) {
                case BYE:
                    commandBye();
                    return;

                case LIST:
                    commandList();
                    break;

                case RELEVANT:
                    commandRelevant(taskDetails);
                    break;

                case MARK:
                    commandMark(taskDetails);
                    break;

                case UNMARK:
                    commandUnmark(taskDetails);
                    break;

                case TODO:
                    commandTodo(taskDetails);
                    break;

                case DEADLINE:
                    commandDeadline(taskDetails);
                    break;

                case EVENT:
                    commandEvent(taskDetails);
                    break;

                case DELETE:
                    commandDelete(taskDetails);
                    break;

                case UNKNOWN:
                    // Fallthrough
                default:
                    throw new BobException("Sorry, I do not understand. Please try something else.");
                }
            } catch (BobException e) {
                ui.showError(e);
            } catch (Exception e) {
                throw new BobException("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Handles the "BYE" command to exit the application.
     */
    static void commandBye() {
        ui.showGoodbye();
    }

    /**
     * Handles the "LIST" command to print the list of tasks.
     */
    static void commandList() {
        if (tasks.isEmpty()) {
            ui.showNoTasks();
        }
            ui.showMessage("Your list of tasks:\n" + tasks.printTasks());
    }

    /**
     * Handles the "RELEVANT" command to print tasks occurring on a specific given date.
     *
     * @param dateStr The date for which relevant tasks should be printed using the format "yyyy-MM-dd".
     * @throws BobException If date format is invalid.
     */
    static void commandRelevant(String dateStr) throws BobException {
        ui.showMessage(tasks.printRelevantTasks(dateStr));
    }

    /**
     * Handles the "MARK" command to mark a task as done.
     *
     * @param taskDetails The task number to mark as done.
     * @throws BobException If the task number is invalid.
     */
    static void commandMark(String taskDetails) throws BobException {
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
            ui.showMessage("Good Job! Marking this task as done:\n " + currTask);
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
    static void commandUnmark(String taskDetails) throws BobException {
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
            ui.showMessage("Okay, marking this task as not done yet:\n " + currTask);
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
    static void commandTodo(String taskDetails) throws BobException {
        String format = "Add ToDo task in the following format:\ntodo <description>";
        if (taskDetails.isEmpty()) {
            throw new BobException("Missing details!\n" + format);
        }
        // Create a new task
        ToDo task = new ToDo(taskDetails);

        // Add task and update tasks
        tasks.addTask(task);
        storage.saveTasks(tasks);
        ui.showMessage("Adding ToDo task:\n " + task
                + "\nTotal number of tasks in your list: " + tasks.getNumTasks());
    }

    /**
     * Handles the "DEADLINE" command to add a Deadline task.
     *
     * @param taskDetails The description and due date of the Deadline task.
     * @throws BobException If there are missing details, wrong format or the due date is invalid.
     */
    static void commandDeadline(String taskDetails) throws BobException {
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
            ui.showMessage("Adding Deadline task:\n " + task
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
    static void commandEvent(String taskDetails) throws BobException {
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
                        "The end date cannot be before the start date. " +
                        "Please try again.");
            }

            // Create a new task
            Event task = new Event(description, from, to);

            // Add task and update tasks
            tasks.addTask(task);
            storage.saveTasks(tasks);
            ui.showMessage("Adding Event task:\n " + task
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
    static void commandDelete(String taskDetails) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Please provide a task number.");
        }
        // Get task
        int taskNum = Integer.parseInt(taskDetails);
        Task currTask = tasks.getTask(taskNum);

        // Delete task and update tasks
        tasks.delTask(taskNum);
        storage.saveTasks(tasks);
        ui.showMessage("Noted, removing this task:\n " + currTask
                + "\nTotal number of tasks in your list: " + tasks.getNumTasks());
    }
}