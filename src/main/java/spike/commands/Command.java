package spike.commands;

import spike.exceptions.SpikeException;
import spike.storage.TaskList;
import spike.storage.Storage;
import spike.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SpikeException;
    public abstract boolean isExit();
}


