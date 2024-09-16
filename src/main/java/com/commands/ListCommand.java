package com.commands;

import com.nimbus.Storage;
import com.nimbus.TaskList;
import com.nimbus.Ui;

/**
 * List all task stored by chatbot.
 */
public class ListCommand extends Command {

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ui.showAllTasks(tasks);
    }
}
