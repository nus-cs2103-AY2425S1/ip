package com.Commands;

import com.Nimbus.*;

import java.util.ArrayList;

public class ByeCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws InvalidArgumentException {
        ui.showGoodbyeMessage();
    }
}
