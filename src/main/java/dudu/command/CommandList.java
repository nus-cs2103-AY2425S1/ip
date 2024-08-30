package dudu.command;

import dudu.command.Command;
import dudu.utils.Storage;
import dudu.utils.TaskList;
import dudu.utils.UI;

public class CommandList extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage){
        ui.printTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !(o instanceof CommandList)) {
            return false;
        }
        return true;
    }
}
