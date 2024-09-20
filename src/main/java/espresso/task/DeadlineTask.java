//Solution below inspired by https://github.com/nus-cs2103-AY2425S1/ip/pull/557 with permission
package espresso.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a task which has a deadline.
 * Extends the {@link Task} class to include a deadline date.
 */
public class DeadlineTask extends Task {
    private Date dl;
    private static final SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");

    public DeadlineTask(String description, String dl) throws ParseException {
        super(description);
        this.dl = inputFormat.parse(dl);
    }

    public Date getDeadline() {
        return this.dl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + outputFormat.format(dl) + ")";
    }

    @Override
    public String toText() {
        return "D | " + super.toText() + " | " + inputFormat.format(dl);
    }
}