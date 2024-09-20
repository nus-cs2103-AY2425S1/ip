package commands;

import task.TaskList;
import ui.Ui;
import storage.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }
}
