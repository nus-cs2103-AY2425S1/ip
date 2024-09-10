package commands;

import cook.Storage;
import cook.TaskList;
import cook.Ui;

/**
 * FindCommand class to process find commands.
 */
public class FindCommand extends Command {
    protected String keyword;
    /**
     * Constructor for FindCommand class.
     */
    public FindCommand(String keyword) {
        super("find");
        this.keyword = keyword;
    }

    /**
     * Finds task containing the keyword and lists them.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = tasks.findTask(this.keyword);
        if (!foundTasks.isEmpty()) {
            ui.say(foundTasks.toString());
        } else {
            ui.say("No tasks have been found containing \"" + this.keyword + "\".");
        }
    }
}
