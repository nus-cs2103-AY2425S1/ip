package Command;

import Storage.Storage;
import TaskList.TaskList;
import UI.UI;

public class DueOnCommand extends Command{
    private String time;
    public DueOnCommand(String time) {
        this.time = time;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.show(tasks.dueOn(this.time));
    }
}
