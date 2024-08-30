package atlas.commands;

import atlas.storage.Storage;
import atlas.tasks.TaskList;
import atlas.ui.Ui;

/**
 * Finds tasks in the task list that match a pattern when this class is instantiated.
 */
public class FindCommand extends Command {
    private final String pattern;
    public FindCommand(String pattern) {
        this.pattern = pattern;
    }

    /**
     * @param tasks The current list of tasks in the chatbot.
     * @param ui The current ui object the chatbot uses to display messages
     * @param storage The storage object the chatbot uses to store and load tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String filteredTasksOutput = tasks.find(this.pattern);
        ui.print(String.format("Here are the matching tasks in your list:\n%s", filteredTasksOutput));
    }
}
