package kietwoforone.tasks;

/**
 * Represents a task with no deadline.
 */
public class Todo extends Task {

    private Task task;

    /**
     * Constructor for new Todo object.
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
        this.task = new Task(this.description);
    }

    /**
     * Returns a Todo string that is marked.
     *
     * @return Todo string.
     */
    @Override
    public String markTask() {
        return String.format("[T] %s", this.task.markTask());
    }

    /**
     * Returns a Todo string that is unmarked.
     *
     * @return Todo string.
     */
    @Override
    public String unmarkTask() {
        return String.format("[T] %s", this.task.unmarkTask());
    }

    /**
     * Returns a boolean.
     *
     * @param date
     * @return False.
     */
    @Override
    public boolean compareDate(String date) {
        return false;
    }

    /**
     * Compares the details of the Todo with the specified string input.
     * Returns true if the task contains the keyword, false otherwise.
     *
     * @param keyword
     * @return Boolean.
     */
    @Override
    public boolean compareString(String keyword) {
        return this.task.compareString(keyword);
    }

    /**
     * Returns the string representation of Todo object.
     *
     * @return String representation of the Todo object.
     */
    @Override
    public String toString() {
        return String.format("[T] %s", this.task.toString());
    }

}
