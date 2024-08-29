public class Parser {

    public static Command parse(String command) throws AlisaException {
        String[] commandArray = command.split(" ", 2);
        switch (commandArray[0]) {
            case "mark":
                return new MarkCommand(Integer.parseInt(commandArray[1]));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(commandArray[1]));
            case "todo":
                return new AddCommand(new Todo(commandArray[1]));
            case "deadline":
                String[] deadlineDescription = commandArray[1].split(" /by ");
                return new AddCommand(new Deadline(deadlineDescription[0], deadlineDescription[1]));
            case "event":
                String[] eventDescription = commandArray[1].split(" /from | /to ");
                return new AddCommand(new Event(eventDescription[0], eventDescription[1], eventDescription[2]));
            case "delete":
                return new DeleteCommand(Integer.parseInt(commandArray[1]));
            case "list":
                return new ListCommand();
            default:
                throw new AlisaException("Sorry, I didn't quite catch that. Put in a command that I understand");
        }
    }
}
