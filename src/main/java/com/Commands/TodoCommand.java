package com.Commands;

import com.Nimbus.*;

import java.util.ArrayList;

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
