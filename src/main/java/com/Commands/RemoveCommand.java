package com.Commands;

import com.Nimbus.Storage;
import com.Nimbus.Task;
import com.Nimbus.TaskList;
import com.Nimbus.Ui;

import java.util.ArrayList;

public class RemoveCommand extends Command {
    private final int index;

    public RemoveCommand(String d) {
        this.index = Integer.parseInt(d) - 1;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        if (storage.removeTaskFromFileByIndex(index, tasks.size()))
            ui.showRemovedTask(tasks.remove(index), tasks.size());
    }
}
