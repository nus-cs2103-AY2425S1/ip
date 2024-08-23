/**
 * The DeadlineTask class represents a task with a deadline. It extends the Task class
 * and includes a deadline date associated with the task.
 */
public class DeadlineTask extends Task {
    private String deadline;

    /**
     * Constructs a DeadlineTask object with the specified name and deadline.
     * @param name the name or description of the task
     * @param deadline the deadline date for the task
     */
    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the deadline task, including the task name
     * and deadline date formatted as "(by: date)".
     * @return a formatted string representation of the deadline task
     */
    @Override
    public String toString() {
        int by = deadline.indexOf(" ");
        String date = String.format("by: " + deadline.substring(by + 1));
        return String.format("[D]" + super.toString() + " (" + date + ")");
    }

}
