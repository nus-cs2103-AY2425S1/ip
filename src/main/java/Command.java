public enum Command {
    BYE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE;

    public static Command getCommand(String stringCommand) throws InvalidCommandException {
        try {
            return Command.valueOf(stringCommand.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException(
                String.format(
                        "     %s is not a valid command!\n" +
                                "     To list items, use 'list'.\n" +
                                "     To mark or unmark an item as done, use '<mark/unmark> <item index>'.\n" +
                                "     To add a new item, use '<todo/deadline/event> <task name> <args>'.",
                        stringCommand
                )
            );
        }
    }
}
