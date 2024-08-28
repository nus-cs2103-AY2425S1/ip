package hue.parser;

import hue.command.*;
import hue.HueException;
public class Parser {
    /**
     * Parses the user input to generate a command.
     *
     * @param fullCommand The full command string input by the user.
     * @return The {@code Command} that corresponds to the user input.
     * @throws HueException If the input command is invalid or unrecognized.
     */
    public static Command parse(String fullCommand) throws HueException {
        if (fullCommand.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("mark")) {
            return new MarkCommand(fullCommand);
        } else if (fullCommand.startsWith("unmark")) {
            return new UnmarkCommand(fullCommand);
        } else if (fullCommand.startsWith("todo")) {
            return new AddTodoCommand(fullCommand);
        } else if (fullCommand.startsWith("deadline")) {
            return new AddDeadlineCommand(fullCommand);
        } else if (fullCommand.startsWith("event")) {
            return new AddEventCommand(fullCommand);
        } else if (fullCommand.startsWith("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (fullCommand.startsWith("find")) {
            return new FindCommand(fullCommand);
        } else {
            throw new HueException("I'm sorry, but I don't know what that means. Womp Womp :(");
        }
    }
}

