public class Parser {

    public static Command parse(String fullCommand) throws SusanException {
        String[] commandParts = fullCommand.split(" ", 2);
        String commandWord = commandParts[0];

        switch (commandWord) {
            case "todo":
                return new AddCommand("todo", commandParts);
            case "deadline":
                return new AddCommand("deadline", commandParts);
            case "event":
                return new AddCommand("event", commandParts);
            case "list":
                return new ListCommand();
            case "delete":
                return new DeleteCommand(commandParts);
            case "mark":
                return new MarkCommand(commandParts, true);
            case "unmark":
                return new MarkCommand(commandParts, false);
            case "bye":
                return new ExitCommand();
            default:
                throw new SusanException("I'm sorry, but I don't know what that means.");
        }
    }
}
