package chatbaby;

/**
 * Parses user input into commands that the ChatBaby application can execute.
 * This class interprets the command string and returns the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the given command string into a Command object.
     *
     * @param curCommand The command string input by the user.
     * @return A Command object corresponding to the user input.
     * @throws ChatBabyException If the command is not recognized or cannot be parsed.
     */
    public class Parser {
        public static Command parse(String curCommand) throws ChatBabyException {
            String[] parts = curCommand.split(" ", 2);
            String commandWord = parts[0];

            switch (commandWord) {
                case "bye":
                    return new ExitCommand("bye");
                case "list":
                    return new ListCommand("list");
                case "todo":
                    return new AddCommand(curCommand, TaskType.TODO);
                case "deadline":
                    return new AddCommand(curCommand, TaskType.DEADLINE);
                case "event":
                    return new AddCommand(curCommand, TaskType.EVENT);
                case "delete":
                    return new DeleteCommand(curCommand);
                case "mark":
                    return new MarkCommand(curCommand);
                case "unmark":
                    return new UnmarkCommand(curCommand);
                case "listEndingOn":
                    return new ListOnDateCommand(curCommand);
                case "find":
                    return new FindCommand(curCommand);
                default:
                    throw new ChatBabyException("I'm sorry, but I don't know what that means.");
            }
        }
}
