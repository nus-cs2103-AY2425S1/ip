package rose;

import rose.command.AddCommand;
import rose.command.Command;
import rose.command.DeleteCommand;
import rose.command.ExitCommand;
import rose.command.FindCommand;
import rose.command.ListCommand;
import rose.command.MarkCommand;
import rose.command.UnmarkCommand;
import rose.task.TaskType;

/**
 * The {@code Parser} class is responsible for interpreting and converting user input into commands
 * that the application can execute.
 * @see rose.command.Command
 */
public class Parser {

    /**
     * Parses the user's input and returns the corresponding {@link rose.command.Command} object.
     *
     * <p>The method interprets the first word of the input as the command type and the rest as
     * the command's details or parameters. It supports the following commands:</p>
     * <ul>
     *   <li><code>list</code> - Displays the list of tasks.</li>
     *   <li><code>mark [index]</code> - Marks the task at the given index as done.</li>
     *   <li><code>unmark [index]</code> - Marks the task at the given index as not done.</li>
     *   <li><code>todo [details]</code> - Adds a new todo task with the given details.</li>
     *   <li><code>event [details]</code> - Adds a new event task with the given details.</li>
     *   <li><code>deadline [details]</code> - Adds a new deadline task with the given details.</li>
     *   <li><code>delete [index]</code> - Deletes the task at the given index.</li>
     *   <li><code>bye</code> - Exits the application.</li>
     * </ul>
     *
     * @param fullCommand The full input string provided by the user.
     * @return The {@link rose.command.Command} object that corresponds to the input command.
     * @throws RoseException If the input command is unrecognized or the format is incorrect.
     */
    public static Command parse(String fullCommand) throws RoseException {
        String[] input = fullCommand.split(" ", 2);
        String command = input[0].toLowerCase();
        String message = (input.length > 1) ? input[1].trim() : "";

        try {
            switch (command) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(Integer.valueOf(message));
            case "unmark":
                return new UnmarkCommand(Integer.valueOf(message));
            case "todo":
                if (message.isEmpty()) {
                    throw new RoseException("You need to provide details for the TODO task.");
                }
                return new AddCommand(TaskType.TODO, message);
            case "event":
                if (message.isEmpty()) {
                    throw new RoseException("You need to provide details for the EVENT task.");
                }
                return new AddCommand(TaskType.EVENT, message);
            case "deadline":
                if (message.isEmpty()) {
                    throw new RoseException("You need to provide details for the DEADLINE task.");
                }
                return new AddCommand(TaskType.DEADLINE, message);
            case "find":
                if (message.isEmpty()) {
                    throw new RoseException("You need to provide keyword that you want to find.");
                }
                return new FindCommand(message);
            case "delete":
                return new DeleteCommand(Integer.valueOf(message));
            case "bye":
                return new ExitCommand();
            default:
                throw new RoseException("OOPS!!! I'm sorry, but I don't know that command :-(");
            }
        } catch (NumberFormatException e) {
            throw new RoseException("OOPS!!! You should provide a number of the task index.");
        }
    }
}

