package atlas.commands;

import atlas.storage.Storage;
import atlas.tasks.TaskList;

/**
 * Exits the chatbot when this class is instantiated.
 */
public class ExitCommand extends Command {
    /**
     * @param tasks The current list of tasks in the chatbot.
     * @param storage The storage object the chatbot uses to store and load tasks
     *
     * @return String The message returned to be displayed on the chatbot GUI.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        super.setIsExit();
        return "Bye. Hope to see you again soon!";
    }
}
