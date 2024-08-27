package com.commands;

import com.nimbus.*;

import static com.nimbus.Parser.getDescription;
import static com.nimbus.Parser.readOption;

public class EventCommand extends Command {
    private final String argument;

    public EventCommand(String a) {
        this.argument = a;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks)
            throws InvalidArgumentException {
        Event task = new Event(getDescription(argument),
                readOption(argument, "from"),
                readOption(argument, "to"));

        storage.writeTaskToFile(task);
        tasks.add(task);
        ui.showAddedTask(task, tasks.size());
    }
}
