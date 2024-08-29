public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws AiException;
    public abstract boolean isExit();
}
