package atlas.commands;

import atlas.storage.Storage;
import atlas.tasks.TaskList;

/**
 * Finds tasks in the task list that match a pattern when this class is instantiated.
 */
public class FindCommand extends Command {
    private final String pattern;
    public FindCommand(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Finds all tasks that match the pattern.
     *
     * @param tasks The current list of tasks in the chatbot.
     * @param storage The storage object the chatbot uses to store and load tasks
     * @return String The message returned to be displayed on the chatbot GUI.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String filteredTasksOutput = tasks.find(this.pattern);
        return String.format("Here are the matching tasks in your list:\n%s", filteredTasksOutput);
    }
}
