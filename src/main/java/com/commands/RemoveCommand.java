package com.commands;

import com.nimbus.Storage;
import com.nimbus.TaskList;
import com.nimbus.Ui;

/**
 * Remove a task.
 */
public class RemoveCommand extends Command {
    private final int index;

    public RemoveCommand(String d) {
        this.index = Integer.parseInt(d) - 1;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        if (storage.removeTaskFromFileByIndex(index, tasks.size())) {
            ui.showRemovedTask(tasks.remove(index), tasks.size());
        }
    }
}
