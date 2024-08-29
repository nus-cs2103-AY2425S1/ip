public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlisaException {
        String message = taskList.getList();
        ui.showMessage(message);
        storage.syncFile(taskList);
    }
}
