package com.commands;

import com.nimbus.InvalidArgumentException;
import com.nimbus.Storage;
import com.nimbus.TaskList;
import com.nimbus.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks)
            throws InvalidArgumentException {
        ui.showGoodbyeMessage();
    }
}
