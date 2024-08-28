public class Parser {

    public static Command parse(String fullCommand) throws HueException {
        if (fullCommand.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("mark")) {
            return new MarkCommand(fullCommand);
        } else if (fullCommand.startsWith("unmark")) {
            return new UnmarkCommand(fullCommand);
        } else if (fullCommand.startsWith("todo")) {
            return new AddTodoCommand(fullCommand);
        } else if (fullCommand.startsWith("deadline")) {
            return new AddDeadlineCommand(fullCommand);
        } else if (fullCommand.startsWith("event")) {
            return new AddEventCommand(fullCommand);
        } else if (fullCommand.startsWith("delete")) {
            return new DeleteCommand(fullCommand);
        } else {
            throw new HueException("I'm sorry, but I don't know what that means. Womp Womp :(");
        }
    }
}
