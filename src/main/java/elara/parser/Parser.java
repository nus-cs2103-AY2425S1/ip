import elara.task.DeleteCommand;
import elara.task.InvalidInputException;

public class Parser {
    public static String[] splitInput(String input) {
        return input.split(" ", 2);
    }
    public static Command parse(String input) throws InvalidInputException {
        String[] split = Parser.splitInput(input);

        String command = split[0];
        String arg = split.length > 1 ? split[1] : "";

        return switch (command) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(arg);
            case "unmark" -> new UnmarkCommand(arg);
            case "delete" -> new DeleteCommand(arg);
            case "todo", "deadline", "event" -> new AddCommand(command, arg);
            default -> throw new InvalidInputException("Errrrrrrr... I don't know what you are trying to say...\n" +
                    "Try one of our commands: list mark unmark bye deadline todo event");
        };
    }
}
