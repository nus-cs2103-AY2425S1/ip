public class MarkCommand extends Command {

    private final int index;

    public MarkCommand(int i) {
        this.index = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ParkException {
        Task t = tasks.get(index);
        String oldLine = t.encode();
        t.mark();
        String newLine = t.encode();
        storage.modify(oldLine, newLine);
        ui.showToUser("OK, I've marked this task as done:" + t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
