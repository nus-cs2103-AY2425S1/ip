package monique.task;

import monique.exception.IllegalDateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;
    private static final String formatString = "[E][%s] %s (from:%s to: %s)";

    public Event(String description, boolean isComplete, String from, String to) throws IllegalDateFormatException {
        super(description,isComplete);

        LocalDate parsedFromDate = null;
        LocalDate parsedToDate = null;
        List<DateTimeFormatter> formatters = List.of(
                DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),
                DateTimeFormatter.ofPattern("M/d/yyyy"),
                DateTimeFormatter.ofPattern("M-d-yyyy HHmm"),
                DateTimeFormatter.ofPattern("M-d-yyyy")
        );
        for (DateTimeFormatter formatter: formatters) {
            try {
                parsedFromDate = LocalDate.parse(from, formatter);
                break;
            } catch (DateTimeParseException e) {
                //continue to next formatter
            }
        }
        for (DateTimeFormatter formatter: formatters) {
            try {
                parsedToDate = LocalDate.parse(to, formatter);
                break;
            } catch (DateTimeParseException e) {
                //continue to next formatter
            }
        }

        if (parsedFromDate == null || parsedToDate == null) {
            throw new IllegalDateFormatException();
        }

        this.from = parsedFromDate;
        this.to = parsedToDate;

    }

    public Event(String description, boolean isComplete, LocalDate from, LocalDate to){
        super(description,isComplete);
        this.from = from;
        this.to = to;
    }

    public Event() throws IllegalDateFormatException {
        this("",true,"","");
    }

    @Override
    public String toString() {
        return String.format(formatString,this.isComplete?"X":" ",this.description,
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public Event mark() {
        return new Event(this.description, true,this.from, this.to);
    }

    @Override
    public Event unmark() {
        return new Event(this.description, false, this.from, this.to);
    }


}
