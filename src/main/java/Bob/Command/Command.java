package bob.Command;

import bob.Exception.BobException;
import bob.Storage.Storage;
import bob.Tasks.Task;
import bob.Ui.Ui;

import java.util.ArrayList;

public abstract class Command {
    public abstract String execute(ArrayList<Task> tasks, Storage storage, Ui ui) throws BobException;
}

