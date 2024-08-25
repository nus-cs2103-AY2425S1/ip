import java.util.Arrays;
import java.util.List;

public abstract class Task {
    private boolean isCompleted;
    private final String LINE_BREAK = "-------------------------------------";
    private String description;

    /**
     * Constructor for Task
     * @param description what the task is about
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Returns the Task as a fancier string with its status and description
     *
     * @return Fancier string of the Task
     */
    abstract public String toFancyString();

    /**
     * Returns the Task as a string with its status and description
     *
     * @return String of the Task
     */
    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    /**
     * Returns the status of the Task
     *
     * @return 1 if the task is marked as completed and 0 if the task is unmarked
     */
    public int getStatus() {
        return isCompleted ? 1 : 0;
    }

    /**
     * Returns the description of the task
     *
     * @return returns the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the isCompleted variable to true and prints out a display message
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Prints display message after marking a task
     */
    public void printMarkMessage() {
        System.out.println(LINE_BREAK + "\nNice! I've marked this task as done:\n" +
                this.toString() + "\n" + LINE_BREAK);
    }

    /**
     * Sets the isCompleted variable to false and prints out a display message
     */
    public void unmark() {
        this.isCompleted = false;
        System.out.println(LINE_BREAK + "\nOK, I've marked this task as not done yet:\n" +
                this.toString() + "\n" + LINE_BREAK);
    }
}
