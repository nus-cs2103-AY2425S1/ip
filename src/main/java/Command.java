abstract class Command {
    private boolean isExit = false;
    public Command(boolean isExit) {
        this.isExit = isExit;
    }
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws HienException;
    public boolean isExit() {
        return isExit;
    }

}