public class Parser {
    public static Command parse(String userInput) throws CharlotteException {
        String[] inputParts = userInput.split(" ", 2);
        String command = inputParts[0];

        switch (command) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(Integer.parseInt(inputParts[1]));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(inputParts[1]));
            case "delete":
                return new DeleteCommand(Integer.parseInt(inputParts[1]));
            case "todo":
                return new AddCommand(new ToDo(inputParts[1]));
            case "deadline":
                String[] deadlineParts = inputParts[1].split(" /by ");
                return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1]));
            case "event":
                String[] eventParts = inputParts[1].split(" /from | /to ");
                return new AddCommand(new Event(eventParts[0], eventParts[1], eventParts[2]));
            case "bye":
                return new ExitCommand();
            default:
                throw new CharlotteException("I'm sorry, I don't know what that means :( Please try again!");
        }
    }
}
