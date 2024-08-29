abstract class Command {
    public void execute(TaskList tasks, Storage storage) {}
    public boolean isExit() { return false; }
}
