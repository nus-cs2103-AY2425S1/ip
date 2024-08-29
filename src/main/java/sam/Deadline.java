package sam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 * Inherits from the Item class.
 */
public class Deadline extends Item {

    private LocalDate by;

    /**
     * Constructs a Deadline object with the specified name and deadline.
     * 
     * @param newname The name of the deadline task.
     * @param by The deadline of the task in the format "dd-MM-yyyy".
     */
    public Deadline(String newname, String by) {
        super(newname);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.by = LocalDate.parse(by, formatter);
    }

    @Override
    public String toData() {
        String str = String.format("D | %s | %s\n", super.toData(), this.by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        return str;
    }

    @Override
    public String toString() {
        String formattedDate = this.by.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
        String str = String.format("[D] %s (by: %s)", super.toString(), formattedDate);
        return str;
    }
}