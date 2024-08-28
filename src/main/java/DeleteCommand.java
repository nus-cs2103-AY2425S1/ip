public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AstraException {
        Task t = tasks.delete(index);
        storage.save(tasks);
        String msg = " Noted. I've removed this task: \n  " + t + "\n";
        ui.display(msg);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
