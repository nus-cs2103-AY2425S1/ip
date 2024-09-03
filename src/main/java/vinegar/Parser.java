package vinegar;

import vinegar.command.*;

public class Parser {
    public static Command parse(String fullCommand) throws VinegarException {
        String[] inputParts = fullCommand.split(" ", 2);
        String instruction = inputParts[0].toLowerCase();

        switch (instruction) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(inputParts);
            case "unmark":
                return new UnmarkCommand(inputParts);
            case "todo":
                return new AddTodoCommand(inputParts);
            case "deadline":
                return new AddDeadlineCommand(inputParts);
            case "event":
                return new AddEventCommand(inputParts);
            case "delete":
                return new DeleteCommand(inputParts);
            default:
                throw new VinegarException("Please use these commands: todo, deadline, event, list, mark, unmark, bye");
        }
    }
}
