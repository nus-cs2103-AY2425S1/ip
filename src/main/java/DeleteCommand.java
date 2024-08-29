class DeleteCommand extends Command {
    private int index;

    DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TudeeException{
        if (index > tasks.size()) {
            throw new TudeeException("You do not have that many tasks!");
        }
        Task task = tasks.delete(index - 1);
        ui.showDelete(task, tasks.size());
        storage.save(tasks.get());
    }
}