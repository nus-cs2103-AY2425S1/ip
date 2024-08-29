package features.command;

/**
 * Represents a descriptor for a command, including its syntax and description.
 */
public class CommandDescriptor {
    public String syntax;
    public String description;

    /**
     * Constructs a CommandDescriptor with the specified syntax and description.
     *
     * @param syntax the syntax of the command
     * @param description a brief description of the command's functionality
     */
    public CommandDescriptor(String syntax, String description) {
        this.syntax = syntax;
        this.description = description;
    }
}
