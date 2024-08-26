public class Task {
    private String description;
    private boolean isDone = false;
    Task(String description) {
        this.description = description;
    }


    /**
     * Mark the task as done by setting the {@code isDone} flag to {@code true}.
     */

    void mark() {
        isDone = true;
    }

    /**
     * Unmark the task by setting the {@code isDone} flag to {@code false}.
     */

    void unMark() {
        isDone = false;
    }

    private String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return getStatus() + " " + this.description;
    }

}
