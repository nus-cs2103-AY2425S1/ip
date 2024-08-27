public abstract class Command {
    public void execute(Ui ui, TaskList tasks) throws GrayException { }
    public boolean isExit() { return false; }
}
