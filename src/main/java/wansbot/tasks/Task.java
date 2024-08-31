package wansbot.tasks;

/**
 * Class which is inherited from all other types of tasks e.g. Todos, Deadlined, Event. Includes basic common
 * functionality of a task.
 */
public class Task {
    private String name;
    private boolean finished;

    /**
     * Assigns a name to the task.
     */
    public Task(String name) {
        this.name = name;
        this.finished = false;
    }

    /**
     * Indicates if task is complete or not
     *
     * @return Boolean which is true if task is complete. False otherwise.
     */
    public boolean isDone() {
        return this.finished;
    }

    /**
     * Marks the task as complete.
     */
    public void finish() {
        this.finished = true;
    }

    /**
     * Marks the class as incomplete.
     */
    public void unfinish() {
        this.finished = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof Task) {
            Task t = (Task) obj;
            if (this.name.equals(t.name)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
