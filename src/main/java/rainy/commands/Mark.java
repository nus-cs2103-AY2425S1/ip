package rainy.commands;

import rainy.database.Parser;
import rainy.database.UI;
import rainy.rainyexceptions.InvalidIndexException;
import rainy.rainyexceptions.InvalidMarkAndUnmarkException;
import rainy.tasks.TaskTracker;

public class Mark extends Command {
    private int validResponse;
    private TaskTracker taskTracker;

    public Mark(int validResponse, TaskTracker taskTracker) {
        this.validResponse = validResponse;
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() throws InvalidIndexException, InvalidMarkAndUnmarkException {
        if (validResponse != -1) {
            this.taskTracker.markDone(validResponse - 1);
        } else {
            this.ui.noCategoryDeclared();
        }
        return this.taskTracker;
    }
}
