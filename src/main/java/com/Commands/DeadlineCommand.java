package com.Commands;

import com.Nimbus.InvalidArgumentException;
import com.Nimbus.Storage;
import com.Nimbus.Task;
import com.Nimbus.Ui;
import com.Nimbus.Deadline;

import static com.Nimbus.Parser.getDescription;
import static com.Nimbus.Parser.readOption;

import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private final String argument;

    public DeadlineCommand(String a) {
        this.argument = a;
    }


    @Override
    public void execute(Ui ui, Storage storage, ArrayList<Task> tasks) throws InvalidArgumentException {
        Task task = new Deadline(getDescription(argument),
                readOption(argument, "by"));
        storage.writeTaskToFile(task);
        tasks.add(task);
        ui.showAddedTask(task, tasks.size());
    }
}
