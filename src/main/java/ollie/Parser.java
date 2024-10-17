package ollie;

import ollie.exception.OllieException;
import ollie.task.Deadline;
import ollie.task.Event;
import ollie.task.TaskList;
import ollie.task.Todo;
import ollie.ui.Ui;

/**
 * The Parser class deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses the task number from the user's command.
     *
     * @param userCommand  The command entered by the user.
     * @param prefixLength The length of the prefix to trim.
     * @return The parsed task number.
     * @throws OllieException If the task number is invalid or not provided.
     */
    public static int parseTaskNumber(String userCommand, int prefixLength) throws OllieException {
        if (userCommand.length() <= prefixLength) {
            throw new OllieException("Please specify a task number! ☺");
        }

        String remainingCommand = userCommand.substring(prefixLength).trim();

        if (remainingCommand.isEmpty()) {
            throw new OllieException("Please specify a task number! ☺");
        }

        try {
            return Integer.parseInt(remainingCommand) - 1;
        } catch (NumberFormatException e) {
            throw new OllieException("Please enter a valid task number! ☺");
        }
    }

    /**
     * Parses the keyword from the user's command.
     *
     * @param userCommand to find the keyword.
     * @return string with keyword.
     */
    public static String parseKeyword(String userCommand) throws OllieException {
        String[] words = userCommand.split(" ", 2);
        if (words.length < 2) {
            throw new OllieException("The find command requires a keyword.");
        }
        return words[1].trim();
    }

    /**
     * Parses the user's command and returns the appropriate action.
     * Users can enter the actions commands starting with "-".
     *
     * @param userInput The input entered by the user.
     * @param taskList  The list of tasks.
     * @param ui        The user interface.
     * @return The response by Ollie.
     */
    public static String parseUserCommand(String userInput, TaskList taskList, Ui ui) {
        assert userInput != null : "User input cannot be null.";
        assert taskList != null : "Task list cannot be null.";
        assert ui != null : "Ui object cannot be null.";

        String[] splitInput = userInput.split(" ");
        String command = splitInput[0].toLowerCase();

        try {
            return executeCommand(command, userInput, taskList, ui);
        } catch (OllieException e) {
            return Ui.returnErrorAsString(e);
        }
    }

    /**
     * Executes the command based on the input.
     *
     * @param command The command to execute.
     * @param userInput The full user input.
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @return The response by Ollie.
     * @throws OllieException If there is an error in executing the command.
     */
    private static String executeCommand(String command, String userInput, TaskList taskList, Ui ui)
            throws OllieException {
        switch (command) {
        case "hello":
            return Ui.greet();
        case "-b": // '-b' as an alias for 'bye'
            return Ui.exit();
        case "-l": // '-l' as an alias for 'list'
            return taskList.listTasks();
        case "-m": // '-m' as an alias for 'mark'
            int taskNumber = Parser.parseTaskNumber(userInput, 3);
            return taskList.markTaskAsDone(taskNumber);
        case "-u": // '-u' as an alias for 'unmark'
            int taskNumberToUnmark = Parser.parseTaskNumber(userInput, 3);
            return taskList.unmarkTaskAsDone(taskNumberToUnmark);
        case "-d": // '-d' as an alias for 'delete'
            int taskNumberDelete = Parser.parseTaskNumber(userInput, 3);
            return taskList.deleteTask(taskNumberDelete);
        case "-f": // '-f' as an alias for 'find'
            String keyword = Parser.parseKeyword(userInput);
            return taskList.findTasksByKeyword(keyword);
        case "todo":
            return taskList.addTask(Todo.createTask(userInput));
        case "deadline":
            return taskList.addTask(Deadline.createTask(userInput));
        case "event":
            return taskList.addTask(Event.createTask(userInput));
        default:
            throw new OllieException("I am sorry, but please enter a valid command! ☺");
        }
    }
}

