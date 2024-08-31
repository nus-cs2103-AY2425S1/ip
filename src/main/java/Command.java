@FunctionalInterface
public interface Command {

    public boolean execute(TaskList tasks, Ui ui, StorageManager storageManager);
}
