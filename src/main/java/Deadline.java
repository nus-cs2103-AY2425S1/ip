import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String byString;
    private LocalDate byDate;

    public Deadline(String description, String byString) {
        super(description);
        this.byString = byString;
        this.byDate = DateTimeParser.parseDate(byString);
    }

    public String toSaveString() {
        return "D" + Constants.SAVE_FILE_DELIMITER + super.toSaveString() + Constants.SAVE_FILE_DELIMITER
                + byString;
    }

    @Override
    public String toString() {
        String rtrByString = (this.byDate != null)
                ? this.byDate.format(DateTimeFormatter.ofPattern(Constants.DATE_TIME_RETURN_FORMAT))
                : this.byString;
        return "[D]" + super.toString() + " (by: " + rtrByString + ")";
    }
}
