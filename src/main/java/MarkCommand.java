class MarkCommand extends Command {
    private int index;

    MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        ui.showMark(task);
        storage.save(tasks.get());
    }
}