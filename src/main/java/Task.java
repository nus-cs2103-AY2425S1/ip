public class Task {
    private boolean completed;
    private String title;

    /**
     * Constructs a new 'Task' object which specifies a task that can be added to Tayoo's list
     *
     * @param title the title of the task
     * @param completed a boolean value representing whether the task has been completed
     */
    public Task(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    /**
     * Constructs a new 'Task' object which specifies a task that can be added to Tayoo's list.
     * Assumes new task is not completed
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

    public boolean isCompleted() {
        return this.completed;
    }

    public String getTitle() {
        return this.title;
    }
    @Override
    public String toString() {
        if (completed) {
            return "[X] " + title;
        } else {
            return "[ ] " + title;
        }
    }

    /**
     * Returns the task in a form that can be stored as data in a .txt file
     * Tasks will be stored in the format "Task" | [true OR false] | [Task title]. The value true will be stored if the
     * task is completed, and false otherwise.
     *
     * @return string representation of task in command form
     */
    public String toTxt() {
        if (completed) {
            return "Task | true | " + this.title;
        } else {
            return "Task | false | " + this.title;
        }
    }

}
