public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DarkpoolException {
        ui.goodbye();
        storage.saveData(taskList);
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
