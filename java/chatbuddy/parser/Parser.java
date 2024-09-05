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

public class Parser {

    public static Command parse(String userInput) throws ChatBuddyException {
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parts[1]);
            case "unmark":
                return new UnmarkCommand(parts[1]);
            case "todo":
                return new AddCommand(parts[1], "T");
            case "deadline":
                return new AddCommand(parts[1], "D");
            case "event":
                return new AddCommand(parts[1], "E");
            case "delete":
                return new DeleteCommand(parts[1]);
            case "find":
                return new FindCommand(parts[1]);
            case "bye":
                return new ExitCommand();
            default:
                throw new ChatBuddyException("Sorry, I don't understand that command.");
        }
    }
}
