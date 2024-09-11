package task;

import ui.BotException;

import java.time.format.DateTimeFormatter;

/**
 * Abstract class representing a task with a completion status and formatting options.
 * Hello!
 */
public abstract class Task {
    protected static final DateTimeFormatter toSelfFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HHmm");
    protected static final DateTimeFormatter toUserFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    protected TaskType getType() {
        return this.type;
    };

    protected enum ReadBy {
        USER,
        BOB
    }

    private final String task;
    protected enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    private TaskType type;
    private boolean completed;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.task = description;
        this.completed = false;
        TaskList.mainTaskList.addTask(this);
    }

    void setType(TaskType type) {
        this.type = type;
    }

    /**
     * Marks the task as completed.
     */
    protected void markAsDone() {
        this.completed = true;
    }

    /**
     * Marks the task as not completed.
     */
    protected void markAsUndone() {
        this.completed = false;
    }

    /**
     * Returns the save file format for the task.
     *
     * @return A string representing the task in the save file format.
     */
    public abstract String saveFileFormat();

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is completed, otherwise false.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.task;
    }

    @Override
    public String toString() {
        String status = this.completed ? "[X]" : "[ ]";
        return this.getTypeAsString() + " " + status + " " + this.task;
    }

    private String getTypeAsString() {
        if (this.type == TaskType.TODO) {
            return "[T]";
        } else if (this.type == TaskType.DEADLINE) {
            return "[D]";
        } else {
            return "[E]";
        }
    }

    /**
     * Checks if the task description contains a specified term.
     *
     * @param term The term to search for within the task description.
     * @return true if the term is found, otherwise false.
     */
    public boolean containsTerm(String term) {
        return this.task.contains(term);
    }
}
