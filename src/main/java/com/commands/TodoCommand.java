package com.commands;

import com.nimbus.Storage;
import com.nimbus.TaskList;
import com.nimbus.Ui;
import com.nimbus.Todo;

public class TodoCommand extends Command {
    private final String argument;

    public TodoCommand(String d) {
        this.argument = d;
    }


    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        Todo task = new Todo(argument);
        storage.writeTaskToFile(task);
        tasks.add(task);
        ui.showAddedTask(task, tasks.size());
    }
}
