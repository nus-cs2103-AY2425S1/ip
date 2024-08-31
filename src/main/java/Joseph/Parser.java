package Joseph;

public class Parser {

    public enum Command {
        EXIT("bye"),
        LIST("list"),
        HELP("help"),
        MARK("mark "),
        UNMARK("unmark "),
        TODO("todo "),
        DEADLINE("deadline "),
        EVENT("event "),
        DELETE("delete ");

        private final String commandText;

        Command(String commandText) {
            this.commandText = commandText;
        }

        public String getCommandText() {
            return commandText;
        }
    }

    public Command parseCommand(String input) throws UnknownCommandException {
        for (Command command : Command.values()) {
            if (input.startsWith(command.getCommandText())) {
                return command;
            }
        }
        throw new UnknownCommandException("That is not a recognised command!");
    }

    public int parseTaskNumber(String input, String commandText)
            throws InsufficientDetailsException {
        try {
            return Integer.parseInt(input.substring(commandText.length()).trim());
        } catch (NumberFormatException e) {
            throw new InsufficientDetailsException("I need a task number!");
        }
    }

    public String parseTodoDetails(String input, String commandText)
            throws InsufficientDetailsException {
        String details = input.substring(commandText.length()).trim();
        if (details.isEmpty()) {
            throw new InsufficientDetailsException("I need a description for a todo!");
        }
        return details;
    }

    public String[] parseDeadlineDetails(String input, String commandText)
            throws InsufficientDetailsException {
        String details[] = input.substring(commandText.length()).trim().split(" /");
        if (details.length < 2) {
            throw new InsufficientDetailsException("You need to provide a description " +
                    "and a due date for a deadline!");
        }
        return details;
    }

    public String[] parseEventDetails(String input, String commandText)
            throws InsufficientDetailsException {
        String details[] = input.substring(commandText.length()).trim().split(" /");
        if (details.length < 3) {
            throw new InsufficientDetailsException("You need to provide a description, " +
                    "a start and an end for an event!");
        }
        return details;
    }
}
