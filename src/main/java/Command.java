public interface Command {
    boolean execute(Storage storage, TaskList master, UI ui);
}
