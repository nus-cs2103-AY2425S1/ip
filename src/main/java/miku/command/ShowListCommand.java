package miku.command;
import miku.utility.UI;
import miku.utility.TaskList;
import miku.utility.Storage;

public class ShowListCommand extends Command {

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.printList();
        ui.printSectionBreak();
    }

    @Override
    public void setData() {

    }
}
