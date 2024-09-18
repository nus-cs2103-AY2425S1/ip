package bro.command;

import bro.task.TaskList;
import bro.ui.UI;

public class ReadCommand implements Command {

    final TaskList taskList;

    public ReadCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    public String execute(UI ui) {
        return ui.printTasks(this.taskList);
    }

    public boolean isExit() {
        return false;
    }
}
