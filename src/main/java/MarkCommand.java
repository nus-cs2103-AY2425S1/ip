public class MarkCommand extends Command {
    int taskListIndex;

    public MarkCommand(int taskListIndex) {
        super(false);
        this.taskListIndex = taskListIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TaskException {
        Task task = taskList.getTaskAtIndex(this.taskListIndex);
        boolean isDone = task.toggleIsDone();
        if (isDone) {
            ui.PixelSays("Nice! I've marked this task as done:",
                    " " + task);
        } else {
            ui.PixelSays("OK, I've marked this task as not done yet:",
                    " " + task);
        }
    }
}