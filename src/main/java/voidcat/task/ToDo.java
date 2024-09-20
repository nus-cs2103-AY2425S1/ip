package voidcat.task;

/**
 * Represents a ToDo task, which is a simple task with no specific deadline or event time.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new ToDo task with the specified description
     * and completion status to save to file.
     *
     * @param description The description of the ToDo task.
     * @param done 1 if the task is done, 0 if not done.
     */
    public ToDo(String description, int done) {
        super(description);
        if (done == 1) {
            this.isDone = true;
        }
    }
    @Override
    public String getTaskType() {
        return "T";
    }

}
