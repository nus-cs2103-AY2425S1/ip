package com.commands;

import com.nimbus.*;

public class ByeCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws InvalidArgumentException {
        ui.showGoodbyeMessage();
    }
}
