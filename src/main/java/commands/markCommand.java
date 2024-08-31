package commands;
import storage.Storage;
import storage.TaskList;
import ui.UI;

public class markCommand implements Command {
    private final String action;
    private final String desc;

    public markCommand(String action, String desc) {
        this.action = action;
        this.desc = desc;
    }

    @Override
    public boolean execute(Storage storage, TaskList master, UI ui) {
        try {
            int index = Integer.parseInt(this.desc);

            master.doneTask(this.action, index-1);
            storage.saveList(master.getParent());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Friday > Input the task number (1 - " + master.getSize() + ") to mark/unmark the task");
        }
        UI.printLine();
        return false;
    }
}
