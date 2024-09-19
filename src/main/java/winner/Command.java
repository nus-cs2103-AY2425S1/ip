package winner;

/**
 * Represents a Command that encapsulates a specific CommandType and String representing user input.
 */
public class Command {
    private final CommandType commandType;
    private final String input;

    /**
     * Creates a new Command object.
     *
     * @param commandType CommandType representing user's command.
     * @param input String representing user's input.
     */
    public Command(CommandType commandType, String input) {
        this.commandType = commandType;
        this.input = input;
    }

    /**
     * Executes the command based on the CommandType.
     *
     * @param taskList TaskList object that manages the list of tasks.
     * @return String representing the response from executing the specific command.
     * @throws WinnerException If there is an issue with user input.
     */
    public String execute(TaskList taskList) throws WinnerException {
        return switch (this.commandType) {
        case HI_AGAIN -> executeHiAgain();
        case HELP -> executeHelp();
        case TODO -> executeAddToDo(taskList);
        case DEADLINE -> executeAddDeadline(taskList);
        case EVENT -> executeAddEvent(taskList);
        case LIST -> executeList(taskList);
        case MARK -> executeMark(taskList);
        case UNMARK -> executeUnmark(taskList);
        case DELETE -> executeDelete(taskList);
        case FIND -> executeFind(taskList);
        case BYE -> executeBye();
        default -> executeUnknown();
        };
    }

    /**
     * Handles the "hi" or "hello" command and returns a "hi!" message.
     *
     * @return String representing a greeting message.
     */
    public String executeHiAgain() {
        return Ui.hiAgain();
    }

    /**
     * Handles the "help" command and returns a list of commands a user can use.
     *
     * @return String representing a list of commands.
     */
    public String executeHelp() {
        return Ui.winnerGivesHelp();
    }

    /**
     * Handles the "todo" command by adding a ToDo task into the task list.
     *
     * @param taskList TaskList object that manages the list of tasks.
     * @return String indicating that ToDo task has been added.
     * @throws WinnerException If task description is missing.
     */
    public String executeAddToDo(TaskList taskList) throws WinnerException {
        String description = input.split("todo", 2)[1].trim().toLowerCase();
        if (description.isEmpty()) {
            throw new WinnerException("""
                        Expected format for adding todo task:
                        todo (task)""");
        }
        return taskList.addToDo(description);
    }

    /**
     * Handles the "deadline" command by adding a Deadline task into the task list.
     *
     * @param taskList TaskList object that manages the list of tasks.
     * @return String indicating that Deadline task has been added.
     * @throws WinnerException If format is incorrect or missing.
     */
    public String executeAddDeadline(TaskList taskList) throws WinnerException {
        String[] parts = input.split("(?i)\\bdeadline\\b | \\bby\\b");
        if (parts.length != 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new WinnerException("""
                        Expected format for adding deadline task:
                        deadline (task) by (dd/mm/yyyy) at (time - 24 hour format)""");
        }
        return taskList.addDeadline(parts[1].trim().toLowerCase(), parts[2]);
    }

    /**
     * Handles the "event" command by adding an Event task into the task list.
     *
     * @param taskList TaskList object that manages the list of tasks.
     * @return String indicating that Event task has been added.
     * @throws WinnerException If format is incorrect or missing.
     */
    public String executeAddEvent(TaskList taskList) throws WinnerException {
        String[] parts = input.split("(?i)\\bevent\\b | \\bfrom\\b | \\bto\\b");
        if (parts.length != 4 || parts[1].trim().isEmpty()
                || parts[2].trim().isEmpty() || parts[3].trim().isEmpty()) {
            throw new WinnerException("""
                        Expected format for adding event task:
                        event (task) from (start) to (end)""");
        }
        String description = parts[1].trim().toLowerCase(); //TaskList
        String start = parts[2].trim().toLowerCase();
        String end = parts[3].trim().toLowerCase();
        return taskList.addEvent(description, start, end);
    }

    /**
     * Handles the "list" command by returning a list of all the tasks.
     *
     * @param taskList TaskList object that manages the list of tasks.
     * @return String representing the list of tasks.
     */
    public String executeList(TaskList taskList) {
        return taskList.listTasks();
    }

    /**
     * Handles the "mark" command by marking a task as done.
     *
     * @param taskList TaskList object that manages the list of tasks.
     * @return String indicating that the task has been marked as done.
     * @throws WinnerException If the task number is invalid.
     */
    public String executeMark(TaskList taskList) throws WinnerException {
        int taskNumber = getTaskNumber(input);
        if (taskNumber < 1 || taskNumber > taskList.getNoOfTasks()) {
            throw new WinnerException("""
                        Oh no! I cannot mark this task as done because the number is invalid.
                        Expected format for marking tasks as done:
                        mark (task number)""");
        }
        return taskList.markTaskAsDone(taskNumber);
    }

    /**
     * Handles the "unmark" commmand by marking the task as undone.
     *
     * @param taskList TaskList object that manages the list of tasks.
     * @return String indicating that the task has been marked as undone.
     * @throws WinnerException If the task number is invalid.
     */
    public String executeUnmark(TaskList taskList) throws WinnerException {
        int taskNumber = getTaskNumber(input);
        if (taskNumber < 1 || taskNumber > taskList.getNoOfTasks()) {
            throw new WinnerException("""
                        Oh no! I cannot unmark this done task because the number is invalid.
                        Expected format for unmarking done tasks:
                        unmark (task number)""");
        }
        return taskList.unmarkDoneTask(taskNumber);
    }

    /**
     * Handles the "delete" command by removing a task from the task list.
     *
     * @param taskList TaskList object that manages the list of tasks.
     * @return String indicating that the task has been deleted successfully.
     * @throws WinnerException If task number is invalid.
     */
    public String executeDelete(TaskList taskList) throws WinnerException {
        int taskNumber = getTaskNumber(input);
        if (taskNumber < 1 || taskNumber > taskList.getNoOfTasks()) {
            throw new WinnerException("""
                        Oh no! I cannot remove this task from the list because the number is invalid.
                        Expected format for removing tasks:
                        delete (task number)""");
        }
        return taskList.deleteTask(taskNumber);
    }

    /**
     * Handles the "find" command by searching for tasks that contain the given keyword.
     *
     * @param taskList TaskList object that manages the list of tasks.
     * @return String list of tasks that contain the specified keyword.
     * @throws WinnerException If keyword is missing from input.
     */
    public String executeFind(TaskList taskList) throws WinnerException {
        String keyword = input.split("(?i)\\bfind\\b")[1].trim().toLowerCase();
        if (keyword.isEmpty()) {
            throw new WinnerException("""
                        Expected format for finding all tasks with keyword:
                        find (keyword)""");
        }
        return taskList.findTasksWithKeyword(keyword);
    }

    /**
     * Handles the "bye" command by returning a goodbye message.
     *
     * @return String representing a goodbye message.
     */
    public String executeBye() {
        return Ui.winnerSaysBye();
    }

    /**
     * Handles unknown commands by throwing a WinnerException.
     *
     * @return WinnerException indicating that the command is unknown.
     * @throws WinnerException If command is not recognised.
     */
    public String executeUnknown() throws WinnerException {
        throw new WinnerException("Oops sorry! I do not know what that means :(");
    }

    /**
     * Returns the task number by extracting from String representing user input.
     *
     * @param input User input from Scanner as String.
     * @return An integer representing the task number.
     * @throws WinnerException If input does not match a task number or if there are issues with the input format.
     */
    public static int getTaskNumber(String input) throws WinnerException {
        String numberString = input.replaceAll("[^0-9]", "");
        if (numberString.isEmpty()) {
            throw new WinnerException("Please input a task number instead.");
        }
        return Integer.parseInt(numberString);
    }
}
