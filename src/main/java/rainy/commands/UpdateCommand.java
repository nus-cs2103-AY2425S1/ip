package rainy.commands;

import rainy.rainyexceptions.InvalidDeadlineParametersException;
import rainy.rainyexceptions.InvalidEventParametersException;
import rainy.rainyexceptions.InvalidIndexException;
import rainy.tasks.Deadline;
import rainy.tasks.Event;
import rainy.tasks.TaskTracker;

/**
 * Parent class of the separate Update classes for each type of class.
 */
public class UpdateCommand extends Command {
    protected int validResponse;
    protected TaskTracker taskTracker;
    protected String[] updateParameters;
    private static final int INVALID_RESPONSE = -1;
    private static final char ERROR_OUTPUT = '`';

    /**
     * Constructs a new <code>UpdateCommand</code>
     * @param validResponse     If validResponse is -1 then the input is invalid else validResponse
     *                          refers to the index number of the task list.
     * @param taskTracker       <code>TaskTracker</code> object to be updated.
     * @param updateParameters  List containing the set of update parameters.
     */
    public UpdateCommand(int validResponse, TaskTracker taskTracker, String[] updateParameters) {
        this.validResponse = validResponse;
        this.taskTracker = taskTracker;
        this.updateParameters = updateParameters;
    }

    public TaskTracker getResponse() throws InvalidDeadlineParametersException, InvalidEventParametersException,
            InvalidIndexException {
        if (validResponse == INVALID_RESPONSE || validResponse > taskTracker.getList().size()) {
            System.out.println(ui.invalidTask() + ERROR_OUTPUT);
            throw new InvalidIndexException(ui.invalidTask());
        }
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
