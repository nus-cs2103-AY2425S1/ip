public class Parser {
    public static Command parse(String input) throws WolfieException {
        String[] parts = input.split(" ", 2); // Split the input into two parts
        String commandWord = parts[0]; // First part is the command word
        String arguments = parts.length > 1 ? parts[1] : ""; // Second part is the arguments

        return switch (commandWord) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(arguments);
            case "unmark" -> new UnmarkCommand(arguments);
            case "todo" -> new AddTodoCommand(arguments);
            case "deadline" -> new AddDeadlineCommand(arguments);
            case "event" -> new AddEventCommand(arguments);
            case "delete" -> new DeleteCommand(arguments);
            case "on" -> new OnCommand(arguments);
            default -> throw new WolfieException("I'm sorry Dean's Lister, but I don't know what that means :-(");
        };
    }
}