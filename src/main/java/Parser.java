public class Parser {
    public Command parse(String fullCommand) throws MichaelScottException{
        String[] parts = fullCommand.split("", 2);
        String command = parts[0];
        String args = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "list" : return new ListCommand();
            case "bye" : return new ExitCommand();
            case"mark" : return new MarkCommand(args);
            case "unmark" : return new UnmarkCommand(args);
            case "delete": return new DeleteCommand(args);
            case "todo" : return new TodoCommand(args);
            case "deadline" : return new DeadlineCommand(args);
            case "event" : return new EventCommand(args);
            default -> {
                throw new MichaelScottException("I don't understand what you mean to say!");
            }
        }
    }
}
