public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlisaException {
        String message = taskList.markTask(index);
        ui.showMessage(message);
        storage.syncFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
