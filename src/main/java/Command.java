public abstract class Command {
    private String command;
    private boolean isExit;
    public Command(String command) {
        this.command = command;
        this.isExit = false;
    }

    public abstract void execute(Task task, Ui ui, Storage storage);

    public abstract boolean isExit();
}
