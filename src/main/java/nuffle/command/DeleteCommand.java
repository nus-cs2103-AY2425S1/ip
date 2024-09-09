package nuffle.command;

import nuffle.storage.Storage;
import nuffle.task.Task;
import nuffle.task.TaskList;
import nuffle.ui.Ui;

public class DeleteCommand extends Command {
    private final String userInput;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        int index = Integer.parseInt(userInput.substring(7)) - 1;
        if (index >= 0 && index < tasks.getSize()) {
            Task remove = tasks.deleteTask(index);
            return ui.deleteTaskMessage(remove, tasks.getSize());
        } else {
            // if the index is not in range, then print an error message
            return ui.deleteTaskError();
        }
    }

    public boolean isTerminateProgram() {
        return false;
    }

}
