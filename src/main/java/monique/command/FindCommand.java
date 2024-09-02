package monique.command;

import java.util.ArrayList;

import monique.storage.Storage;
import monique.task.Task;
import monique.tasklist.TaskList;
import monique.ui.Ui;


public class FindCommand extends Command {

    private final String searchKey;
    private String findResults = "";

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
        this.findResults = ui.showFindResults(resultList);
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public String getResponse(Ui ui) {
        return this.findResults;
    }
}
