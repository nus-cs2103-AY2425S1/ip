package rainy.commands;

import rainy.rainyexceptions.InvalidIndexException;
import rainy.tasks.TaskTracker;

public class Delete extends Command {
    private int validResponse;
    private TaskTracker taskTracker;
    private static int INVALID_RESPONSE = -1;

    public Delete(int validResponse, TaskTracker taskTracker) {
        this.validResponse = validResponse;
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() throws InvalidIndexException {
        if (validResponse != INVALID_RESPONSE) {
            this.taskTracker.delete(validResponse - 1);
        } else {
            this.ui.noCategoryDeclared();
        }
        return this.taskTracker;
    }
}