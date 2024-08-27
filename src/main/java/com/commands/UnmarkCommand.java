package com.commands;

import com.nimbus.Storage;
import com.nimbus.TaskList;
import com.nimbus.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(String d) {
        this.index = Integer.parseInt(d) - 1;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        tasks.unmark(index);
        ui.showNotDoneTask(tasks.get(index));
        storage.writeTasksToFile(tasks);
    }
}
