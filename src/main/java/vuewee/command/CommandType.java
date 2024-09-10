package vuewee.command;

import vuewee.parser.IllegalCommandException;

/**
 * Represents the types of commands available in the application.
 */
public enum CommandType {
    BYE(ByeCommand.class),
    LIST(ListCommand.class),
    MARK(MarkCommand.class),
    UNMARK(UnmarkCommand.class),
    FIND(FindCommand.class),
    DELETE(DeleteCommand.class),
    TODO(TodoCommand.class),
    DEADLINE(DeadlineCommand.class),
    EVENT(EventCommand.class),
    SCHEDULE(ScheduleCommand.class);

    private final Class<? extends Command> commandClass;

    /**
     * Constructs a CommandType with the specified command class.
     *
     * @param commandClass the class representing the command
     */
    CommandType(Class<? extends Command> commandClass) {
        this.commandClass = commandClass;
    }

    /**
     * Creates an instance of the command associated with this CommandType.
     *
     * @return the created command instance
     * @throws RuntimeException if the command instance cannot be created
     */
    public Command createCommand() {
        try {
            return commandClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create command instance", e);
        }
    }

    /**
     * Converts a string representation of a command to the corresponding
     * CommandType.
     *
     * @param command the string representation of the command
     * @return the corresponding CommandType
     * @throws IllegalCommandException if the command is unknown
     */
    public static CommandType fromString(String command) {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalCommandException("Unknown command: " + command);
        }
    }

    public static String toString(CommandType commandType) {
        return commandType.name().toLowerCase();
    }
}
