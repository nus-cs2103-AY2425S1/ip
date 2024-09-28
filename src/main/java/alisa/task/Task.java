package alisa.task;

import java.time.LocalDateTime;

public abstract class Task {
    private String taskDescription;
    private boolean isDone;

    /**
     * Constructs an instance of Task.
     *
     * @param taskDescription Description of the task.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Marks the task as complete.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the status of the task.
     *
     * @return "X" if task is completed, " " otherwise
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as not complete.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the details of the task.
     *
     * @return String of status and description of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + taskDescription;
    }

    /**
     * Returns task status in the format of the txt file.
     *
     * @return "1" if task is completed, "0" otherwise.
     */
    public String getFileStatus() { return (isDone ? "1" : "0"); }

    /**
     * Returns description of the task.
     */
    public String getTask() {
        return this.taskDescription;
    }

    /**
     * Returns details of the task in format for txt file.
     *
     * @return String of task details.
     */
    public abstract String convertToFileString();

    /**
     * Indicates if the task description contains the keyword.
     *
     * @param keyword Keyword to search for in task description.
     * @return true if task description contains keyword, false otherwise.
     */
    public boolean containsWord(String keyword) {
        return taskDescription.contains(keyword);
    }

    /**
     * Changes the task description.
     *
     * @param description The new description to change to.
     */
    public void changeDescription(String description) {
        taskDescription = description;
    }
}
