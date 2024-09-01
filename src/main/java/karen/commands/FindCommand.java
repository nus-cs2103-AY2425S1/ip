package Karen.commands;

import Karen.tasks.Task;
import Karen.tasks.TaskList;
import Karen.util.Ui;

import java.util.List;

public class FindCommand extends Command{
    private String searchWord;

    public FindCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        List<Task> foundList = taskList.searchTasks(this.searchWord);
        TaskList foundTasks = TaskList.fromList(foundList);
        if (foundTasks.getSize() == 0) {
            ui.showFoundNothingMessage();
        } else {
            ui.showFindOutput(foundTasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
