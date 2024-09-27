package alexer.command;

import alexer.ui.Response;

/**
 * Represents a command that can be processed by the chatbot
 *
 * @author sayomaki
 */
public abstract class Command {
    private final String name;

    /**
     * Creates a new command with the given name.
     * Used by subclasses as this class is abstract.
     *
     * @param name command name
     */
    public Command(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the command as a string
     *
     * @return string name of command
     */
    public String getName() {
        return name;
    }

    /**
     * Executes the command with the given arguments.
     * This method is abstract and needs to be overridden by the
     * implementation in the command subclass.
     *
     * @param arguments string array of arguments to be provided
     * @return the response for the command
     */
    public abstract Response run(String... arguments);
}
