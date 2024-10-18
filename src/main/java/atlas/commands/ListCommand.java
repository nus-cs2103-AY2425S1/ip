package atlas.commands;

import atlas.storage.Storage;
import atlas.tasks.TaskList;

/**
 * Lists all tasks in the task list when this class is instantiated.
 */
public class ListCommand extends Command {
    /**
     * Lists all tasks in the task list.
     *
     * @param tasks The current list of tasks in the chatbot.
     * @param storage The storage object the chatbot uses to store and load tasks
     * @return String The message returned to be displayed on the chatbot GUI.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            return "No items have been added to the task list.";
        }

        return tasks.listAllTasks();
    }
}
