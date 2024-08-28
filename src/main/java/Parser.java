public class Parser {

    public static Command parse(String input) throws FannyException {
        String[] cmdParts = input.split(" ", 2);
        String action = cmdParts[0];
        String arguments = cmdParts.length > 1 ? cmdParts[1] : "";

        switch (action) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(arguments);
            case "unmark":
                return new UnmarkCommand(arguments);
            case "todo":
                return new ToDoCommand(arguments);
            case "deadline":
                return new DeadlineCommand(arguments);
            case "event":
                return new EventCommand(arguments);
            case "delete":
                return new DeleteCommand(arguments);
            default:
                throw new FannyException("Sorry, I'm confused! Please try again.");
        }
    }

}