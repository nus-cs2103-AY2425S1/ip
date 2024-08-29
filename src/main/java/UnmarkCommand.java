public class UnmarkCommand extends Command{
    int index;
    public UnmarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NayanaException{
        Task task = tasks.markAsNotDone(index);
        storage.writeToFile(tasks.getTasks());
        ui.printUnmarkTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
