public class Parser {
    public static Command parse(String input) throws SageException {
        String[] fullCommand = input.split(" ", 2);
        String commandType = fullCommand[0];

        switch (commandType) {
            case "list":
                return new ListCommand();

            case "mark":
                return new MarkCommand(fullCommand[1], true);

            case "unmark":
                return new MarkCommand(fullCommand[1], false);

            case "todo":
                return new AddCommand("todo", fullCommand[1]);

            case "deadline":
                return new AddCommand("deadline", fullCommand[1]);

            case "event":
                return new AddCommand("event", fullCommand[1]);

            case "delete":
                return new DeleteCommand(fullCommand[1]);

            case "bye":
                return new ExitCommand();

            default:
                throw new SageException("Invalid Command");
        }
    }
}
