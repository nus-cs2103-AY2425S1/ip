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
        String commandWord = fullCommand.split(" ")[0].toLowerCase(); // Get the first word of the command

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(fullCommand);
        case "unmark":
            return new UnmarkCommand(fullCommand);
        case "todo":
            return new AddTodoCommand(fullCommand);
        case "deadline":
            return new AddDeadlineCommand(fullCommand);
        case "event":
            return new AddEventCommand(fullCommand);
        case "delete":
            return new DeleteCommand(fullCommand);
        case "find":
            return new FindCommand(fullCommand);
        case "reschedule":
            return new RescheduleCommand(fullCommand);
        default:
            throw new HueException("I'm sorry, but I don't know what that means. Womp Womp :(");
        }
    }
}

