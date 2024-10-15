package lunabot.parser;

import lunabot.command.AddDeadlineCommand;
import lunabot.command.AddEventCommand;
import lunabot.command.AddToDoCommand;
import lunabot.command.Command;
import lunabot.command.DeleteCommand;
import lunabot.command.ExitCommand;
import lunabot.command.FindCommand;
import lunabot.command.HelpCommand;
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
        assert input != null && !input.isEmpty()
                : "Input cannot be null or empty";
        String[] fullCommand = input.split(" ");
        String command = fullCommand[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "ls":
            return new ListCommand();
        case "mark":
            return new MarkCommand(input);
        case "unmark":
            return new UnmarkCommand(input);
        case "del":
            return new DeleteCommand(input);
        case "t":
            return new AddToDoCommand(input);
        case "d":
            return new AddDeadlineCommand(input);
        case "e":
            return new AddEventCommand(input);
        case "find":
            return new FindCommand(input);
        case "?":
            return new HelpCommand();
        default:
            throw new LunaBotException("Invalid command");
        }
    }
}
