package com.commands;

import static com.nimbus.Parser.getDescription;
import static com.nimbus.Parser.readOption;

import com.nimbus.Event;
import com.exceptions.InvalidArgumentException;
import com.nimbus.Storage;
import com.nimbus.Task;
import com.nimbus.TaskList;
import com.nimbus.Ui;

/**
 * Add a new event to chatbot
 */
public class EventCommand extends Command {
    private final String argument;

    public EventCommand(String a) {
        this.argument = a;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks)
            throws InvalidArgumentException {
        Task task = new Event(getDescription(argument),
                readOption(argument, "from"),
                readOption(argument, "to"));

        storage.writeTaskToFile(task);
        tasks.add(task);
        ui.showAddedTask(task, tasks.size());
    }
}
