package elara.command;

import elara.storage.Storage;
import elara.task.TaskList;
import elara.ui.Ui;

public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage);
}