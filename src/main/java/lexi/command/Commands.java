package lexi.command;

/**
 * Enum representing the different commands available in the Lexi application.
 * Each command is associated with a description that explains its purpose.
 */
public enum Commands {
    TODO("Adds a todo task."),
    DEADLINE("Adds a task with a deadline."),
    EVENT("Adds an event with a start and end time."),
    MARK("Marks a task as done."),
    UNMARK("Marks a task as not done."),
    DELETE("Deletes a task."),
    LIST("Lists all tasks."),
    FIND("Finds all related tasks"),
    BYE("Exits the application.");

    private final String description;

    /**
     * Constructs a Commands enum with the specified description.
     *
     * @param description A brief description of what the command does.
     */
    Commands(String description) {
        // Assertion to ensure description is not null
        assert description != null : "Description cannot be null";
        this.description = description;
    }

    /**
     * Returns the string representation of the command, including its name and description.
     *
     * @return The string representation of the command.
     */
    @Override
    public String toString() {
        return this.name() + ": " + this.description;
    }

    /**
     * Returns a string that lists all commands and their descriptions.
     * Each command is listed on a new line.
     *
     * @return A string listing all available commands with their descriptions.
     */
    public static String printCommands() {
        StringBuilder commands = new StringBuilder();
        for (Commands command : Commands.values()) {
            commands.append(command).append(System.lineSeparator());
        }
        return commands.toString();
    }
}
