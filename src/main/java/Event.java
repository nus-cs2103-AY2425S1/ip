import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    LocalDate from;
    LocalDate to;
    static DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy");

    Event(String task, String from, String to) {
        super(task);
        this.from = super.stringToDate(from);
        this.to = super.stringToDate(from);
    }

    Event(int status, String task, String from, String to) {
        super(status, task);
        this.from = super.stringToDate(from);
        this.to = super.stringToDate(from);
    }

    public String toString() {
        return "[E]" + super.toString() + 
            " (from: " + this.from.format(DATEFORMAT) + " to: " + this.to.format(DATEFORMAT) + ")";
    }

    String toSaveAsString() {
        return "E" + super.toSaveAsString() + "/" + this.from.toString() + "/" + this.to.toString();
    }
}