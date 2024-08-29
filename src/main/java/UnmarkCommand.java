class UnmarkCommand extends Command {
    private int index;

    UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        Task task = tasks.get(index - 1);
        task.markAsNotDone();
        ui.showUnMark(task);
        storage.save(tasks.get());
    }
}