public interface Command {

    String execute(TaskList tasks) throws MichaelScottException;
    default boolean isExit() {
        return false;
    }
}
