package Commands;

import java.util.List;

import Storage.Storage;
import TaskList.TaskList;
import Tasks.Task;
import UI.Ui;

public class FindCommand extends Command {
    public FindCommand(String s) {
        super(s);
    }

    public void execute(TaskList t, Storage s, Ui ui) {
        List<Task> taskList = t.getTasks();
        TaskList res = new TaskList();
        String taskToFind = getInput().substring(5);
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(taskToFind)) {
                res.addTask(taskList.get(i));
            }
        }
        ui.findingTask();
        res.printTasks();
    }
}
