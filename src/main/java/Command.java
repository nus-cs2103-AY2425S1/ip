public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws TaskException;

    public boolean isExit() {
        return this.isExit;
    }
}
