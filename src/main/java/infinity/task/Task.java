package infinity.task;

import infinity.storage.Storage;

/**
 * This abstract class are the tasks that the bot will recognise and manage.
 */
public abstract class Task {
    /** Marker for reader if marked */
    public static final String MARKED_MARKER = "X";
    /** Marker for reader if not done */
    public static final String UNMARKED_MARKER = " ";
    /** Type of Tasks available as enum */
    public enum TaskTypes {
        D,
        E,
        T
    }
    /** Description of the Task */
    protected String description;
    /** Status of the Task, whether it is marked */
    protected boolean isDone = false;
    /** Type of Task */
    protected Task.TaskTypes typeOfTask;

    /**
     * Sets the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Sets the type of task
     *
     * @param typeOfTask The type of task, it will set the first letter to the type. Can be String or char.
     */
    protected void setTypeOfTask(Task.TaskTypes typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The description of the task.
     */
    protected void setDescription(String description) {
        this.description = description;
    }

    /**
     * Finds whether a given keyword is in the description of the Task.
     *
     * @param keyword keyword to check
     * @return true if it contains, false otherwise
     */
    public boolean findTask(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Parses the save-file format of the task.
     *
     * @param delimiter The delimiter to separate the fields.
     * @return The save-file format of the task.
     */
    public String saveFileFormat(String delimiter) {
        return String.format("%s%s%s%s%s",
                typeOfTask.toString(),
                delimiter,
                isDone ? Storage.DONE_MARKER : Storage.UNDONE_MARKER,
                delimiter,
                description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                typeOfTask.toString(),
                this.isDone ? MARKED_MARKER : UNMARKED_MARKER,
                this.description);
    }
}
