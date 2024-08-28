package bro.command;

import bro.task.TaskList;
import bro.ui.UI;

public class ReadCommand implements Command {

    private final TaskList taskList;

    public ReadCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    public void execute(UI ui) {
        ui.printTasks(this.taskList);
    }

    public boolean isExit() {
        return false;
    }
}
