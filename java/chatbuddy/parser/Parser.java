package chatbuddy.parser;

import chatbuddy.command.AddCommand;
import chatbuddy.command.Command;
import chatbuddy.command.DeleteCommand;
import chatbuddy.command.ExitCommand;
import chatbuddy.command.FindCommand;
import chatbuddy.command.ListCommand;
import chatbuddy.command.MarkCommand;
import chatbuddy.command.UnmarkCommand;
import chatbuddy.exception.ChatBuddyException;

/**
 * Parses user input and returns the appropriate command to execute.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param userInput The user's input string.
     * @return The command corresponding to the user input.
     * @throws ChatBuddyException If the user input is invalid.
     */
    public static Command parse(String userInput) throws ChatBuddyException {
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
            case "list":
                return new ListCommand();

            case "mark":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChatBuddyException("Please specify the task number to mark.");
                }
                return new MarkCommand(parts[1]);

            case "unmark":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChatBuddyException("Please specify the task number to unmark.");
                }
                return new UnmarkCommand(parts[1]);

            case "todo":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChatBuddyException("The description of a todo cannot be empty.");
                }
                return new AddCommand(parts[1], "T");

            case "deadline":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChatBuddyException("The description of a deadline cannot be empty.");
                }
                return new AddCommand(parts[1], "D");

            case "event":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChatBuddyException("The description of an event cannot be empty.");
                }
                return new AddCommand(parts[1], "E");

            case "delete":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChatBuddyException("Please specify the task number to delete.");
                }
                return new DeleteCommand(parts[1]);

            case "find":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new ChatBuddyException("Please provide a keyword to search.");
                }
                return new FindCommand(parts[1]);

            case "bye":
                return new ExitCommand();

            default:
                throw new ChatBuddyException("Sorry, I don't understand that command.");
        }
    }
}
