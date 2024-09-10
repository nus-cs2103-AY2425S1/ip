package LunaBot.parser;

import LunaBot.command.AddDeadlineCommand;
import LunaBot.command.AddEventCommand;
import LunaBot.command.AddToDoCommand;
import LunaBot.command.Command;
import LunaBot.command.DeleteCommand;
import LunaBot.command.ExitCommand;
import LunaBot.command.ListCommand;
import LunaBot.command.MarkCommand;
import LunaBot.command.UnmarkCommand;
import LunaBot.exception.LunaBotException;

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
        case "mark" :
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
