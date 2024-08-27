public abstract class Command {
    private String command;

    public Command(String command){
        this.command = command;
    }
    public Command(){
        command = null;
    }
    protected abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BeeBooExceptions;

    protected abstract boolean isExit();

}
