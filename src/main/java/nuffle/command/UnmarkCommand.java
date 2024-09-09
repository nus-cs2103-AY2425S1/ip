package nuffle.command;

import nuffle.storage.Storage;
import nuffle.task.Task;
import nuffle.task.TaskList;
import nuffle.ui.Ui;

public class UnmarkCommand extends Command {
    private final String userInput;

    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        int index = Integer.parseInt(userInput.substring(7)) - 1;
        // check that index is always more than or equals to 0 and index must be within the inputList size
        if (index >= 0 && index < tasks.getSize()) {
            Task currTask = tasks.getTask(index);
            currTask.markNotDone();
            return ui.unmarkTaskMessage(currTask);
        } else {
            // print as error message
            return ui.unmarkTaskError();
        }
    }
}
