package froggy;

/**
 * The {@code Parser} class is responsible for interpreting user input and converting it
 * into a {@code Command} that Froggy can execute. The parser reads the first word of the input
 * as the keyword and matches it to the corresponding command.
 */
public class Parser {

    /**
     * Constructs a new {@code Parser} object. The {@code Parser} is used to process
     * user input and determine which command to execute.
     */
    public Parser() {
    }

    /**
     * Parses the user input and returns the corresponding {@code Command} based on the keyword.
     * The first word in the input is treated as the command keyword, which is matched to one of the
     * following commands: bye, list, mark, unmark, delete, find, todo, deadline, or event.
     *
     * @param input The user input as a string.
     * @return The {@code Command} object that corresponds to the keyword in the input.
     */
    public Command parse(String input) {

        String keyword = input.split("\\s+")[0].toLowerCase();
        switch (keyword) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(input);
        case "unmark":
            return new UnmarkCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "find":
            return new FindCommand(input);
        case "todo":
            return new AddTodoCommand(input);
        case "deadline":
            return new AddDeadlineCommand(input);
        case "event":
            return new AddEventCommand(input);
        default:
            return new InvalidCommand();
        }
    }
}
