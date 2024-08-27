package com.commands;

import com.nimbus.Storage;
import com.nimbus.TaskList;
import com.nimbus.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ui.showAllTasks(tasks);
    }
}