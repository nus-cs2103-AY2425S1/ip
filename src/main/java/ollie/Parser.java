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
     * @throws OllieException If the task number is invalid.
     */
    public static int parseTaskNumber(String userCommand, int prefixLength) throws OllieException {
        try {
            return Integer.parseInt(userCommand.substring(prefixLength).trim()) - 1;
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
     *
     * @param userInput The input entered by the user.
     * @param taskList  The list of tasks.
     * @param ui        The user interface.
     * @return The response by Ollie.
     */
    public static String parseUserCommand(String userInput, TaskList taskList, Ui ui) {
        String[] splitInput = userInput.split(" ");
        String command = splitInput[0].toLowerCase();

        try {
            switch (command) {
            case "hello":
                return ui.greeting();
            case "bye":
                return ui.exit();
            case "list":
                return taskList.listTasks();
            case "mark":
                int taskNumber = Parser.parseTaskNumber(userInput, 5);
                return taskList.markTaskAsDone(taskNumber);
            case "unmark":
                int taskNumberToUnmark = Parser.parseTaskNumber(userInput, 7);
                return taskList.unmarkTaskAsDone(taskNumberToUnmark);
            case "delete":
                int taskNumberDelete = Parser.parseTaskNumber(userInput, 7);
                return taskList.deleteTask(taskNumberDelete);
            case "find":
                String keyword = Parser.parseKeyword(userInput);
                return taskList.findTasksByKeyword(keyword);
            case "todo":
                return taskList.addTask(Todo.createTask(userInput));
            case "deadline":
                return taskList.addTask(Deadline.createTask(userInput));
            case "event":
                return taskList.addTask(Event.createTask(userInput));
            default:
                return Ui.returnErrorAsString(
                        new OllieException("I am sorry, but please enter a valid command! ☺"));
            }
        } catch (OllieException e) {
            return Ui.returnErrorAsString(e);
        }
    }
}

