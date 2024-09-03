public interface Command {
    void execute(TaskList tasks, TaskFileManager manager, UserInterface ui);
}
