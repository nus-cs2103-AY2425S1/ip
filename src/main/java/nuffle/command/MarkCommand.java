package nuffle.command;

import nuffle.storage.Storage;
import nuffle.task.Task;
import nuffle.task.TaskList;
import nuffle.ui.Ui;

public class MarkCommand extends Command {
    private final String userInput;

    public MarkCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        int index = Integer.parseInt(userInput.substring(5)) - 1;
        // check that index is always more than or equals to 0 and index must be within the inputList size
        assert index >= 0 && index < tasks.getSize() : "Index should be within the range of the input list";
        String response;
        // check that index is always more than or equals to 0 and index must be within the inputList size
        if (index >= 0 && index < tasks.getSize()) {
            Task currTask = tasks.getTask(index);
            currTask.markAsDone();
            response = ui.markTaskMessage(currTask);
        } else {
            // print as error message
            response = ui.markTaskError();
        }
        return response;
    }
}
