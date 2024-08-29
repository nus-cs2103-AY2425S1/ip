class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LoloException {
        Task task = tasks.markTaskAsNotDone(taskNumber);
        ui.showMarkTaskAsNotDone(task);
        storage.save(tasks.getTasks());
    }
}