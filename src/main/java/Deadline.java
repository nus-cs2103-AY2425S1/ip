import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;
public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by);
    }

    public LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            //converts the String into a LocalDateTime object
            return LocalDateTime.parse(dateTimeString,formatter);
        } catch(DateTimeException e) {
            System.out.println(" The date/time format is incorrect. Use d/M/yyyy HHmm format.");
            return null;
        }
    }

    @Override
    public String toString() {
        //define the formatter which will be passed to the .format function
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        //converts the LocalDateTime Object into a string
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by.format(formatter);
    }
    public static Deadline fromFileFormat(String fileFormat) {
        String[] parts = fileFormat.split(" \\| ");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid deadline format.");
        }
        String description = parts[2];
        boolean isDone = parts[1].equals("1");
        LocalDateTime by = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        Deadline deadline = new Deadline(description, by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
        if (isDone) {
            deadline.setDone();
        }
        return deadline;
    }
}

