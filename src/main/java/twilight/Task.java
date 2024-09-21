package twilight;

/**
 * Stores all the information pertaining to a task and allows modification to status.
 */
public class Task {
    protected String EMPTY_TAG = "NA";
    protected String description;
    protected boolean isDone;
    protected String tag;

    /**
     * Creates a Task with status as incomplete.
     *
     * @param description What the task is.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = EMPTY_TAG;
    }

    /**
     * Creates a task.
     *
     * @param description What the task is.
     * @param status Whether task is complete.
     */
    public Task(String description, boolean status, String tag) {
        this.description = description;
        this.isDone = status;
        this.tag = tag;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    /**
     * Returns a string format of the task when storing tasklist in a text file.
     * 1 and 0 indicate the completion of the task with 1 being complete.
     */
    public String toStorageString() {
        if (isDone) {
            return "1," + this.description;
        } else {
            return "0," + this.description;
        }
    }

    /**
     * Marks task as complete.
     *
     * @throws InvalidInputException when task is already complete.
     */
    public void setDone() throws InvalidInputException {
        if (!this.isDone) {
            this.isDone = true;
        } else {
            throw new InvalidInputException("Task is already marked complete.");
        }
    }

    /**
     * Marks task as incomplete.
     *
     * @throws InvalidInputException when task is already incomplete.
     */
    public void setUndone() throws InvalidInputException {
        if (!this.isDone) {
            throw new InvalidInputException("Task is already marked incomplete.");
        }
        this.isDone = false;
    }

    /**
     * Tags the task with the inputted tag.
     *
     * @param tag Description of tag to be added.
     */
    public void tag(String tag) {
        if (this.tag.equals(EMPTY_TAG)) {
            this.tag = tag;
        } else {
            this.tag += " " + tag;
        }
    }

    protected String getTagString() {
        if (tag.equals("NA")) {
            return "";
        }
        return " " + tag;
    }
}
