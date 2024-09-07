package karen.commands;

import java.util.List;

import karen.tasks.Task;
import karen.tasks.TaskList;
import karen.util.Ui;

/**
 * Handles searching a <code>TaskList</code> by name and prints the appropriate message
 */
public class FindCommand extends Command {
    private String searchWord;

    public FindCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        List<Task> foundList = taskList.searchTasks(this.searchWord);
        TaskList foundTasks = TaskList.fromList(foundList);
        if (foundTasks.getSize() == 0) {
            return ui.showFoundNothingMessage();
        } else {
            return ui.showFindOutput(foundTasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
