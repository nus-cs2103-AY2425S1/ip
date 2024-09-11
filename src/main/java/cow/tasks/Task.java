package cow.tasks;

import java.time.LocalDate;

/** Task class to create a task. **/
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new tasks object.
     *
     * @param description The description of the tasks Object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Alternate constructor for loading isDone directly
     *
     * @param isDone 1 or 0 indicating if task is done
     * @param description The Description of the Tasks.Task
     */
    public Task(String isDone, String description) {
        this.description = description;
        this.isDone = isDone.equals("1");
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the todo object as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark the todo object as completed
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * A default date used for filtering out Tasks with deadlines.
     *
     * @return a LocalDate.
     */
    public LocalDate getDate() {
        return LocalDate.of(0, 1, 1);
    }

    /**
     * Check if description contains substring.
     *
     * @return boolean.
     */
    public boolean getContainsSubString(String subString) {
        return this.description.contains(subString);
    }

    /**
     * Creates the string to save the data in a .txt file;
     *
     * @return a string to be written to a file;
     */
    public String getSaveData() {
        String result = "";
        if (this.isDone) {
            result += "1";
        } else {
            result += "0";
        }
        return result + " | " + this.description;
    }
}
