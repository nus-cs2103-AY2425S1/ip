package nuffle.command;

import nuffle.storage.Storage;
import nuffle.task.TaskList;
import nuffle.ui.Ui;

public class FindCommand extends Command {
    private final String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        assert userInput != null && !userInput.trim().isEmpty() : "Command should not be null or empty";
        assert tasks != null : "Task list should not be null";
        String desc = userInput.substring(4).trim();
        return ui.displayFoundTasks(tasks.findTasksByKeyword(desc));
    }

    public boolean isTerminateProgram() {
        return false;
    }
}
