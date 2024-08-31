package bean.command;

/**
 * Represents a command input by the user, including its type and optional details.
 */
public class Command {
    private String type;
    private String details;

    /**
     * Constructs a Command with the specified type.
     *
     * @param type The type of the command (e.g., "list", "mark", "todo").
     */
    public Command(String type) {
        this.type = type;
    }

    /**
     * Constructs a Command with the specified type and details.
     *
     * @param type    The type of the command (e.g., "list", "mark", "todo").
     * @param details Additional details associated with the command (e.g., task description or index).
     */
    public Command(String type, String details) {
        this.type = type;
        this.details = details;
    }

    /**
     * Retrieves the type of the command.
     *
     * @return The type of the command.
     */
    public String getType() {
        return type;
    }

    /**
     * Retrieves the details of the command.
     *
     * @return The details of the command, or null if none were provided.
     */
    public String getDetails() {
        return details;
    }
}