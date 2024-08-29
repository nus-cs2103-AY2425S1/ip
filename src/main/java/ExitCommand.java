public class ExitCommand extends Command {

    @Override
    protected void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        ui.printGoodBye();
        storage.toFile(taskList);
    }

    @Override
    protected boolean isExit() {
        return true;
    }
}
