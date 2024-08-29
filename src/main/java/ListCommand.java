public class ListCommand extends Command {

    public ListCommand(String command) {
        super(command);
    }
    public void execute(Storage storage, Ui ui) {
        ui.returnList();
    }
}
