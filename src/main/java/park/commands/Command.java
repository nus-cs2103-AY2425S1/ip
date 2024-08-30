package main.java.park.commands;

import main.java.park.exceptions.ParkException;
import main.java.park.storage.Storage;
import main.java.park.storage.TaskList;
import main.java.park.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ParkException;

    public abstract boolean isExit();
}
