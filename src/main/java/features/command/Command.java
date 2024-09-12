package features.command;

/**
 * Represents a command with a name and provides methods to access and manipulate it.
 */
public class Command {
    private String name;

    /**
     * Constructs a Command with the specified name.
     *
     * @param name the name of the command
     */
    public Command(String name) {
        assert name != null : "Command name must not be null.";
        this.name = name;
    }

    /**
     * Returns the name of the command.
     *
     * @return the name of the command
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the command.
     *
     * @param name the new name for the command
     */
    public void setName(String name) {
        assert name != null : "Command name must not be null.";
        this.name = name;
    }

    /**
     * Checks if the command is an exit command.
     * This method assumes that the exit command is "bye".
     *
     * @return true if the command is "bye", false otherwise
     */
    public boolean isExitCommand() {
        return "bye".equals(name); // Use .equals() for string comparison
    }
}
