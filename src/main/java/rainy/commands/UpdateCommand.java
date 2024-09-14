package rainy.commands;

import rainy.tasks.TaskTracker;

public abstract class UpdateCommand extends Command {
    protected int validResponse;
    protected TaskTracker taskTracker;

    public UpdateCommand(int validResponse, TaskTracker taskTracker) {
        this.validResponse = validResponse;
        this.taskTracker = taskTracker;
    }


}
