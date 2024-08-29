package monique.command;

import monique.storage.Storage;
import monique.task.Task;
import monique.tasklist.TaskList;
import monique.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    private final String searchKey;

    public FindCommand(String searchKey) {
        super();
        this.searchKey = searchKey;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //create a new arrayList<Task> for UI to print through
        ArrayList<Task> resultList = new ArrayList<>();
        for (Task task: tasks.getTaskList()) {
            if (task.getDescription().contains(this.searchKey)) {
                resultList.add(task);
            }
        }
        ui.showFindResults(resultList);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
