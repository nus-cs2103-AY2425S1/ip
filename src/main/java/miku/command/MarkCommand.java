package miku.command;

import miku.utility.UI;
import miku.utility.TaskList;
import miku.utility.Storage;

public class MarkCommand extends Command {
    int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.mark(index);
        storage.saveFromTaskList(taskList);
        ui.printSectionBreak();
    }

    @Override
    public void setData() {

    }
}
