//Solution below inspired by https://github.com/nus-cs2103-AY2425S1/ip/pull/557 with permission
package espresso.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import espresso.command.InvalidCommandException;

/**
 * Represents a task which is an event.
 * Extends the {@link Task} class to include an event date.
 */
public class EventTask extends Task {
    private Date starts;
    private Date ends;
    private static final SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

    public EventTask(String description, String starts, String ends) throws ParseException, InvalidCommandException {
        super(description);
        this.starts = inputFormat.parse(starts);
        this.ends = inputFormat.parse(ends);

        if (this.starts.after(this.ends)) {
            throw new InvalidCommandException("Invalid event time.");
        }
    }
    public Date getStarts() {
        return this.starts;
    }
    public Date getEnds() {
        return this.ends;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + outputFormat.format(starts) + " to: " + outputFormat.format(ends) + ")";
    }

    @Override
    public String toText() {
        return "E | " + super.toText() + " | " + inputFormat.format(starts) + " | " + inputFormat.format(ends);
    }
}