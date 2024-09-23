package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task implements Comparable<Deadline> {

    private LocalDate by;

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        try {
            LocalDate d = LocalDate.parse(by);
            this.by = d;
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String toString() {
        return String.format("[Deadline] " + super.toString() + " By: [%s]", this.by.toString());
    }

    public String toStringFormatted() {
        return String.format("[Deadline] " + super.toString() + " By: [%s]", this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Compares this Deadline object with the specified Deadline object for order.
     *
     * @param d the Deadline object to be compared
     * @return a negative integer, zero, or a positive integer as this Deadline object has a earlier, equal or further deadline than the specified Deadline object.
     */
    @Override
    public int compareTo(Deadline d) {
        if (this.by.isBefore(d.by)) {
            return -1;
        } else if (this.by.isAfter(d.by)) {
            return 1;
        } else {
            return 0;
        }
    }

    public LocalDate getDeadline() {
        return this.by;
    }
}
