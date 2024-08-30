public class UnmarkCommand extends Command {

    private final int index;

    public UnmarkCommand(int i) {
        this.index = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParkException {
        Task t = tasks.get(index);
        String oldLine = t.encode();
        t.unmark();
        String newLine = t.encode();
        storage.modify(oldLine, newLine);
        ui.showToUser("OK, I've marked this task as not done yet:" + t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
