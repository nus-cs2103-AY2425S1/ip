package trackie.parsing;

import trackie.commands.*;

/**
 * Represents a parser that is used to determine which command to execute, based on the user input.
 */
public class Parser {
    /**
     * Parses the command given by the user and returns a corresponding command
     * depending on the first argument that the user provides.
     *
     * @param input the input string provided by the user.
     * @return a relevant command, or and invalid command in the default case.
     */
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
