package bobbybot.commands;

import bobbybot.Storage;
import bobbybot.TaskList;
import bobbybot.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class CommandList extends Command {

    public CommandList(String argument) {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }
}
