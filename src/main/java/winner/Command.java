package winner;

public class Command {
    private final CommandType commandType;
    private final String input;

    public Command(CommandType commandType, String input) {
        this.commandType = commandType;
        this.input = input;
    }

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

    public String executeHiAgain() {
        return Ui.hiAgain();
    }

    public String executeHelp() {
        return Ui.winnerGivesHelp();
    }

    public String executeAddToDo(TaskList taskList) throws WinnerException {
        String description = input.split("todo", 2)[1].trim().toLowerCase();
        if (description.isEmpty()) {
            throw new WinnerException("""
                        Expected format for adding todo task:
                        todo (task)""");
        }
        return taskList.addToDo(description);
    }

    public String executeAddDeadline(TaskList taskList) throws WinnerException {
        String[] parts = input.split("(?i)\\bdeadline\\b | \\bby\\b");
        if (parts.length != 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new WinnerException("""
                        Expected format for adding deadline task:
                        deadline (task) by (dd/mm/yyyy) at (time - 24 hour format)""");
        }
        return taskList.addDeadline(parts[1].trim().toLowerCase(), parts[2]);
    }

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

    public String executeList(TaskList taskList) {
        return taskList.listTasks();
    }

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

    public String executeDelete(TaskList taskList) throws WinnerException{
        int taskNumber = getTaskNumber(input);
        if (taskNumber < 1 || taskNumber > taskList.getNoOfTasks()) {
            throw new WinnerException("""
                        Oh no! I cannot remove this task from the list because the number is invalid.
                        Expected format for removing tasks:
                        delete (task number)""");
        }
        return taskList.deleteTask(taskNumber);
    }

    public String executeFind(TaskList taskList) throws WinnerException{
        String keyword = input.split("(?i)\\bfind\\b")[1].trim().toLowerCase();
        if (keyword.isEmpty()) {
            throw new WinnerException("""
                        Expected format for finding all tasks with keyword:
                        find (keyword)""");
        }
        return taskList.findTasksWithKeyword(keyword);
    }

    public String executeBye() {
        return Ui.winnerSaysBye();
    }

    public String executeUnknown() throws WinnerException{
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
