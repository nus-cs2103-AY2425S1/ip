public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TaskException {
        Task task = taskList.getTaskAtIndex(taskIndex);
        taskList.deleteTask(task);
        ui.PixelSays("Noted. I've removed this task:", "  " + task,
                "Now you have " + taskList.size() + " tasks in the list.");
    }
}
