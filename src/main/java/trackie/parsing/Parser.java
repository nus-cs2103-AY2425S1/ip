package trackie.parsing;

import trackie.commands.*;

public class Parser {
    public static Command parseCommand(String input) {
        String[] arguments = input.split(" ");
        switch (arguments[0]) {
        case "t", "d", "e":
            return new AddCommand(arguments);
        case "mark":
            return new MarkCommand(arguments);
        case "unmark":
            return new UnmarkCommand(arguments);
        case "rm":
            return new DeleteCommand(arguments);
        case "find":
            return new FindCommand(arguments);
        case "ls":
            return new ListCommand();
        case "help":
            return new HelpCommand();
        default:
            return new InvalidCommand();
        }
    }

}
