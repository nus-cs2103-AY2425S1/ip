public class ByeCommand extends Command {

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.bye();
    }
}
