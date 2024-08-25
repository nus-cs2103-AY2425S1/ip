class Parser {
    Command parse(String fullCommand) throws RizzlerException {
        String[] parsedCommand = fullCommand.split(" ");
        if (parsedCommand.length == 0) {
            throw new RizzlerException("I've gyatt no idea what you're saying!");
        }
        switch (parsedCommand[0]) {
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(parsedCommand);
        case "list":
            return new ListCommand();
        case "delete":
            return new DeleteCommand(parsedCommand);
        case "mark":
            return new MarkCommand(parsedCommand);
        case "unmark":
            return new UnmarkCommand(parsedCommand);
        case "bye":
            return new ExitCommand();
        default:
            throw new RizzlerException("I've gyatt no idea what you're saying!");
        }
    }
}
