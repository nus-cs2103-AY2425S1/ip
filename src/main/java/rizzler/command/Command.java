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

    Command() {
        this("");
    }

    Command(String textInput) {
        this.textInput = textInput;
        this.shouldEnd = false;
    }

    /**
     * Executes whatever the command specifies, modifying storage and taskLog accordingly.
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return <code>String[]</code> to be printed in response to execution of this command.
     */
    public abstract String[] execute(Storage storage, TaskLog taskLog);

    /**
     * Outputs whether the command should result in the ending of the chatbot session.
     * @return <code>boolean</code>
     */
    public boolean shouldEnd() {
        return shouldEnd;
    }

    void setShouldEnd(boolean shouldEnd) {
        this.shouldEnd = shouldEnd;
    }

    /**
     * Obtains the <code>String</code> originally used in creation of this command.
     * @return <code>String</code> that was used to create this command.
     */
    public String getTextInput() {
        return textInput;
    }
}
