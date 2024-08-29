package milutrock.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import milutrock.exceptions.InvalidTaskFormatException;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    public static Deadline getDeadlineFromInput(String input) throws InvalidTaskFormatException {
        if (input.length() < 9) {
            throw new InvalidTaskFormatException("Deadline");
        }
        
        String[] parts = input.substring(9).split(" /by ");
        if (parts.length != 2) {
            throw new InvalidTaskFormatException("Deadline");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime datetime = LocalDateTime.parse(parts[1], formatter);

        return new Deadline(parts[0], datetime);
    }

    @Override
    public String toString() { 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")"; 
    }
}
