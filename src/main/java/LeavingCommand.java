public class LeavingCommand extends Command {

    public LeavingCommand(String command) {
        super(command);
    }

    public void execute(Storage storage, Ui ui) {
        storage.storeInFile();
        ui.printLeavingMessage();
    }
}
