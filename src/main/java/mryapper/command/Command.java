package mryapper.command;

import mryapper.storagemanager.StorageManager;
import mryapper.task.TaskList;
import mryapper.ui.Ui;

@FunctionalInterface
public interface Command {

    public String execute(TaskList tasks, Ui ui, StorageManager storageManager);
}
