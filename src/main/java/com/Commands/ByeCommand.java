package com.Commands;

import com.Nimbus.InvalidArgumentException;
import com.Nimbus.Storage;
import com.Nimbus.Task;
import com.Nimbus.Ui;

import java.util.ArrayList;

public class ByeCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, ArrayList<Task> tasks) throws InvalidArgumentException {
        ui.showGoodbyeMessage();
    }
}
