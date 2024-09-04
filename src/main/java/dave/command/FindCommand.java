package dave.command;

import dave.storage.Storage;
import dave.task.Task;
import dave.task.TaskList;
import dave.ui.Ui;

public class FindCommand extends Command{

    public String keyword;

    public FindCommand(String description) {
        this.keyword = description;
    }

    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            for (Task task : tasks.findTasks(keyword)) {
                System.out.println(task);
            }
        } catch (Exception e) {
            System.out.println("Unexpected error occurred : " + e.getMessage());
        }
    }
}
