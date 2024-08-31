package commands;
import storage.Storage;
import storage.TaskList;
import ui.UI;

public class exitCommand implements Command {

    @Override
    public boolean execute(Storage storage, TaskList master, UI ui) {
        System.out.println("Friday > Type \"bye\" or \"Bye\" to exit");
        UI.printLine();
        return false;
    }
}
