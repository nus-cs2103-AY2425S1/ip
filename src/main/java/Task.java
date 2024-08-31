public class Task {
    String desc;
    Boolean isMarked;

    public Task (String desc) {
        this.desc = desc;
        this.isMarked = false;
    }

    public Task markTask() {
        /** 
         * Marks the task if unmarked & returns task.
         */
        this.isMarked = true;
        return this;
    }

    public Task unmarkTask() {
        /** 
         * Unmarks the task if marked & returns task.
         */
        this.isMarked = false;
        return this;
    }

    public String toString() {
        /** 
         * Returns the String output
         */
        if (this.isMarked) {
            return "[X] " + this.desc;
        } else {
            return "[ ] " + this.desc;
        }
    }
}
