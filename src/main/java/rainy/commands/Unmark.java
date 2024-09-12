package rainy.commands;

import rainy.rainyexceptions.InvalidIndexException;
import rainy.rainyexceptions.InvalidMarkAndUnmarkException;
import rainy.tasks.TaskTracker;

public class Unmark extends Command {
    private int validResponse;
    private TaskTracker taskTracker;

    public Unmark(int validResponse, TaskTracker taskTracker) {
        this.validResponse = validResponse;
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() throws InvalidIndexException, InvalidMarkAndUnmarkException {
        if (validResponse != -1) {
            this.taskTracker.unmarkDone(validResponse - 1);
        } else {
            this.ui.noCategoryDeclared();
        }
        return this.taskTracker;
    }
}