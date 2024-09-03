package edith.task;

public abstract class Task {
    private String typeOfTask;
    private String task;
    private boolean isDone;

    public Task(String typeOfTask, String task) {
        this.typeOfTask = typeOfTask;
        this.task = task;
        this.isDone = false;
    }

    public String taskString() {
        return this.task;
    }

    public String savedTaskString() {
        return this.task;
    }

    public String typeOfTaskString() {
        return this.typeOfTask;
    }

    public String statusString() {
        if (this.isDone) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    public void markTaskDone() {
        this.isDone = true;
    }

    public void markTaskUndone() {
        this.isDone = false;
    }

    /**
     * Checks if the task's description contains the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return true if the task's description contains the keyword, false otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return taskString().toLowerCase().contains(keyword);
    }

    @Override
    public String toString() {
        return typeOfTaskString() + statusString() + taskString();
    }
}
