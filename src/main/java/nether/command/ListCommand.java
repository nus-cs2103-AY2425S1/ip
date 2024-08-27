package nether.command;

import nether.Ui;
import nether.storage.Storage;
import nether.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }
}
