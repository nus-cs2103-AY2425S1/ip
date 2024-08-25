public class Task {
    private boolean completed;
    private String title;

    /**
     * Constructs a new 'Task' object which specifies a task that can be added to Tayoo's list
     *
     * @param title the title of the task
     */
    public Task(String title) {
        this.title = title;
        this.completed = false;
    }

    public boolean markAsDone() {
        if (this.completed) {
            return false;
        } else {
            this.completed = true;
            return true;
        }
    }

    public boolean unmark() {
        if (!this.completed) {
            return false;
        } else {
            this.completed = false;
            return true;
        }
    }
    @Override
    public String toString() {
        if (completed) {
            return "[X] " + title;
        } else {
            return "[ ] " + title;
        }
    }


}
