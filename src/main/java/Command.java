public abstract class Command {
    private boolean isQuit = false;
    public void quitBot() {
        this.isQuit = true;
    }
    public boolean getIsQuit() {
        return this.isQuit;
    }
    public abstract void execute(TaskList tasks,Ui ui, Storage storage);
}
