public class Task {
    protected String description;
    protected Boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task markTask() {
        /** 
         * Marks the task if unmarked & returns task.
         */
        this.isDone = true;
        return this;
    }

    public Task unmarkTask() {
        /** 
         * Unmarks the task if marked & returns task.
         */
        this.isDone = false;
        return this;
    }

    public String toString() {
        /** 
         * Returns the String output
         */
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
