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
                if (commandFields.length() <= 1) {
                    throw new HamyoException("Usage: todo [task description]");
                }
                tasks.addTask(TaskType.TODO, commandFields.substring(1));
                break;

            case "deadline":
                if (commandFields.length() <= 1) {
                    throw new HamyoException("Usage: deadline [task description] /by [deadline]");
                }
                String[] split = commandFields.substring(1).split(" /by ");
                if (split.length != 2) {
                    throw new HamyoException("Usage: deadline [task description] /by [deadline]");
                }
                tasks.addTask(TaskType.DEADLINE, split[0], split[1]);
                break;

            case "event":
                if (commandFields.length() <= 1) {
                    throw new HamyoException(
                        "Usage: event [task description] /from [start timestamp] /to [end timestamp]");
                }
                split = commandFields.substring(1).split(" /from | /to ");
                if (split.length != 3) {
                    throw new HamyoException(
                        "Usage: event [task description] /from [start timestamp] /to [end timestamp]");
                }
                tasks.addTask(TaskType.EVENT, split[0], split[1], split[2]);
                break;

            case "list":
                tasks.listTasks();
                break;

            case "listDate":
                if (commandFields.length() <= 1) {
                    throw new HamyoException("Usage: listDate yyyy-MM-dd.");
                }
                tasks.listTasksByDate(commandFields.substring(1));
                break;

            case "find":
                if (commandFields.length() <= 1) {
                    throw new HamyoException("Usage: find [index]");
                }
                tasks.listTasksByKeyword(commandFields.substring(1));
                break;

            case "mark":
                if (commandFields.length() <= 1) {
                    throw new HamyoException("Usage: mark [index]");
                }
                try {
                    tasks.markTask(Integer.parseInt(commandFields.substring(1)) - 1);
                } catch (NumberFormatException e) {
                    throw new HamyoException("Usage: mark [index]");
                }
                break;

            case "unmark":
                if (commandFields.length() <= 1) {
                    throw new HamyoException("Usage: unmark [index]");
                }
                try {
                    tasks.unmarkTask(Integer.parseInt(commandFields.substring(1)) - 1);
                } catch (NumberFormatException e) {
                    throw new HamyoException("Usage: unmark [index]");
                }
                break;

            case "delete":
                if (commandFields.length() <= 1) {
                    throw new HamyoException("Usage: delete [index]");
                }
                try {
                    tasks.deleteTask(Integer.parseInt(commandFields.substring(1)) - 1);
                } catch (NumberFormatException e) {
                    throw new HamyoException("Usage: delete [index]");
                }
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
}
