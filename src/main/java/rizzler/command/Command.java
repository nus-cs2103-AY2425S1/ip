package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;

/**
 * Represents abstract commands within the Rizzler.
 * Enables further subclassing for specific commands.
 */
public abstract class Command {
    private final String textInput;
    private boolean shouldEnd;

    /**
     * Constructs a generic command object. Sets <code>textInput</code> to an empty string.
     */
    Command() {
        this("");
    }

    /**
     * Constructs a generic command object. Sets <code>textInput</code> to the given input.
     *
     * @param textInput String given to be stored as the <code>textInput</code>.
     */
    Command(String textInput) {
        this.textInput = textInput;
        this.shouldEnd = false;
    }

    /**
     * Executes whatever the command specifies, modifying storage and taskLog accordingly.
     *
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return <code>String[]</code> to be printed in response to execution of this command.
     */
    public abstract String[] execute(Storage storage, TaskLog taskLog);

    /**
     * Outputs whether the command should result in the ending of the chatbot session.
     */
    public boolean shouldEnd() {
        return shouldEnd;
    }

    /**
     * Updates the <code>shouldEnd</code> variable with a new boolean.
     */
    void setShouldEnd(boolean shouldEnd) {
        this.shouldEnd = shouldEnd;
    }

    /**
     * Returns the <code>textInput</code> originally used in creation of this command.
     *
     * @return <code>textInput</code> that was used to create this command.
     */
    public String getTextInput() {
        return textInput;
    }
}
