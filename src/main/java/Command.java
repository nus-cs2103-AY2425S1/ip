public interface Command {

    String execute(TaskList tasks, Ui ui) throws MichaelScottException;
    default boolean isExit() {
        return false;
    }
}
