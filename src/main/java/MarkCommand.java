public class MarkCommand extends Command{
    int index;
    public MarkCommand( int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NayanaException{
        Task task = tasks.markAsDone(index);
        storage.writeToFile(tasks.getTasks());
        ui.printMarkTask(task);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
