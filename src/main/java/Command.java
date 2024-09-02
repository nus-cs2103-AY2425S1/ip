public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException;
    boolean isExit();
}