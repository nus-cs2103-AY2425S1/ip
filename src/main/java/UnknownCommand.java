public class UnknownCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.unknownCommand();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
