package barney.data.task;

import java.time.LocalDate;
import java.util.ArrayList;

import barney.data.datetime.BarneyDateTime;

/**
 * Represents an event task.
 */
public class EventTask extends Task {
    private final String atString;
    private final LocalDate atDate;
    private final String toString;
    private final LocalDate toDate;

    /**
     * Represents an event task that has a specific start and end date/time.
     * Inherits from the Task class.
     *
     * @param description a description of the event task
     * @param atString    a string representation of the start date/time of the
     *                    event task
     * @param toString    a string representation of the end date/time of the event
     *                    task
     */
    public EventTask(String description, String atString, String toString) {
        super(description);
        assert atString != null;
        assert !atString.isEmpty();
        assert toString != null;
        assert !toString.isEmpty();
        this.atString = atString;
        this.atDate = BarneyDateTime.parseDate(atString);
        this.toString = toString;
        this.toDate = BarneyDateTime.parseDate(toString);

    }

    /**
     * Converts the EventTask object into an ArrayList of strings for saving
     * purposes.
     *
     * @return The ArrayList of strings representing the EventTask object.
     */
    @Override
    public ArrayList<String> toSaveArray() {
        ArrayList<String> rtr = super.toSaveArray();
        rtr.add("E");
        rtr.add(this.atString);
        rtr.add(this.toString);
        return rtr;
    }

    /**
     * Returns a string representation of the EventTask object.
     *
     * @return A string representation of the EventTask object.
     */
    @Override
    public String toString() {
        String rtrAtString = (this.atDate != null) ? BarneyDateTime.formatDate(this.atDate) : this.atString;

        String rtrToString = (this.toDate != null) ? BarneyDateTime.formatDate(this.toDate) : this.toString;

        return "[E]" + super.toString() + " (at: " + rtrAtString + " to: " + rtrToString + ")";
    }
}
