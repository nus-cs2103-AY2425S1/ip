package trackie.parsing;

import trackie.commands.*;

public class Parser {
    public static Command parseCommand(String input) {
        String[] arguments = input.split(" ");
        switch (arguments[0]) {
            case "todo":
                return new AddCommand(arguments);
            case "deadline":
                return new AddCommand(arguments);
            case "event":
                return new AddCommand(arguments);
            case "mark":
                return new MarkCommand(arguments);
            case "unmark":
                return new UnmarkCommand(arguments);
            case "delete":
                return new DeleteCommand(arguments);
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            default:
                return new InvalidCommand();

        }
    }

}
