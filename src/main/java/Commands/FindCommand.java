package Commands;

import java.util.List;

import Storage.Storage;
import Tasks.Task;
import TaskList.TaskList;
import UI.Ui;

public class FindCommand extends Command {
    public FindCommand(String s) {
        super(s);
    }

    /**
     * creates a list of tasks that contain the given keyword
     *
     * @param t  The task list to operate on.
     * @param s  The storage to use for saving/loading data.
     * @param ui The user interface to interact with the user.
     * @return the string representation of the tasks
     */
    public String execute(TaskList t, Storage s, Ui ui) {
        String str = "";
        List<Task> taskList = t.getTasks();
        TaskList res = new TaskList();
        String taskToFind = getInput().substring(5);
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(taskToFind)) {
                res.addTask(taskList.get(i));
            }
        }
        str += ui.findingTask();
        str += res.printTasks();
        return str;
    }
}
