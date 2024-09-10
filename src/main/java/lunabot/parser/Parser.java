package lunabot.parser;

import lunabot.command.AddDeadlineCommand;
import lunabot.command.AddEventCommand;
import lunabot.command.AddToDoCommand;
import lunabot.command.Command;
import lunabot.command.DeleteCommand;
import lunabot.command.ExitCommand;
import lunabot.command.ListCommand;
import lunabot.command.MarkCommand;
import lunabot.command.UnmarkCommand;
import lunabot.exception.LunaBotException;

/**
 * The Parser class is responsible for interpreting user input
 * and returning the corresponding command.
 */
public class Parser {

    /**
     * Parses the user input and returns the appropriate Command object based on the input.
     *
     * @param input The user's input as a string.
     * @return A Command object corresponding to the user's input.
     * @throws LunaBotException If the input command is not recognized or formatted incorrectly.
     */
    public static Command parse(String input) throws LunaBotException {
        String[] fullCommand = input.split(" ");
        String command = fullCommand[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(input);
        case "unmark":
            return new UnmarkCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "todo":
            return new AddToDoCommand(input);
        case "deadline":
            return new AddDeadlineCommand(input);
        case "event":
            return new AddEventCommand(input);
        default:
            throw new LunaBotException(" Invalid command");
        }
    }
}
