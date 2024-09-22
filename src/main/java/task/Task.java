package task;

import java.time.format.DateTimeFormatter;

/**
 * Abstract class representing a task with a completion status and formatting options.
 */
public abstract class Task {
    protected static final DateTimeFormatter TO_SELF_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy HHmm");
    protected static final DateTimeFormatter TO_USER_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    private final String task;
    private TaskType type;
    private boolean isCompleted;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.task = description;
        this.isCompleted = false;
        TaskList.mainTaskList.addTask(this);
    }

    /**
     * Sets the task type.
     *
     * @param type The task type.
     */
    void setType(TaskType type) {
        this.type = type;
    }

    /**
     * Returns the type of the task.
     *
     * @return The TaskType of the task.
     */
    protected TaskType getType() {
        return this.type;
    }

    /**
     * Marks the task as completed.
     */
    protected void markAsDone() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as not completed.
     */
    protected void markAsUndone() {
        this.isCompleted = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is completed, otherwise false.
     */
    public boolean isMarkedAsCompleted() {
        return this.isCompleted;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.task;
    }

    /**
     * Returns the save file format for the task.
     *
     * @return A string representing the task in the save file format.
     */
    public abstract String saveFileFormat();

    @Override
    public String toString() {
        String status = this.isCompleted ? "[X]" : "[ ]";
        return getTypeAsString() + " " + status + " " + this.task;
    }

    private String getTypeAsString() {
        switch (this.type) {
        case TODO:
            return "[T]";
        case DEADLINE:
            return "[D]";
        case EVENT:
            return "[E]";
        default:
            throw new IllegalStateException("Unexpected value: " + this.type);
        }
    }

    /**
     * Checks if the task description contains a specified term.
     *
     * @param term The term to search for within the task description.
     * @return true if the term is found, otherwise false.
     */
    public boolean hasTerm(String term) {
        return this.task.contains(term);
    }

    protected enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    protected enum ReadBy {
        USER,
        BOB
    }
}

