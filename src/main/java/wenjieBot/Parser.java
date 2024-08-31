package wenjieBot;

import wenjieBot.commands.*;
import wenjieBot.exceptions.UnknownCommandException;

/**
 * The Parser class is responsible for interpreting user input
 * and returning the corresponding Command object for execution in the wenjieBot application.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The user's input as a String.
     * @return The corresponding Command object based on the user's input.
     * @throws UnknownCommandException If the command is not recognized.
     */
    public static Command parse(String input) throws UnknownCommandException {
        String[] parts = input.split(" ");
        String action = parts[0].toLowerCase();

        switch (action){
        case "mark":
            return new MarkCommand(false, input);
            // Fallthrough

        case "unmark":
            return new UnmarkCommand(false, input);
            // Fallthrough

        case "delete":
            return new DeleteCommand(false, input);
            // Fallthrough

        case "bye":
            return new ByeCommand(true, input);
            // Fallthrough

        case "find": {
            return new FindCommand(false, input);
            // Fallthrough
        }

        case "list":
            return new ListCommand(false, input);
            // Fallthrough

        case "todo":
            return new AddCommand(false, input, AddCommand.TypeOfEvent.TODO);
            // Fallthrough

        case "event":
            return new AddCommand(false, input, AddCommand.TypeOfEvent.EVENT);
            // Fallthrough

        case "deadline":
            return new AddCommand(false, input, AddCommand.TypeOfEvent.DEADLINE);
            // Fallthrough

        default:
            throw new UnknownCommandException();
        }
    }
}
