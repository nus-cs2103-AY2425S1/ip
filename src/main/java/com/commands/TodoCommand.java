package com.commands;

import com.nimbus.Storage;
import com.nimbus.TaskList;
import com.nimbus.Todo;
import com.nimbus.Ui;

/**
 * Add a new todo task to chatbot
 */
public class TodoCommand extends Command {
    private final String argument;

    public TodoCommand(String d) {
        this.argument = d;
    }


    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        Todo task = new Todo(argument);
        if (!tasks.add(task)) {
            ui.showDuplicateTask(tasks.findAllWith(argument));
            return;
        }
        storage.writeTaskToFile(task);
        ui.showAddedTask(task, tasks.size());
    }
}
