import java.io.IOException;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WaterfallException, IOException {
        if (!tasks.checkIndex(index)) {
            throw new WaterfallException("Why are you trying to edit a waterfall task that does not exist?");
        }
        tasks.setDone(index, false);
        storage.updateTask(tasks.getTasks());
        ui.showUnmarkMessage(tasks.getTask(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
