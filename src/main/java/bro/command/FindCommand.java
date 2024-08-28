package bro.command;

import bro.task.Task;
import bro.task.TaskList;
import bro.ui.UI;

import java.util.ArrayList;

public class FindCommand extends ReadCommand{

    private final String content;

    public FindCommand(TaskList taskList, String content) {
        super(taskList);
        this.content = content;
    }

    @Override
    public void execute(UI ui) {
        ArrayList<Task> tasks = super.taskList.findTasks(this.content);
        ui.showTaskFind(tasks);
    }
}
