public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlisaException {
        String message = taskList.deleteTask(index);
        ui.showMessage(message);
        storage.syncFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
