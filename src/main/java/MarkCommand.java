public class MarkCommand extends Command {
    private final int taskToMark;

    public MarkCommand(int taskToMark) {
        this.taskToMark = taskToMark;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.markTaskAsDone(taskToMark);
    }
}

