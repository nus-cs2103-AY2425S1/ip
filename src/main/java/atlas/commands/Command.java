package atlas.commands;

import atlas.exceptions.AtlasException;
import atlas.storage.Storage;
import atlas.tasks.TaskList;

/**
 * Abstract command class that all other command classes inherit from.
 */
public abstract class Command {
    /**
     * Executes a set of steps according to the command.
     *
     * @param tasks The current list of tasks in the chatbot.
     * @param storage The storage object the chatbot uses to store and load tasks
     * @return String The message returned to be displayed on the chatbot GUI.
     * @throws AtlasException The exception to be thrown in the event of any error.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws AtlasException;
}
