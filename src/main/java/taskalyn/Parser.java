package taskalyn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

/**
 * Manages scanning of input and parsing of commands.
 */
public class Parser {
    private static final String TASK_DESCRIPTION = "task description";
    private static final String COMMAND_FORMAT = "command format";
    private static final String EXPECTED_DATE_FORMAT = "dd-MM-yyyy HHmm";
    private final TaskManager taskManager;
    private final Ui ui;

    /**
     * Constructs the Parser object with ui and taskmanager.
     *
     * @param ui Ui object to manage user interaction.
     * @param taskManager TaskManager object to manage tasks.
     */
    public Parser(Ui ui, TaskManager taskManager) {
        this.ui = ui;
        this.taskManager = taskManager;
    }

    /**
     * Parses user input commands and handles command management.
     *
     * @return String stating the reply from Taskalyn.
     */
    public String parse(String input) {
        String[] items = input.trim().split(" ", 2);
        String commandString = items[0];

        try {
            CommandType command = CommandType.fromString(commandString);
            if (command == null) {
                return "Sorry bro, no clue what you're saying!";
            }

            switch (command) {
            case BYE:
                return handleByeCommand();
            case LIST:
                return handleListCommand();
            case SORT:
                return handleSortCommand();
            case FIND:
                checkArgumentCount(items, command);
                return handleFindCommand(items);
            case DELETE:
                checkArgumentCount(items, command);
                return handleDeleteCommand(items);
            case MARK:
                checkArgumentCount(items, command);
                return handleMarkCommand(items);
            case UNMARK:
                checkArgumentCount(items, command);
                return handleUnmarkCommand(items);
            case TODO:
                checkArgumentCount(items, command);
                return handleTodoCommand(items);
            case DEADLINE:
                checkArgumentCount(items, command);
                return handleDeadlineCommand(items);
            case EVENT:
                checkArgumentCount(items, command);
                return handleEventCommand(items);
            default:
                return "Sorry bro, no clue what you're saying!";
            }
        } catch (NoSuchTaskException | CommandFormatException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the bye message as a String.
     *
     * @return String saying bye.
     */
    private String handleByeCommand() {
        return ui.showByeMessage();
    }

    /**
     * Returns the list of tasks as a String.
     *
     * @return String containing list of tasks.
     */
    private String handleListCommand() {
        return taskManager.listTasks();
    }

    /**
     * Returns the list of deadline tasks sorted by deadline.
     *
     * @return String containing list of deadline tasks sorted by deadline.
     */
    private String handleSortCommand() {
        return taskManager.sortDeadlineTasksByDeadline();
    }

    /**
     * Returns a message String when a task is deleted.
     *
     * @param items The user input split by whitespace.
     * @return String saying that task is deleted.
     * @throws CommandFormatException If the command format was written incorrectly.
     * @throws NoSuchTaskException No such task exists.
     * @throws IndexOutOfBoundsException The list index is out of bounds.
     */
    private String handleDeleteCommand(String[] items) throws CommandFormatException, NoSuchTaskException,
            IndexOutOfBoundsException {
        return handleTaskOperation(items, taskManager::deleteTask, CommandType.DELETE);
    }

    /**
     * Returns a message String saying that a task has been marked as complete.
     *
     * @param items The user input split by whitespace.
     * @return String saying that task is marked as complete.
     * @throws CommandFormatException Command Format is incorrect.
     * @throws NoSuchTaskException No such task exists.
     * @throws IndexOutOfBoundsException The list index is out of bounds.
     */
    private String handleMarkCommand(String[] items) throws CommandFormatException, NoSuchTaskException,
            IndexOutOfBoundsException {
        return handleTaskOperation(items, taskManager::markTaskAsComplete, CommandType.MARK);
    }

    /**
     * Returns a message String saying that a task has been marked as incomplete.
     *
     * @param items The user input split by whitespace.
     * @return String saying that a task has been marked as incomplete.
     * @throws CommandFormatException Command format is incorrect.
     * @throws NoSuchTaskException No such task exists.
     * @throws IndexOutOfBoundsException The list index is out of bounds.
     */
    private String handleUnmarkCommand(String[] items) throws CommandFormatException, NoSuchTaskException,
            IndexOutOfBoundsException {
        return handleTaskOperation(items, taskManager::markTaskAsIncomplete, CommandType.UNMARK);
    }

    /**
     * Returns the tasks filtered by keyword.
     *
     * @param items The user input split by whitespace.
     * @return String stating the list of tasks with keyword.
     * @throws CommandFormatException If the command format was written incorrectly.
     */
    private String handleFindCommand(String[] items) throws CommandFormatException {
        checkForEmptyCommandParameters(items[1], CommandType.FIND, TASK_DESCRIPTION);
        assert !items[1].isEmpty() : "The keyword to be searched cannot be empty.";
        return taskManager.searchTasksByKeyword(items[1]);
    }

    /**
     * Adds a TodoTask and returns a message String saying that the TodoTask has been added.
     *
     * @param items The user input split by whitespace.
     * @return String saying TodoTask has been added.
     * @throws CommandFormatException Command format is incorrect.
     */
    private String handleTodoCommand(String[] items) throws CommandFormatException {
        checkForEmptyCommandParameters(items[1], CommandType.TODO, TASK_DESCRIPTION);
        assert !items[1].isEmpty() : "Task description cannot be empty.";
        return taskManager.addTask(new TodoTask(items[1], false));
    }

    /**
     * Adds a DeadlineTask and returns a message String saying that the DeadlineTask has been added.
     *
     * @param items The user input split by whitespace.
     * @return String saying that DeadlineTask has been added.
     * @throws CommandFormatException Command format is incorrect.
     */
    private String handleDeadlineCommand(String[] items) throws CommandFormatException {
        checkForEmptyCommandParameters(items[1], CommandType.DEADLINE, COMMAND_FORMAT);

        // Returns [taskDescription, deadlineDate]
        String[] splitInput = splitInputOverKeyword(items[1], "/by", CommandType.DEADLINE);

        checkForEmptyCommandParameters(splitInput[0], CommandType.DEADLINE, TASK_DESCRIPTION);

        // Ensures that task description is not empty
        assert !splitInput[0].isEmpty() : "Task description cannot be empty.";

        LocalDateTime deadlineDate = getDateTime(splitInput[1]);

        // Ensures date and time inputs are valid
        String deadlineDateAsString = deadlineDate.format(DateTimeFormatter.ofPattern(EXPECTED_DATE_FORMAT));
        assert deadlineDateAsString.matches("\\d{2}-\\d{2}-\\d{4} \\d{4}") : "Date and Time format is incorrect.";

        return taskManager.addTask(new DeadlineTask(splitInput[0], deadlineDate, false));
    }

    /**
     * Adds an EventTask and returns a message String saying that EventTask has been added.
     *
     * @param items The user input split by whitespace.
     * @return String saying that EventTask has been added.
     * @throws CommandFormatException Command format is incorrect.
     */
    private String handleEventCommand(String[] items) throws CommandFormatException {
        checkForEmptyCommandParameters(items[1], CommandType.EVENT, COMMAND_FORMAT);

        // Returns [taskDescription, fromDate + /to + toDate]
        String[] splitInput = splitInputOverKeyword(items[1], "/from", CommandType.EVENT);

        checkForEmptyCommandParameters(splitInput[0], CommandType.EVENT, TASK_DESCRIPTION);

        // Ensures that task description is not empty
        assert !splitInput[0].isEmpty() : "Task description cannot be empty.";

        // Returns [fromDate, toDate]
        String[] fromAndToDates = splitInputOverKeyword(splitInput[1], "/to", CommandType.EVENT);
        LocalDateTime fromDate = getDateTime(fromAndToDates[0]);
        LocalDateTime toDate = getDateTime(fromAndToDates[1]);

        // Ensures date and time inputs are valid
        String fromDateAsString = fromDate.format(DateTimeFormatter.ofPattern(EXPECTED_DATE_FORMAT));
        assert fromDateAsString.matches("\\d{2}-\\d{2}-\\d{4} \\d{4}") : "fromDate format is incorrect.";
        String toDateAsString = toDate.format(DateTimeFormatter.ofPattern(EXPECTED_DATE_FORMAT));
        assert toDateAsString.matches("\\d{2}-\\d{2}-\\d{4} \\d{4}") : "toDate format is incorrect.";

        return taskManager.addTask(new EventTask(splitInput[0], fromDate, toDate, false));
    }

    /**
     * Performs task operations for the delete, mark, and unmark commands.
     *
     * @param items The user input split by whitespace.
     * @param taskOperation The taskManager method used by either delete, mark, or unmark.
     * @param commandType The name of the command.
     * @return String stating the status of task executed.
     * @throws CommandFormatException If the command format was written incorrectly.
     * @throws NoSuchTaskException If no such task index exists.
     */
    private String handleTaskOperation(String[] items, Function<Integer, String> taskOperation, CommandType commandType)
            throws CommandFormatException, NoSuchTaskException {
        if (items.length != 2) {
            throw new CommandFormatException("Aw... " + commandType
                    + " command must have just 2 arguments: "
                    + commandType + " {task number}.");
        }

        try {
            int taskIndex = getTaskIndex(items);
            return taskOperation.apply(taskIndex);
        } catch (NumberFormatException e) {
            throw new CommandFormatException("Aw... " + commandType
                    + " command must be followed by a number (e.g. "
                    + commandType
                    + " 1).");
        }
    }

    /**
     * Gets the task index of a task and returns the index.
     *
     * @param items The user input split by whitespace.
     * @return Task index in list of tasks.
     * @throws NoSuchTaskException If given task index is not within the list of tasks.
     */
    private int getTaskIndex(String[] items) throws NoSuchTaskException {
        int taskIndex = Integer.parseInt(items[1]);
        int taskCount = taskManager.getTaskSize();
        if (taskCount == 0) {
            throw new NoSuchTaskException("You have no tasks at the moment!");
        }

        if (taskIndex <= 0) {
            throw new NoSuchTaskException("Aw... task number cannot be zero or negative.");
        }

        if (taskIndex > taskCount) {
            throw new NoSuchTaskException("Aw... that task doesn't exist. Try again!");
        }
        return taskIndex;
    }

    /**
     * Checks if the user command is complete or incomplete.
     *
     * @param items The user input split by whitespace.
     * @param command The command given by the user.
     * @throws CommandFormatException If the command format was written incorrectly.
     */
    private void checkArgumentCount(String[] items, CommandType command) throws CommandFormatException {
        String commandFormat = getCommandFormat(command);
        assert commandFormat != null : "Command format cannot be null.";
        if (items.length != 2) {
            throw new CommandFormatException("Aw... " + command
                    + " command is incomplete. The format is: "
                    + commandFormat);
        }
    }

    /**
     * Checks if a command is following its command format.
     *
     * @param argument An input parameter in the user command.
     * @param command The main user command.
     * @param parameter The parameter that is looked for by this method.
     * @throws CommandFormatException If the command format is written incorrectly.
     */
    private void checkForEmptyCommandParameters(String argument, CommandType command, String parameter)
            throws CommandFormatException {
        if (argument.isEmpty() || argument.equals(" ")) {
            String exceptionMessage = "Aw... " + command + " command must ";

            if (Objects.equals(parameter, TASK_DESCRIPTION)) {
                exceptionMessage += "contain a non-empty task description.";
            } else if (Objects.equals(parameter, COMMAND_FORMAT)) {
                exceptionMessage += "follow the following command format: " + getCommandFormat(command);
            }
            throw new CommandFormatException(exceptionMessage);
        }
    }

    /**
     * Returns the expected format for a given command.
     *
     * @param command The user command.
     * @return Expected format for the command as a String.
     */
    private String getCommandFormat(CommandType command) {
        switch (command) {
        case FIND:
            return "find {keyword}";
        case TODO:
            return "todo {task}";
        case DEADLINE:
            return "deadline {task} /by {date in dd-MM-yyyy HHmm}";
        case EVENT:
            return "event {task} /from {date in dd-MM-yyyy HHmm} /to {date in dd-MM-yyyy HHmm}";
        default:
            return null;
        }
    }

    /**
     * Returns an array of String containing the parameters of the user input when split over a keyword.
     *
     * @param input The user input.
     * @param keyword The keyword that this method splits over.
     * @param command The command given by the user.
     * @return array of String containing parameters of the user input.
     * @throws CommandFormatException If the command was written incorrectly.
     */
    private String[] splitInputOverKeyword(String input, String keyword, CommandType command)
            throws CommandFormatException {
        if (!input.contains(keyword)) {
            throw new CommandFormatException("Aw... " + command + " command should contain " + keyword + ".");
        }

        String[] splitInput = Arrays.stream(input.split(keyword, 2))
                .map(part -> part.trim())
                .toArray(String[]::new);

        return splitInput;
    }

    /**
     * Returns a LocalDateTime object for a given String containing a date.
     *
     * @param date String object that says the date in dd-MM-yyyy HHmm.
     * @return LocalDateTime object.
     * @throws CommandFormatException If the date was written incorrectly.
     */
    private LocalDateTime getDateTime(String date) throws CommandFormatException {
        if (date.isEmpty() || date.equals(" ")) {
            throw new CommandFormatException("Aw... you are missing the date.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EXPECTED_DATE_FORMAT);
        try {
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new CommandFormatException("Aw... the date and time should be in this format: "
                    + EXPECTED_DATE_FORMAT);
        }
    }
}
