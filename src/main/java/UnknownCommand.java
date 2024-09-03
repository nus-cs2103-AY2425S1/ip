public class UnknownCommand extends Command {

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.unknownCommand();
    }
}
