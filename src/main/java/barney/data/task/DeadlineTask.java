package barney.data.task;

import java.time.LocalDate;
import java.util.ArrayList;

import barney.data.datetime.BarneyDateTime;

/**
 * Represents a task with a deadline.
 * <p>
 * This class extends the Task class and adds functionality to handle tasks with
 * deadlines.
 */
public class DeadlineTask extends Task {
    private final String byString;
    private final LocalDate byDate;

    /**
     * Represents a task with a deadline.
     * <p>
     * This class extends the Task class and adds functionality to handle tasks with
     * deadlines.
     *
     * @param description The description of the task.
     * @param byString    The deadline of the task in string format.
     */
    public DeadlineTask(String description, String byString) {
        super(description);
        assert byString != null;
        this.byString = byString;
        this.byDate = BarneyDateTime.parseDate(byString);
    }

    /**
     * Converts the DeadlineTask object into an ArrayList of strings for saving
     * purposes.
     *
     * @return The ArrayList of strings representing the DeadlineTask object.
     */
    @Override
    public ArrayList<String> toSaveArray() {
        ArrayList<String> rtr = super.toSaveArray();
        rtr.add("D");
        rtr.add(this.byString);
        return rtr;
    }

    /**
     * Returns a string representation of the DeadlineTask object.
     *
     * @return A string representation of the DeadlineTask object.
     */
    @Override
    public String toString() {
        String rtrByString = (this.byDate != null) ? BarneyDateTime.formatDate(this.byDate) : this.byString;
        return "[D]" + super.toString() + " (by: " + rtrByString + ")";
    }
}
