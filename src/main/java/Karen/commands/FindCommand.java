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
        List<Task> foundTasks = taskList.searchTasks(this.searchWord);
        if (foundTasks.isEmpty()) {
            System.out.println("Couldnt find any matching tasks :(");
        } else {
            System.out.println("Found tasks!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
