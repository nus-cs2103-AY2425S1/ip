package duck.data.task;

/**
 * A stub class for Task.
 */
public class TaskStub extends Task {


    private String description = "taskStub";
    private boolean isDone = false;




    public TaskStub() {
        super("taskStub");
    }

    public TaskStub(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String getDescription() {
        return description;
    }


    /**
     * Returns a string representation of the stub task's completion status.
     *
     * @return The status icon of the task.
     */
    @Override
    public String toFileFormat() {
        return "T | 0 | taskStub";
    }

    @Override
    public void markAsDone() {
        // do nothing
    }

    @Override
    public void markAsIncomplete() {
        // do nothing
    }
    @Override
    public String toString() {
        return "[T]" + "[ ]" + " taskStub";
    }

}
