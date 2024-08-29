public class DeleteCommand extends Command {
    private int taskListIndex;

    public DeleteCommand(String input) throws TaskException {
        super(false);
        if (!input.matches("-?(0|[1-9]\\d*)")) {
            throw new TaskException(String.format("%s need to be a number", input));
        }
        this.taskListIndex = Integer.parseInt(input) - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TaskException {
        if (this.taskListIndex < 0 || this.taskListIndex >= taskList.size()) {
            throw new TaskException(String
                    .format("%d is out of range of task list, index should be range between %d and %d inclusive",
                            this.taskListIndex + 1, 1, taskList.size()));
        }
        Task task = taskList.getTaskAtIndex(taskListIndex);
        taskList.deleteTask(task);
        ui.PixelSays("Noted. I've removed this task:", "  " + task,
                "Now you have " + taskList.size() + " tasks in the list.");
    }
}
