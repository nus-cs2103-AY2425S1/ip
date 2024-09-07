package rizzler;

/**
 * Represents the parsing function of Rizzler.
 */
class Parser {

    /**
     * Parses the raw input String and creates a
     * <code>Command</code> corresponding to the
     * command type featured.
     *
     * @param fullCommand String that represents the raw command input.
     * @return Command that corresponds to the raw input given.
     * @throws RizzlerException If the command is not understood by the Parser.
     */
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
        case "find":
            return new FindCommand(parsedCommand);
        case "bye":
            return new ExitCommand();
        case "sort":
            return new SortCommand();
        default:
            throw new RizzlerException("I've gyatt no idea what you're saying!");
        }
    }
}
