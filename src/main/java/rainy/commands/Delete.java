package rainy.commands;

import rainy.rainyexceptions.InvalidIndexException;
import rainy.tasks.TaskTracker;

public class Delete extends Command {
    private int validResponse;
    private TaskTracker taskTracker;

    public Delete(int validResponse, TaskTracker taskTracker) {
        this.validResponse = validResponse;
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() throws InvalidIndexException {
        if (validResponse != -1) {
            this.taskTracker.delete(validResponse - 1);
        } else {
            this.ui.noCategoryDeclared();
        }
        return this.taskTracker;
    }
}