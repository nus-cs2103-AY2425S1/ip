public abstract class MarkCommand extends Command {
    protected int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NetherException {
        if (taskNumber > tasks.getSize() || taskNumber < 1) {
            throw new NetherException("invalid task number!");
        }

        Task taskToMark = tasks.getTask(taskNumber - 1);
        markTask(taskToMark);

        ui.printHorizontalLine();
        ui.printMarkMessage(taskToMark, getMarkMessage());
        storage.saveTasks(tasks.getTasks());
    }

    public abstract void markTask(Task taskToMark);

    public abstract String getMarkMessage();
}
