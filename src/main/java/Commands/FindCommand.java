package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Tasks.Task;
import UI.UI;

import java.util.ArrayList;
import java.util.List;
public class FindCommand extends Command {
    public FindCommand(String s) {
        super(s);
    }

    public void execute (TaskList t, Storage s, UI ui) {
        List<Task> taskList = t.getTasks();
        TaskList res = new TaskList();
        String taskToFind = input.substring(5);
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(taskToFind)) {
                res.addTask(taskList.get(i));
            }
        }
        ui.findingTask();
        res.printTasks();
    }
}
