package cheese.command;

import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;

/**
 * Command to list all tasks
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.say(tasks);
    }
}
