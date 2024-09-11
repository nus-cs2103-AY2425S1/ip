package froggy;

/**
 * Parses user input
 */
public class Parser {

    public Parser() {

    }

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
