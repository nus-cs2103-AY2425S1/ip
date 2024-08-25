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
     * Returns the Task as a string with its status and description
     *
     * @return a String of the Task
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
     * Sets the isCompleted variable to true and prints out a display message
     */
    public void mark() {
        this.isCompleted = true;
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
