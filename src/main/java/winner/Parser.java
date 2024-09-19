package winner;

/**
 * Represents a Parser to handle the parsing of user inputs and directs the appropriate actions to be taken
 * based on the command given by the user.
 */
public class Parser {

    /**
     * Parses the user input and performs the appropriate action based on the command.
     *
     * @param input User input from Scanner as String.
     * @param taskList TaskList object that contains and manages the list of tasks.
     * @throws WinnerException If input does not match any known command or if there are issues with the input format.
     */
    public static String parseInput(String input, TaskList taskList) throws WinnerException {
        assert taskList != null : "TaskList should not be null.";
        String msg = "";
        if (input.matches("(?i)hi|hello")) {
            msg = Ui.hiAgain();

        } else if (input.matches("(?i)\\bhelp\\b")) {
            msg = Ui.winnerGivesHelp();

        } else if (input.matches("(?i).*\\btodo\\b.*")) {
            String description = input.split("todo", 2)[1].trim().toLowerCase();
            if (description.isEmpty()) {
                throw new WinnerException("""
                        Expected format for adding todo task:
                        todo (task)""");
            }
            msg = taskList.addToDo(description);

        } else if (input.matches("(?i).*\\bdeadline\\b.*")) {
            String[] parts = input.split("(?i)\\bdeadline\\b | \\bby\\b");
            if (parts.length != 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                throw new WinnerException("""
                        Expected format for adding deadline task:
                        deadline (task) by (dd/mm/yyyy) at (time - 24 hour format)""");
            }
            msg = taskList.addDeadline(parts[1].trim().toLowerCase(), parts[2]);

        } else if (input.matches("(?i).*\\bevent\\b.*")) {
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
            msg = taskList.addEvent(description, start, end);

        } else if (input.matches("(?i).*\\blist\\b.*")) {
            msg = taskList.listTasks();

        } else if (input.matches("(?i).*\\bmark\\b.*")) {
            int taskNumber = getTaskNumber(input);
            if (taskNumber < 1 || taskNumber > taskList.getNoOfTasks()) {
                throw new WinnerException("""
                        Oh no! I cannot mark this task as done because the number is invalid.
                        Expected format for marking tasks as done:
                        mark (task number)""");
            }
            msg = taskList.markTaskAsDone(taskNumber);

        } else if (input.matches("(?i).*\\bunmark\\b.*")) {
            int taskNumber = getTaskNumber(input);
            if (taskNumber < 1 || taskNumber > taskList.getNoOfTasks()) {
                throw new WinnerException("""
                        Oh no! I cannot unmark this done task because the number is invalid.
                        Expected format for unmarking done tasks:
                        unmark (task number)""");
            }
            msg = taskList.unmarkDoneTask(taskNumber);

        } else if (input.matches("(?i).*\\bdelete\\b.*")) {
            int taskNumber = getTaskNumber(input);
            if (taskNumber < 1 || taskNumber > taskList.getNoOfTasks()) {
                throw new WinnerException("""
                        Oh no! I cannot remove this task from the list because the number is invalid.
                        Expected format for removing tasks:
                        delete (task number)""");
            }
            msg = taskList.deleteTask(taskNumber);

        } else if (input.matches("(?i).*\\bfind\\b.*")) {
            String keyword = input.split("(?i)\\bfind\\b")[1].trim().toLowerCase();
            if (keyword.isEmpty()) {
                throw new WinnerException("""
                        Expected format for finding all tasks with keyword:
                        find (keyword)""");
            }
            msg = taskList.findTasksWithKeyword(keyword);

        } else if (input.matches("(?i).*bye.*")) {
            msg = Ui.winnerSaysBye();

        } else {
            throw new WinnerException("Oops sorry! I do not know what that means :(");
        }
        assert msg != null && !msg.isEmpty() : "Message should not be null or empty.";
        return msg;
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
