package arsenbot.command;

import arsenbot.storage.Storage;
import arsenbot.task.Task;
import arsenbot.task.TaskList;
import arsenbot.task.TaskManagerException;
import arsenbot.ui.Ui;

import java.io.IOException;
import java.util.List;

public class FindCommand extends Command{
    private final String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, TaskManagerException {
        String keyword;
        if (input.length() == 4) {
            throw new TaskManagerException("Error: The description of a 'todo' cannot be empty.");
        }
        keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new TaskManagerException("Error: The description of a 'todo' cannot be empty.");

        }

        List<Task> foundTasks = tasks.findTaskByKeyword(keyword);
        ui.showFoundTasks(foundTasks);
    }
}
