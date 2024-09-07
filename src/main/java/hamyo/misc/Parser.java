package hamyo.misc;

import hamyo.tasks.TaskList;
import hamyo.tasks.TaskType;

/**
 * Deals with making sense of the user command.
 *
 * @author Han Yu
 */
public class Parser {

    /**
     * Parse method takes in user input from terminal and executes the command.
     * If no command is found, a HamyoException occurs and prompted in terminal.
     *
     * @param fullCommand The full command/ input given by the user.
     * @param tasks The list of the users' tasks.
     * @return false if the user command ("bye") terminates the chatbot, true
     *     otherwise. false: application terminates, true: application continues.
     */
    public static boolean parse(TaskList tasks, String fullCommand) {
        String commandType = fullCommand.split(" ")[0];
        String commandFields = fullCommand.substring(commandType.length());
        try {
            switch (commandType) {
            case "todo":
                parseToDo(commandFields, tasks);
                break;
            case "deadline":
                parseDeadline(commandFields, tasks);
                break;
            case "event":
                parseEvent(commandFields, tasks);
                break;
            case "list":
                tasks.listTasks();
                break;
            case "listDate":
                parseListDate(commandFields, tasks);
                break;
            case "find":
                parseFind(commandFields, tasks);
                break;
            case "mark":
                parseMark(commandFields, tasks);
                break;
            case "unmark":
                parseUnmark(commandFields, tasks);
                break;
            case "delete":
                parseDelete(commandFields, tasks);
                break;
            case "bye":
                return false;
            default:
                throw new HamyoException("Invalid Command!");
            }
        } catch (HamyoException e) {
            Ui.printException(e);
        }
        return true;
    }

    /**
     * Parse method takes in command fields in user input from terminal and
     * adds the specified To-Do Task to the tasks TaskList. If command fields
     * are invalid, a HamyoException is thrown.
     *
     * @param commandFields The fields input by user required to create a To-Do.
     * @param tasks The list of the users' tasks.
     * @throws HamyoException Thrown when command fields are invalid.
     */
    private static void parseToDo(String commandFields, TaskList tasks) throws HamyoException {
        if (commandFields.length() <= 1) {
            throw new HamyoException("Usage: todo [task description]");
        }
        tasks.addTask(TaskType.TODO, commandFields.substring(1));
    }

    /**
     * Parse method takes in command fields in user input from terminal and
     * adds the specified Deadline Task to the tasks TaskList. If command fields
     * are invalid, a HamyoException is thrown.
     *
     * @param commandFields The fields input by user required to create a Deadline
     * @param tasks The list of the users' tasks.
     * @throws HamyoException Thrown when command fields are invalid.
     */
    private static void parseDeadline(String commandFields, TaskList tasks) throws HamyoException {
        if (commandFields.length() <= 1) {
            throw new HamyoException("Usage: deadline [task description] /by [deadline]");
        }
        String[] split = commandFields.substring(1).split(" /by ");
        if (split.length != 2) {
            throw new HamyoException("Usage: deadline [task description] /by [deadline]");
        }
        tasks.addTask(TaskType.DEADLINE, split[0], split[1]);
    }

    /**
     * Parse method takes in command fields in user input from terminal and
     * adds the specified Event Task to the tasks TaskList. If command fields
     * are invalid, a HamyoException is thrown.
     *
     * @param commandFields The fields input by user required to create a Event.
     * @param tasks The list of the users' tasks.
     * @throws HamyoException Thrown when command fields are invalid.
     */
    private static void parseEvent(String commandFields, TaskList tasks) throws HamyoException {
        if (commandFields.length() <= 1) {
            throw new HamyoException(
                "Usage: event [task description] /from [start timestamp] /to [end timestamp]");
        }
        String[] split = commandFields.substring(1).split(" /from | /to ");
        if (split.length != 3) {
            throw new HamyoException(
                "Usage: event [task description] /from [start timestamp] /to [end timestamp]");
        }
        tasks.addTask(TaskType.EVENT, split[0], split[1], split[2]);
    }

    /**
     * Parse method takes in command fields in user input from terminal and
     * executes the command to list out tasks by the given date. If command fields
     * are invalid, a HamyoException is thrown.
     *
     * @param commandFields The given date.
     * @param tasks The list of the users' tasks.
     * @throws HamyoException Thrown when command fields are invalid.
     */
    private static void parseListDate(String commandFields, TaskList tasks) throws HamyoException {
        if (commandFields.length() <= 1) {
            throw new HamyoException("Usage: listDate yyyy-MM-dd.");
        }
        tasks.listTasksByDate(commandFields.substring(1));
    }

    /**
     * Parse method takes in command fields in user input from terminal and
     * executes the command to list out tasks by the given keyword. If command fields
     * are invalid, a HamyoException is thrown.
     *
     * @param commandFields The given keyword.
     * @param tasks The list of the users' tasks.
     * @throws HamyoException Thrown when command fields are invalid.
     */
    private static void parseFind(String commandFields, TaskList tasks) throws HamyoException {
        if (commandFields.length() <= 1) {
            throw new HamyoException("Usage: find [index]");
        }
        tasks.listTasksByKeyword(commandFields.substring(1));
    }

    /**
     * Parse method takes in command fields in user input from terminal and
     * executes the command to mark tasks of the specified index. If command fields
     * are invalid, a HamyoException is thrown.
     *
     * @param commandFields The specified index.
     * @param tasks The list of the users' tasks.
     * @throws HamyoException Thrown when command fields are invalid.
     */
    private static void parseMark(String commandFields, TaskList tasks) throws HamyoException {
        if (commandFields.length() <= 1) {
            throw new HamyoException("Usage: mark [index]");
        }
        try {
            tasks.markTask(Integer.parseInt(commandFields.substring(1)) - 1);
        } catch (NumberFormatException e) {
            throw new HamyoException("Usage: mark [index]");
        }
    }

    /**
     * Parse method takes in command fields in user input from terminal and
     * executes the command to unmark tasks of the specified index. If command fields
     * are invalid, a HamyoException is thrown.
     *
     * @param commandFields The specified index.
     * @param tasks The list of the users' tasks.
     * @throws HamyoException Thrown when command fields are invalid.
     */
    private static void parseUnmark(String commandFields, TaskList tasks) throws HamyoException {
        if (commandFields.length() <= 1) {
            throw new HamyoException("Usage: unmark [index]");
        }
        try {
            tasks.unmarkTask(Integer.parseInt(commandFields.substring(1)) - 1);
        } catch (NumberFormatException e) {
            throw new HamyoException("Usage: unmark [index]");
        }
    }

    /**
     * Parse method takes in command fields in user input from terminal and
     * executes the command to delete tasks of the specified index. If command fields
     * are invalid, a HamyoException is thrown.
     *
     * @param commandFields The specified index.
     * @param tasks The list of the users' tasks.
     * @throws HamyoException Thrown when command fields are invalid.
     */
    private static void parseDelete(String commandFields, TaskList tasks) throws HamyoException {
        if (commandFields.length() <= 1) {
            throw new HamyoException("Usage: delete [index]");
        }
        try {
            tasks.deleteTask(Integer.parseInt(commandFields.substring(1)) - 1);
        } catch (NumberFormatException e) {
            throw new HamyoException("Usage: delete [index]");
        }
    }
}
