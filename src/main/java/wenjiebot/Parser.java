package wenjiebot;

import wenjiebot.commands.AddCommand;
import wenjiebot.commands.ByeCommand;
import wenjiebot.commands.Command;
import wenjiebot.commands.DeleteCommand;
import wenjiebot.commands.FindCommand;
import wenjiebot.commands.ListCommand;
import wenjiebot.commands.MarkCommand;
import wenjiebot.commands.UnmarkCommand;
import wenjiebot.exceptions.NoFollowUpException;
import wenjiebot.exceptions.UnknownCommandException;

/**
 * The Parser class is responsible for interpreting user input
 * and returning the corresponding Command object for execution in the wenjiebot application.
 */
public class Parser {

    private static String regexToDo = "todo .*";
    private static String regexDeadline = "deadline .* /by .*";
    private static String regexEvent = "event .* /.* /.*";

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The user's input as a String.
     * @return The corresponding Command object based on the user's input.
     * @throws UnknownCommandException If the command is not recognized.
     */
    public static Command parse(String input) throws UnknownCommandException, NoFollowUpException {
        String[] parts = input.split(" ");
        String action = parts[0].toLowerCase();

        switch (action) {
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
            if (input.matches(regexToDo)) {
                return new AddCommand(false, input, AddCommand.TypeOfEvent.TODO);
            } else {
                throw new NoFollowUpException();
            }
            // Fallthrough

        case "event":
            if (input.matches(regexEvent)) {
                return new AddCommand(false, input, AddCommand.TypeOfEvent.EVENT);
            } else {
                throw new NoFollowUpException();
            }
            // Fallthrough

        case "deadline":
            if (input.matches(regexDeadline)) {
                return new AddCommand(false, input, AddCommand.TypeOfEvent.DEADLINE);
            } else {
                throw new NoFollowUpException();
            }
            // Fallthrough

        default:
            throw new UnknownCommandException();
        }
    }
}
