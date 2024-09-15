package rainy.commands;

import rainy.rainyexceptions.InvalidDeadlineParametersException;
import rainy.rainyexceptions.InvalidEventParametersException;
import rainy.tasks.Deadline;
import rainy.tasks.Event;
import rainy.tasks.TaskTracker;

public class UpdateCommand extends Command {
    protected int validResponse;
    protected TaskTracker taskTracker;
    protected String[] updateParameters;

    public UpdateCommand(int validResponse, TaskTracker taskTracker, String[] updateParameters) {
        this.validResponse = validResponse;
        this.taskTracker = taskTracker;
        this.updateParameters = updateParameters;
    }

    public TaskTracker getResponse() throws InvalidDeadlineParametersException, InvalidEventParametersException {
        if (taskTracker.getList().get(validResponse - 1) instanceof Deadline) {
            UpdateDeadline updateDeadline = new UpdateDeadline(validResponse, this.taskTracker, updateParameters);
            this.taskTracker = updateDeadline.getResponse();
        } else if (taskTracker.getList().get(validResponse - 1) instanceof Event) {
            UpdateEvent updateEvent = new UpdateEvent(validResponse, taskTracker, updateParameters);
            this.taskTracker = updateEvent.getResponse();
        } else {
            UpdateToDo updateToDo = new UpdateToDo(validResponse, taskTracker, updateParameters);
            this.taskTracker = updateToDo.getResponse();
        }
        return this.taskTracker;
    }


}
