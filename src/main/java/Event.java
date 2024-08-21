import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String atString;
    private LocalDate atDate;
    private String toString;
    private LocalDate toDate;

    public Event(String description, String atString, String toString) {
        super(description);
        this.atString = atString;
        this.atDate = DateTimeParser.parseDate(atString);
        this.toString = toString;
        this.toDate = DateTimeParser.parseDate(toString);

    }

    public String toSaveString() {

        return "E" + Constants.SAVE_FILE_DELIMITER + super.toSaveString() + Constants.SAVE_FILE_DELIMITER
                + this.atString
                + Constants.SAVE_FILE_DELIMITER + this.toString;
    }

    @Override
    public String toString() {
        String rtrAtString = (this.atDate != null)
                ? this.atDate.format(DateTimeFormatter.ofPattern(Constants.DATE_TIME_RETURN_FORMAT))
                : this.atString;

        String rtrToString = (this.toDate != null)
                ? this.toDate.format(DateTimeFormatter.ofPattern(Constants.DATE_TIME_RETURN_FORMAT))
                : this.toString;

        return "[E]" + super.toString() + " (at: " + rtrAtString + " to: " + rtrToString + ")";
    }
}
