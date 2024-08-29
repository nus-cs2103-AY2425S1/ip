class AddTaskCommand extends Command {
    private Task task;

    AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showAdd(task, tasks.size());
        storage.save(tasks.get());
    }
}