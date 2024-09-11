package bob.command;

import bob.exception.BobException;
import bob.storage.Storage;
import bob.tasks.Task;
import bob.ui.Ui;

import java.util.ArrayList;

public abstract class Command {
    public abstract String execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException;
}

