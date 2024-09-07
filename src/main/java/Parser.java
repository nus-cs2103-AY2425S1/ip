public class Parser {

    public static Command parse(String input) throws LunaBotException {
        String[] fullCommand = input.split(" ");
        String command = fullCommand[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark" :
            return new MarkCommand(input);
        case "unmark":
            return new UnmarkCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "todo":
            return new AddToDoCommand(input);
        case "deadline":
            return new AddDeadlineCommand(input);
        case "event":
            return new AddEventCommand(input);
        default:
            throw new LunaBotException(" Invalid command");
        }
    }
}
