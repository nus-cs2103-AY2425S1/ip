package alisa.task;

public abstract class Task {
    private String taskDescription;
    private boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsNotDone() {
        isDone = false;
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + taskDescription;
    }

    public String getFileStatus() { return (isDone ? "1" : "0"); }

    public String getTask() {
        return this.taskDescription;
    }

    public abstract String toFileString();

    /**
     * Indicates if the task description contains the keyword.
     *
     * @param keyword Keyword to search for in task description.
     * @return true if task description contains keyword, false otherwise.
     */
    public boolean containsWord(String keyword) {
        return taskDescription.contains(keyword);
    }
}
