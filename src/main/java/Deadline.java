import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    private LocalDateTime byDate;

    /**
     * Checks the length of the value given whether it is within the specified range.
     * Also checks if the value is parsable to an integer.
     */
    private boolean dateCheck(String value, int min, int max, int minNo, int maxNo) {
        return value.length() >= min 
                && value.length() <= max 
                && value.chars().allMatch(Character::isDigit)
                && Integer.parseInt(value) >= minNo 
                && Integer.parseInt(value) <= maxNo;
    }

    private String prependZero(int value) {
        return value < 10 ? "0" + value : String.valueOf(value);
    }

    private String parseDateTimeString(LocalDateTime dateTime) {
        return prependZero(dateTime.getDayOfMonth())
                + "-" 
                + prependZero(dateTime.getMonthValue()) 
                + "-" + dateTime.getYear() 
                + " " + prependZero(dateTime.getHour()) 
                + prependZero(dateTime.getMinute());
    }

    private String parseDateTimeStringAlt(LocalDateTime dateTime) {
        return Month.of(dateTime.getMonthValue()).getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH)
                + " " 
                + prependZero(dateTime.getDayOfMonth()) 
                + ", " + dateTime.getYear() 
                + ", " + prependZero(dateTime.getHour()) 
                + prependZero(dateTime.getMinute());
    }

    private LocalDateTime parseDateTime(String dateTime) throws DateTimeException {
        // Accepted Format: dd-mm-yyyy hhmm or dd/mm/yyyy hhmm, hhmm in 24-hour format
        String[] dateTimeBrokenDown = dateTime.replace("/", "-")
                .replace(" ", "-").split("-");
        if (dateTimeBrokenDown.length != 4
                || !dateCheck(dateTimeBrokenDown[0], 1, 2, 1, 31)
                || !dateCheck(dateTimeBrokenDown[1], 1, 2, 1, 12)
                || !dateCheck(dateTimeBrokenDown[2], 4, 4, 2024, 9999)
                || !dateCheck(dateTimeBrokenDown[3], 4, 4, 0, 2359)
                || !dateCheck(dateTimeBrokenDown[3].substring(0, 2), 2, 2, 0, 23)
                || !dateCheck(dateTimeBrokenDown[3].substring(2), 2, 2, 0, 59)) {;
            throw new DateTimeException("Invalid date time format");
        };
        return LocalDateTime.of(
                Integer.parseInt(dateTimeBrokenDown[2]),
                Integer.parseInt(dateTimeBrokenDown[1]),
                Integer.parseInt(dateTimeBrokenDown[0]),
                Integer.parseInt(dateTimeBrokenDown[3].substring(0, 2)),
                Integer.parseInt(dateTimeBrokenDown[3].substring(2))
        );
    }

    public Deadline(String description) throws InfinityException {
        try {
            this.setDescription(description.split(" /by ")[0]);

            String by = description.split(" /by ")[1];
            byDate = parseDateTime(by);

            this.setTypeOfTask("D");
        } catch (DateTimeException e) {
            throw new InfinityException("""
                    Oops, I think your format is a little wrong. 
                    I only understand dates in the format of DD-MM-YYYY HHMM or DD/MM/YYYY HHMM""");
        }
    }

    public Deadline(boolean isDone, String description, String by) throws InfinityException {
        this.isDone = isDone;
        this.setDescription(description);
        this.byDate = parseDateTime(by);
        this.setTypeOfTask("D");
    }

    @Override
    public String saveFileFormat(String delimiter) {
        return String.format("%s%s%s%s%s%s%s", 
                this.typeOfTask, delimiter, 
                this.isDone ? "1" : "0", delimiter, 
                this.parseDateTimeString(byDate), delimiter, 
                this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", 
                this.typeOfTask, 
                this.isDone ? "X" : " ", 
                this.description, 
                this.parseDateTimeStringAlt(byDate));
    }
}