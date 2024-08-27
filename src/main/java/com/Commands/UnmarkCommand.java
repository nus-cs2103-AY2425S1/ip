package com.Commands;

import com.Nimbus.Storage;
import com.Nimbus.Task;
import com.Nimbus.Ui;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(String d) {
        this.index = Integer.parseInt(d) - 1;
    }

    @Override
    public void execute(Ui ui, Storage storage, ArrayList<Task> tasks) {
        tasks.get(index).setNotDone();
        ui.showNotDoneTask(tasks.get(index));
        storage.writeTasksToFile(tasks);
    }
}
