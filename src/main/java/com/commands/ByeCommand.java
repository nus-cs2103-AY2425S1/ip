package com.commands;

import com.exceptions.InvalidArgumentException;
import com.nimbus.Storage;
import com.nimbus.TaskList;
import com.nimbus.Ui;

/**
 * ByeCommand will exit the program
 */
public class ByeCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks)
            throws InvalidArgumentException {
        ui.showGoodbyeMessage();
    }
}
