package elara.command;

import elara.storage.Storage;
import elara.task.TaskList;
import elara.ui.Ui;

/**
 * Represents a command that lists out the tasks in task list that can be executed in the Elara chatbot.
 */
public class ListCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listTasks(taskList);
    }
}
