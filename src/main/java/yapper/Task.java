package yapper;

/**
 * Represents a Task.
 */
public abstract class Task {
    public static final String DONE_SYMBOL = "[X]";
    public static final String UNDONE_SYMBOL = "[ ]";
    public static final String FILE_DONE_SYMBOL = "D";
    public static final String FILE_UNDONE_SYMBOL = "N";
    private final String name;
    private boolean isDone;
    private String taskTag;

    /**
     * Creates an instance of a Task.
     *
     * @param taskName Name of the task.
     */
    public Task(String taskName) {
        this.name = taskName;
    }

    public String getTaskTag() {
        return this.taskTag;
    }
    public void setTaskTag(String taskTag) {
        this.taskTag = taskTag;
    }
    public void setIsDone(boolean bool) {
        this.isDone = bool;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Returns the string representation of the Task object to be recorded into a file.
     * String to be decoded when loading history from the file.
     *
     * @return String representation of the Task object.
     */
    public abstract String toFile();

    public boolean contains(String word) {
        return this.name.contains(word);
    }
}
