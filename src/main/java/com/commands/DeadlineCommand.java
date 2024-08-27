package com.commands;

import com.nimbus.*;

import static com.nimbus.Parser.getDescription;
import static com.nimbus.Parser.readOption;

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
        storage.writeTaskToFile(task);
        tasks.add(task);
        ui.showAddedTask(task, tasks.size());
    }
}
