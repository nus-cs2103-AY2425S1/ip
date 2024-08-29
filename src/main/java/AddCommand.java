class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LoloException {
        tasks.addTask(task);
        ui.showAddedTask(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
