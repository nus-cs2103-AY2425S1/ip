package FRIDAY;

import java.time.LocalDate;

/**
 * Represents a task with a deadline, description and a completion status.
 * <p>
 * This class extends the Task class and adds functionality to manage deadlines
 * it allows for the storage and display of a deadline date
 * </p>
 */
public class Deadline extends Task{
    private LocalDate taskDeadline;

    /**
     * Constructor for task of type deadline
     * @param description task description
     * @param deadline task deadline, in format YYYY-MM-DD format
     * @param type specifies task completion status
     */
    public Deadline(String description, String deadline, int type) {
        super(description, type);
        //accept deadline in YYYY-MM-DD format
        this.taskDeadline = LocalDate.parse(deadline.trim());
    }

    /**
     * outputs all the information pertaining the task in a specified format for storage purposes
     * @return a string containing all important details of the task
     */
    //store deadline in YYYY-MM-DD format
    public String storageDisplay() {
        return "D" + super.storageDisplay() + " | " + taskDeadline;
    }

    @Override
    public String toString() {
        //print it out in MM-DD-YYYY format
        return "[D]" + super.toString() + "(" + taskDeadline.getMonth() + " " + taskDeadline.getDayOfMonth() + " " + taskDeadline.getYear() + ")";
    }

    public LocalDate getTaskDeadline() {
        return this.taskDeadline;
    }
}
