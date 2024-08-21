/**
* This is an abstract class to define a task object.
*/
public abstract class Task {
    
    /** Description of the task */
    protected String taskName;
    /** Determine if the task is marked or not */
    protected boolean isChecked;

    /**
     * Constructor to create a Task object.
     */
    Task(String taskName) {
        this.taskName = taskName;
        isChecked = false;
    }

    /**
     * This function updates the task to be marked.
     * It checks if the task has been marked already.
     */
    public void mark() {

        // Check if the task is already marked or not
        if (!isChecked) {
            this.isChecked = true;
            System.out.println("Nice! I've marked this task as done:");
            
        } else {
            System.out.println("This task is already marked as done!");
        }

        // Print the task
        System.out.println(this.toString());
    }

    /**
     * This function updates the task to be unmarked.
     * It checks if the task has been unmarked already.
     */
    public void unMark() {

        // Check if the task is already unmarked
        if (isChecked) {
            this.isChecked = false;
            System.out.println("OK, I've marked this task as not done yet:");
            
        } else {
            System.out.println("This task is already unmarked!");
        }

        // Print the task
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        if (isChecked) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
