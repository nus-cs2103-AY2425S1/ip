package Commands;

import java.util.ArrayList;
import Applemazer.*;
import Tasks.*;

public abstract class Command {
    public abstract void execute(ArrayList<Task> tasks, Storage storage);

    public abstract boolean continueProcessing();

    public enum IntegerCommands {
        Mark,
        Unmark,
        Delete
    }
}
