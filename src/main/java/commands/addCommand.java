package commands;
import storage.Storage;
import storage.TaskList;
import ui.UI;

public class addCommand implements Command {

    private final String desc;

    public addCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean execute(Storage storage, TaskList master, UI ui) {
        master.addTask(this.desc);
        storage.saveList(master.getParent());
        UI.printLine();
        return false;
    }
}
