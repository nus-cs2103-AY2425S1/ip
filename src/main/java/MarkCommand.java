class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LoloException {
        Task task = tasks.markTaskAsDone(taskNumber);
        ui.showMarkTaskAsDone(task);
        storage.save(tasks.getTasks());
    }
}
