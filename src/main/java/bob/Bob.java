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
    private static Command commandType;

    /**
     * Constructs a new instance of the Bob application.
     */
    public Bob() {
        initialiseApplication();
    }

    /**
     * Initialises the Bob application and load saved tasks if available.
     */
    private void initialiseApplication() {
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
        SORT,
        UNKNOWN
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
     * Returns a response from Bob based on the user's input.
     *
     * @param userInput The user's input message.
     * @return A string containing Bob's response to the user.
     */
    public String getResponse(String userInput) {
        try {
            Command command = Parser.parseCommand(userInput);
            String taskDetails = Parser.getTaskDetails(userInput);
            setCommandType(command);
            return executeCommand(command, taskDetails);
        } catch (BobException e) {
            return ui.showError(e);
        }
    }

    /**
     * Executes the appropriate command based on the parsed command type.
     *
     * @param command The command parsed from user input.
     * @param taskDetails The task details or parameters provided in the user input.
     * @return A string representing Bob's response after executing the command.
     * @throws BobException If any error occurs during command execution.
     */
    private String executeCommand(Command command, String taskDetails) throws BobException {
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
        case SORT:
            return commandSort(taskDetails);
        case UNKNOWN:
            // Fallthrough
        default:
            throw new BobException("Sorry, I do not understand. Please try something else.");
        }
    }

    /**
     * Sets commandType as the current command provided by the user.
     *
     * @param command The command from the user input.
     */
    private static void setCommandType(Command command) {
        commandType = command;
    }

    /**
     * Handles the "BYE" command to exit the application.
     *
     * @return A string containing Bob's goodbye message.
     */
    private static String commandBye() {
        return ui.showGoodbye();
    }

    /**
     * Handles the "LIST" command to print the list of tasks.
     *
     * @return A string containing the current list of tasks.
     */
    private static String commandList() {
        if (tasks.isEmpty()) {
            return ui.showNoTasks();
        }
        return ui.showMessage("Your list of tasks:\n", tasks.printTasks());
    }

    /**
     * Handles the "RELEVANT" command to print tasks occurring on a specific given date.
     *
     * @param dateStr The date for which relevant tasks should be printed using the format "yyyy-MM-dd".
     * @return A string containing relevant tasks for the given date.
     * @throws BobException If date format is invalid.
     */
    private static String commandRelevant(String dateStr) throws BobException {
        return ui.showMessage(tasks.printRelevantTasksByDate(dateStr));
    }

    /**
     * Handles the "FIND" command to find tasks that have description containing a keyword or a phrase.
     *
     * @param keyword The keyword or phrase to find within the task description.
     * @return A string containing tasks that match the search keyword.
     * @throws BobException If the keyword is empty.
     */
    private static String commandFind(String keyword) throws BobException {
        validateKeyword(keyword);
        return ui.showMessage(tasks.printTasksByKeyword(keyword));
    }

    /**
     * Handles the "MARK" command to mark a task as done.
     *
     * @param taskDetails The task number to mark as done.
     * @return A string confirming that the task is marked as done.
     * @throws BobException If the task number is invalid.
     */
    private static String commandMark(String taskDetails) throws BobException {
        int taskNum = Parser.parseTaskNumber(taskDetails);
        Task currTask = tasks.getTask(taskNum);
        currTask.markAsDone();
        saveTasks();
        return ui.showMessage("Good Job! Marking this task as done:\n " + currTask);
    }

    /**
     * Handles the "UNMARK" command to mark a task as not done.
     *
     * @param taskDetails The task number to mark as not done.
     * @return A string confirming that the task is marked as not done.
     * @throws BobException If the task number is invalid.
     */
    private static String commandUnmark(String taskDetails) throws BobException {
        int taskNum = Parser.parseTaskNumber(taskDetails);
        Task currTask = tasks.getTask(taskNum);
        currTask.markAsUndone();
        saveTasks();
        return ui.showMessage("Okay, marking this task as not done yet:\n " + currTask);
    }

    /**
     * Handles the "TODO" command to add a ToDo task.
     *
     * @param taskDetails The description of the ToDo task.
     * @return A string confirming that the ToDo task is added.
     * @throws BobException If there are missing details.
     */
    private static String commandTodo(String taskDetails) throws BobException {
        String format = "Add ToDo task in the following format:\ntodo <description>";
        validateTaskDetails(taskDetails, format);
        ToDo task = new ToDo(taskDetails);
        addTaskAndSave(task);
        return ui.showMessage("Adding ToDo task:\n " + task
                + "\nTotal number of tasks in your list: " + tasks.getNumTasks());
    }

    /**
     * Handles the "DEADLINE" command to add a Deadline task.
     *
     * @param taskDetails The description and due date of the Deadline task.
     * @return A string confirming that the Deadline task is added.
     * @throws BobException If there are missing details, wrong format or the due date is invalid.
     */
    private static String commandDeadline(String taskDetails) throws BobException {
        String format = "Add Deadline task in the following format:\ndeadline <description> /by <due date>";
        validateTaskDetails(taskDetails, format);

        int numParams = 2;
        String[] params = splitTaskDetails(taskDetails, numParams);
        String description = params[0];
        LocalDateTime by = Parser.parseDateTime(params[1]);

        Deadline task = new Deadline(description, by);
        addTaskAndSave(task);
        return ui.showMessage("Adding Deadline task:\n " + task
                + "\nTotal number of tasks in your list: " + tasks.getNumTasks());
    }

    /**
     * Handles the "EVENT" command to add an Event task.
     *
     * @param taskDetails The description, start date, and end date of the Event task.
     * @return A string confirming that the Event task is added.
     * @throws BobException If there are missing details, wrong format, or invalid dates.
     */
    private static String commandEvent(String taskDetails) throws BobException {
        String format = "Add Event task in the following format:\n"
                + "deadline <description> /from <start date> /to <due date>";
        validateTaskDetails(taskDetails, format);

        int numParams = 3;
        String[] params = splitTaskDetails(taskDetails, numParams);
        String description = params[0];
        LocalDateTime from = Parser.parseDateTime(params[1]);
        LocalDateTime to = Parser.parseDateTime(params[2]);

        validateEventDates(from, to);

        Event task = new Event(description, from, to);
        addTaskAndSave(task);
        return ui.showMessage("Adding Event task:\n " + task
                + "\nTotal number of tasks in your list: " + tasks.getNumTasks());
    }

    /**
     * Handles the "DELETE" command to delete a task.
     *
     * @param taskDetails TThe task number to delete.
     * @return A string confirming that the task is deleted.
     * @throws BobException If the task number is invalid.
     */
    private static String commandDelete(String taskDetails) throws BobException {
        int taskNum = Parser.parseTaskNumber(taskDetails);
        Task currTask = tasks.getTask(taskNum);
        tasks.delTask(taskNum);
        saveTasks();
        return ui.showMessage("Noted, removing this task:\n " + currTask
                + "\nTotal number of tasks in your list: " + tasks.getNumTasks());
    }

    /**
     * Sorts the task list based on the specified sort option and returns a formatted string of the sorted tasks.
     *
     * @param sortOption The sorting option provided by the user (either "description" or "date").
     * @return A formatted string of tasks sorted according to the specified option.
     * @throws BobException If the sorting option is invalid (i.e., not "description" or "date").
     */
    private static String commandSort(String sortOption) throws BobException {
        TaskList sortedTasks = getSortedTaskList(sortOption.toLowerCase());
        return ui.showMessage("Here is your list of tasks sorted by ", sortOption, ":\n", sortedTasks.printTasks());
    }

    /**
     * Returns the command type of the last user input processed.
     *
     * @return The command type.
     */
    public static Command getCommandType() {
        return commandType;
    }

    /**
     * Splits the task details into different parts based on the provided split parameters.
     *
     * @param taskDetails The string containing task details.
     * @param numParams The number of parameters
     * @return A string array containing the separated task details.
     * @throws BobException If there are missing details or the format is incorrect.
     */
    private static String[] splitTaskDetails(String taskDetails, int numParams) throws BobException {
        try {
            String[] params = taskDetails.split(" /");
            for (int i = 1; i < numParams; i++) {
                params[i] = params[i].split(" ", 2)[1];
            }
            return params;
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("You may have missing details or wrong format!");
        }
    }

    /**
     * Saves the tasks to the storage.
     *
     * @throws BobException If an error occurs while saving tasks.
     */
    private static void saveTasks() throws BobException {
        storage.saveTasks(tasks);
    }

    /**
     * Adds a task to the task list and saves it to storage.
     *
     * @param task The task to be added.
     * @throws BobException If an error occurs while saving tasks.
     */
    private static void addTaskAndSave(Task task) throws BobException {
        tasks.addTask(task);
        saveTasks();
    }

    /**
     * Validates the task details and ensures they are not missing.
     *
     * @param taskDetails The task details to validate.
     * @param format The format in which the task should be provided.
     * @throws BobException If task details are missing.
     */
    private static void validateTaskDetails(String taskDetails, String format) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Missing details!\n" + format);
        }
    }

    /**
     * Validates the keyword provided for the "FIND" command.
     *
     * @param keyword The keyword to search for.
     * @throws BobException If the keyword is empty.
     */
    private static void validateKeyword(String keyword) throws BobException {
        if (keyword.isEmpty()) {
            throw new BobException("Please provide a keyword or a phrase.");
        }
    }

    /**
     * Validates the provided dates to ensure valid dates.
     * The end date should be before the start date and both dates should be in the future.
     *
     * @param from The start date.
     * @param to The end date.
     * @throws BobException If the end date is before the start date or either date is in the past.
     */
    private static void validateEventDates(LocalDateTime from, LocalDateTime to) throws BobException {
        if (to.isBefore(from)) {
            throw new BobException("The end date cannot be before the start date. Please try again.");
        }
    }

    /**
     * Retrieves the sorted task list based on the specified sorting option.
     *
     * @param sortOption The option for sorting tasks (either "description" or "date").
     * @return A TaskList object containing the sorted tasks.
     * @throws BobException If the sort option is invalid (i.e., not "description" or "date").
     */
    private static TaskList getSortedTaskList(String sortOption) throws BobException {
        switch (sortOption) {
        case "description":
            return tasks.sortTasksByDescription();
        case "date":
            return tasks.sortTasksByDate();
        default:
            throw new BobException("Invalid sorting option. Key in either 'sort description' or 'sort date'.");
        }
    }
}
