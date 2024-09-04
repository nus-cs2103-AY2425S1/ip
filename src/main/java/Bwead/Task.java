package Bwead;

/**
 * Represents a task that has a string method and can be marked to done.
 */
public class Task {

    private String text;
    private boolean isDone;

    /**
     * Constructs a Task instance using a string name.
     *
     * @param text name of the task.
     */
    public Task(String text) {
        this.isDone = false;
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return this.text;
    }

    /**
     * Returns the string representation of a task.
     *
     * @return String representation of a task.
     */
    public String toString() {
        String str = "";
        if (isDone) {
            str = "X";
        } else {
            str = " ";
        }
        return "[" + str + "] " + text;
    }
}
