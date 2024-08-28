package commands;

import java.util.ArrayList;
import applemazer.Storage;
import tasks.Task;

public abstract class Command {
    public abstract void execute(ArrayList<Task> tasks, Storage storage);

    public abstract boolean continueProcessing();

    public enum IntegerCommands {
        Mark,
        Unmark,
        Delete
    }
}
