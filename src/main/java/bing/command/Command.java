package bing.command;

import bing.storage.Storage;
import bing.task.TaskList;
import bing.ui.Ui;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage);

    boolean isExit();
}
