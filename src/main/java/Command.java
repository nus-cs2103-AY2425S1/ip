public abstract class Command {
    protected final CommandType type;

    public Command(CommandType type) {
        this.type = type;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MonoBotException;

    public boolean isExit() {
        return type == CommandType.BYE;
    }
}
