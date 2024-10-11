package arona.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for the deadline class which encapsulates a task with a set deadline but irrelevant/any start date
     * @param  description  the name of the task
     * @param  by  the deadline given in LocalDate readable format, time not included
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String getCategory() {
        return "[D]";
    }

    /**
     * A variant of toString that returns a nicer human friendly date format
     * @return String that looks like: [X][D] Deadline by 9 Aug 2024
     */
    public String toFriendlyString() {
        return getStatusIcon() + getCategory() + " " + getDescription()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }

    @Override
    public String toString() {
        return getStatusIcon() + getCategory() + " " + getDescription() + " (by: " + by + ")";
    }
}
