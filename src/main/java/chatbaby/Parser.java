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
    public static Command parse(String curCommand) throws ChatBabyException {
        String[] parts = curCommand.split(" ", 2);
        String commandWord = parts[0];

        return switch (commandWord) {
        case "bye" -> new ExitCommand("bye");
        case "list" -> new ListCommand("list");
        case "todo" -> new AddCommand(curCommand, TaskType.TODO);
        case "deadline" -> new AddCommand(curCommand, TaskType.DEADLINE);
        case "event" -> new AddCommand(curCommand, TaskType.EVENT);
        case "delete" -> new DeleteCommand(curCommand);
        case "mark" -> new MarkCommand(curCommand);
        case "unmark" -> new UnmarkCommand(curCommand);
        case "listEndingOn" -> new ListOnDateCommand(curCommand);
        case "find" -> new FindCommand(curCommand);
        default -> throw new ChatBabyException("I'm sorry, but I don't know what that means.");
        };
    }
}
