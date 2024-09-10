package com.commands;

import static com.nimbus.Parser.getDescription;
import static com.nimbus.Parser.readOption;

import com.exceptions.InvalidArgumentException;
import com.nimbus.Deadline;
import com.nimbus.Storage;
import com.nimbus.Task;
import com.nimbus.TaskList;
import com.nimbus.Ui;

/**
 * Add a new deadline to chatbot
 */
public class DeadlineCommand extends Command {
    private final String argument;

    public DeadlineCommand(String a) {
        this.argument = a;
    }


    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks)
            throws InvalidArgumentException {
        Task task = new Deadline(getDescription(argument),
                readOption(argument, "by"));
        if (!tasks.add(task)) {
            ui.showDuplicateTask(tasks.findAllWith(task.getDescription()));
            return;
        }
        storage.writeTaskToFile(task);
        ui.showAddedTask(task, tasks.size());
    }
}
