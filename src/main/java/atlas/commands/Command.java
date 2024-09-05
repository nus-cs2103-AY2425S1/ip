package atlas.commands;

import atlas.exceptions.AtlasException;
import atlas.storage.Storage;
import atlas.tasks.TaskList;

/**
 * Abstract command class that all other command classes inherit from.
 */
public abstract class Command {
    private boolean isExit = false;

    /**
     * @param tasks The current list of tasks in the chatbot.
     * @param storage The storage object the chatbot uses to store and load tasks
     * @throws AtlasException The exception to be thrown in the event of any error.
     *
     * @return String The message returned to be displayed on the chatbot GUI.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws AtlasException;

    /**
     * Shows the exit status of the chatbot.
     *
     * @return boolean The exit status of the chatbot.
     */
    public boolean isExit() {
        return this.isExit;
    }

    public void setIsExit() {
        this.isExit = true;
    }
}
