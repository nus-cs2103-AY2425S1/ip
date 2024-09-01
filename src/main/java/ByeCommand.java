public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GalliumException {
        ui.printByeMessage();
        storage.writeFile(ui);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
