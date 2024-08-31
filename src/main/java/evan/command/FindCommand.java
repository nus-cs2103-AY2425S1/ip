package evan.command;

import evan.main.Storage;
import evan.main.TaskList;
import evan.main.Ui;
import evan.task.Task;
import evan.task.Todo;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Get Tasks with matching descriptions
        TaskList matchingTasks = taskList.getTasksWithMatchingDescription(description);
        
        ui.showSuccess("Here are the matching tasks in your list:\n" + matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
