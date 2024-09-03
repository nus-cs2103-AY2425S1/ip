package edith.task;

public abstract class Task {
    private String taskName;
    private boolean status; // false for undone, true for done

    public Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }

    public void check() { // mark task as completed
        this.status = true;
    }

    public void uncheck() { // unmark task as completed
        this.status = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getStatus() {
        return this.status;
    }

    /**
     * Returns a boolean for if the task name contains keyword provided in user input.
     * @param keyword User input to be checked against task name.
     * @return True if task name contains keyword, false otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return taskName.contains(keyword);
    }

    @Override
    public String toString() {
        if (this.status) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }

    public abstract String toFileFormat();
}
