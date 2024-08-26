
package simon;

import java.util.ArrayList;
import java.util.List;

public class FindCommand implements Command{
    String name;

    public FindCommand(String name) {
        this.name = name;

    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.toArr();
        ArrayList<Task> results = new ArrayList<>();
        for (Task task:tasks) {
            if (task.getName().toLowerCase().contains(this.name.toLowerCase())) {
                results.add(task);
            }
        }
        ui.showSearchTask(results);


    }
}