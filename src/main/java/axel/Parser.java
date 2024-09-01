package axel;

public class Parser {
    public static Command parse(String command) throws AxelException {
        if (command.startsWith("todo")) {
            return new AddCommand(new ToDoTask(command.substring(5).trim()));
        } else if (command.startsWith("deadline")) {
            String[] parts = command.split(" /by ");
            if (parts.length < 2) {
                throw new AxelException("Invalid deadline command format.");
            }
            return new AddCommand(new DeadlineTask(parts[0].substring(9).trim(), parts[1].trim()));
        } else if (command.startsWith("event")) {
            String[] parts = command.split(" /from | /to ");
            if (parts.length < 3) {
                throw new AxelException("Invalid event command format.");
            }
            return new AddCommand(new EventTask(parts[0].substring(6).trim(), parts[1].trim(), parts[2].trim()));
        } else if (command.startsWith("mark")) {
            return new MarkCommand(parseTaskIndex(command));
        } else if (command.startsWith("unmark")) {
            return new UnmarkCommand(parseTaskIndex(command));
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(parseTaskIndex(command));
        } else if (command.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (command.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else {
            throw new UnrecognisedCommandException();
        }
    }
    private static int parseTaskIndex(String command) throws AxelException {
        try {
            return Integer.parseInt(command.substring(command.indexOf(' ') + 1).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new AxelException("Invalid task number.");
        }
    }
}

