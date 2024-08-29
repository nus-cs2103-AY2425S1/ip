package monique.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import monique.exception.IllegalDateFormatException;

public class Deadline extends Task {

    private static final String formatString = "[D][%s] %s (by: %s)";
    private final LocalDate by;

    //overloaded constructor to accept Date
    public Deadline(String description, boolean isComplete, LocalDate by) {
        super(description, isComplete);
        this.by = by;
    }
    public Deadline(String description, boolean isComplete, String by) throws IllegalDateFormatException {
        super(description, isComplete);

        LocalDate parsedDate = null;

        //accept the following date formats
        List<DateTimeFormatter> formatters = List.of(
                DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),
                DateTimeFormatter.ofPattern("M/d/yyyy"),
                DateTimeFormatter.ofPattern("M-d-yyyy HHmm"),
                DateTimeFormatter.ofPattern("M-d-yyyy")
        );
        for (DateTimeFormatter formatter: formatters) {
            try {
                parsedDate = LocalDate.parse(by, formatter);
                break;
            } catch (DateTimeParseException e) {
                //continue to next formatter
            }
        }

        if (parsedDate == null) {
            throw new IllegalDateFormatException();
        }
        this.by = parsedDate;
    }

    public Deadline() throws IllegalDateFormatException {
        this("", true, "");
    }

    @Override
    public String toString() {
        return String.format(formatString, this.isComplete ? "X" : " ",
                    this.description, this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public Deadline mark() {
        return new Deadline(this.description, true, this.by);
    }

    @Override
    public Deadline unmark() {
        return new Deadline(this.description, false, this.by);
    }


    public LocalDate getBy() {
        return this.by;
    }


}
