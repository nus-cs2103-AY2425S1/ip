package krona.command;

import krona.task.TaskList;
import krona.ui.Ui;
import krona.storage.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}