public class Parser {

    public static Command parse(String userInput) throws ChatBuddyException {
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parts[1]);
            case "unmark":
                return new UnmarkCommand(parts[1]);
            case "todo":
                return new AddCommand(parts[1], "T");
            case "deadline":
                return new AddCommand(parts[1], "D");
            case "event":
                return new AddCommand(parts[1], "E");
            case "delete":
                return new DeleteCommand(parts[1]);
            case "bye":
                return new ExitCommand();
            default:
                throw new ChatBuddyException("Sorry, I don't understand that command.");
        }
    }
}
