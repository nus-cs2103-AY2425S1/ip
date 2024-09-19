package com.commands;

import com.nimbus.Storage;
import com.nimbus.TaskList;
import com.nimbus.Ui;

/**
 * Mark a task to be done.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String d) {
        this.index = Integer.parseInt(d) - 1;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        tasks.mark(index);
        ui.showDoneTask(tasks.get(index));
        storage.writeTasksToFile(tasks);
    }
}
