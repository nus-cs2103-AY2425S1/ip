package cheese.command;

import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;

/**
 * Command to list all tasks
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.say(tasks);
    }
}
