package commands;

import cook.Storage;
import cook.TaskList;

/**
 * FindCommand class to process find commands.
 */
public class FindCommand extends Command {
    protected String keyword;

    /**
     * Constructs FindCommand object.
     *
     * @param keyword String to find in task's description.
     */
    public FindCommand(String keyword) {
        super("find");
        this.keyword = keyword;
    }

    /**
     * Finds task containing the keyword and lists them.
     *
     * @param tasks List of Task objects.
     * @param storage Class to save and load tasks on the local drive.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList foundTasks = tasks.findTask(this.keyword);
        if (!foundTasks.isEmpty()) {
            return foundTasks.toString();
        } else {
            return "No tasks have been found containing \"" + this.keyword + "\".";
        }
    }
}
