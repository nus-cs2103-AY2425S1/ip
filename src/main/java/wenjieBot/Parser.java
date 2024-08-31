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
        Command response = null;

        switch (action) {
            case "mark": {
                response = new MarkCommand(false, input);
                break;
            }

            case "unmark": {
                response = new UnmarkCommand(false, input);
                break;
            }

            case "delete": {
                response = new DeleteCommand(false, input);
                break;
            }

            case "bye": {
                response = new ByeCommand(true, input);
                break;
            }

            case "list": {
                response = new ListCommand(false, input);
                break;
            }

            case "todo": {
                response = new AddCommand(false, input, AddCommand.TypeOfEvent.TODO);
                break;
            }

            case "event": {
                response = new AddCommand(false, input, AddCommand.TypeOfEvent.EVENT);
                break;
            }

            case "deadline": {
                response = new AddCommand(false, input, AddCommand.TypeOfEvent.DEADLINE);
                break;
            }

            default: {
                throw new UnknownCommandException();
            }
        }

        return response;
    }
}
