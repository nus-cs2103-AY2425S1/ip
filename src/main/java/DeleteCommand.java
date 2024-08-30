public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int i) {
        this.index = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParkException {
        Task t = tasks.get(index);
        storage.delete(t);
        ui.showToUser("Noted. I've removed this task:" + t);
        ui.showToUser("Now you have " + tasks.size() + " tasks in the list.");
        tasks.delete(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
