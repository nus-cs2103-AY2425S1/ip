import java.util.ArrayList;

public abstract class Command {
    abstract void execute(ArrayList<Task> tasks, Storage storage);

    abstract boolean continueProcessing();

    public enum IntegerCommands {
        Mark,
        Unmark,
        Delete
    }
}
