public class Parser {

    public static Command parse(String fullCommand) throws RoseException {
        String[] input = fullCommand.split(" ", 2);
        String command = input[0].toLowerCase();
        String message = (input.length > 1) ? input[1] : "";

        try {
            switch (command) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(Integer.valueOf(message));
            case "unmark":
                return new UnmarkCommand(Integer.valueOf(message));
            case "todo":
                if (message.isEmpty()) {
                    throw new RoseException("You need to provide details for the TODO task.");
                }
                return new AddCommand(TaskType.TODO, message);
            case "event":
                if (message.isEmpty()) {
                    throw new RoseException("You need to provide details for the EVENT task.");
                }
                return new AddCommand(TaskType.EVENT, message);
            case "deadline":
                if (message.isEmpty()) {
                    throw new RoseException("You need to provide details for the DEADLINE task.");
                }
                return new AddCommand(TaskType.DEADLINE, message);
            case "delete":
                return new DeleteCommand(Integer.valueOf(message));
            case "bye":
                return new ExitCommand();
            default:
                throw new RoseException("OOPS!!! I'm sorry, but I don't know that command :-(");
            }
        } catch (NumberFormatException e) {
            throw new RoseException("OOPS!!! You should provide a number of the task index.");
        }
    }
}

