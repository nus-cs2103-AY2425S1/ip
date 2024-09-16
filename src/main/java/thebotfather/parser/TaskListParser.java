package thebotfather.parser;

import thebotfather.command.Command;
import thebotfather.command.PrintTaskListCommand;
import thebotfather.util.TheBotFatherException;

/**
 * The {@code TaskListParser} class is responsible for parsing the user's input
 * to create a {@link PrintTaskListCommand}, which prints the current task list.
 */
public class TaskListParser {

    /**
     * Parses the input and returns a {@link PrintTaskListCommand} to display the task list.
     *
     * @return a {@code PrintTaskListCommand} that prints the task list
     * @throws TheBotFatherException if an error occurs during command processing
     */
    public static Command parse() throws TheBotFatherException {
        return new PrintTaskListCommand();
    }
}
