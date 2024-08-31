package miku.command;
import miku.utility.UI;
import miku.utility.TaskList;
import miku.utility.Storage;

public class UnmarkCommand extends Command {
    int index;

    public UnmarkCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.unmark(index);
        ui.printSectionBreak();
        storage.saveFromTaskList(taskList);
    }

    @Override
    public void setData() {

    }
}
