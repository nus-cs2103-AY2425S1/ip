package yapmeister.commands;

import yapmeister.Storage;
import yapmeister.UI;
import yapmeister.task.Task;
import yapmeister.task.TaskList;

import java.util.ArrayList;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) {
        int i = 0;
        ArrayList<Task> taskArrayList = tasks.getTaskArrayList();
        for (Task task : taskArrayList) {
            ui.displayString(String.format("%d. %s", i + 1, task.toString()));
            i++;
        }
        if (i == 0) {
            ui.displayString("No tasks to show");
        }
    }

    @Override
    public Command parse(String input) throws Exception {
        return this;
    }
}
