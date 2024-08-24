public abstract class Command {

    private boolean isExit;

    public Command(boolean isExit){
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(Storage storage, TaskList tasks, Ui ui) throws MoiMoiException;

}
