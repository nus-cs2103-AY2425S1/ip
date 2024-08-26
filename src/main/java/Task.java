public class Task {
    private String description;
    private boolean isDone = false;
    Task(String description) {
        this.description = description;
    }

<<<<<<< HEAD
    /**
     * Mark the task as done by setting the {@code isDone} flag to {@code true}.
     */
=======
>>>>>>> origin/master
    void mark() {
        isDone = true;
    }

<<<<<<< HEAD
    /**
     * Unmark the task by setting the {@code isDone} flag to {@code false}.
     */
=======
>>>>>>> origin/master
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
