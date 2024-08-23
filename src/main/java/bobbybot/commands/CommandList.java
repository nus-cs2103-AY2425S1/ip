package bobbybot.commands;

import bobbybot.Storage;
import bobbybot.TaskList;
import bobbybot.ui.Ui;

public class CommandList extends Command {

    public CommandList(String argument) {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }
}
