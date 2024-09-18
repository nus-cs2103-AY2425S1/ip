package sam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 * Inherits from the Item class.
 */
public class Deadline extends Item {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private LocalDate by;

    /**
     * Constructs a Deadline object with the specified name and deadline.
     *
     * @param name The name of the deadline task.
     * @param by The deadline of the task in the format "dd-MM-yyyy".
     */
    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDate.parse(by, DATE_FORMAT);
    }

    @Override
    public String toData() {
        String str = String.format(
            "D | %s | %s\n", super.toData(), this.by.format(DATE_FORMAT));
        return str;
    }

    @Override
    public String toString() {
        String formattedDate = this.by.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
        String str = String.format("[D] %s (by: %s)", super.toString(), formattedDate);
        return str;
    }
}
