public class DeleteCommand extends Command{
    int index;
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NayanaException{
        Task task = tasks.delete(index);
        storage.writeToFile(tasks.getTasks());
        ui.printDeleteTask(task,tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
