package duck.command;

import duck.task.TaskList;
import duck.ui.Ui;
import duck.storage.Storage;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage);
}
