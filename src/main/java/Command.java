abstract class Command {
    StringBuilder output = new StringBuilder();
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
    public abstract boolean isExit();
}
