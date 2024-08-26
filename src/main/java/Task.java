import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    protected final String task;
    protected boolean isDone;
    private static final String[] FORMAT_DATE_TIME = {
            "d-M-[uu][uuuu] HHmm",
            "d-M-[uu][uuuu] HH:mm",
            "d-M-[uu][uuuu] h.mma",
            "d-M-[uu][uuuu] hmma",
            "d-M-[uu][uuuu] ha",
            "d/M/[uu][uuuu] HHmm",
            "d/M/[uu][uuuu] HH:mm",
            "d/M/[uu][uuuu] h.mma",
            "d/M/[uu][uuuu] hmma",
            "d/M/[uu][uuuu] ha",
            "[uu][uuuu]-M-d HHmm",
            "[uu][uuuu]-M-d HH:mm",
            "[uu][uuuu]-M-d h.mma",
            "[uu][uuuu]-M-d hmma",
            "[uu][uuuu]-M-d ha",
            "[uu][uuuu]/M/d HHmm",
            "[uu][uuuu]/M/d HH:mm",
            "[uu][uuuu]/M/d h.mma",
            "[uu][uuuu]/M/d hmma",
            "[uu][uuuu]/M/d ha",
    };
    private static final String[] FORMAT_DATE = {
            "d-M-[uu][uuuu]",
            "d/M/[uu][uuuu]",
            "[uu][uuuu]/M/d",
            "[uu][uuuu]-M-d",
    };

    public Task() {
        task = "";
    }

    protected LocalDateTime parseDateTime(String str) {
        for (String s : FORMAT_DATE) {
            try {
                return LocalDate.parse(str, DateTimeFormatter.ofPattern(s))
                        .atStartOfDay();
            } catch (DateTimeParseException e) {
                //Fallthrough
            }
        }
        for (String s : FORMAT_DATE_TIME) {
            try {
                return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(s));
            } catch (DateTimeParseException e) {
                //Fallthrough
            }
        }
        throw new DateTimeParseException("", str, 0);
    }

    public void mark() {
        isDone = true;
        System.out.println("  " + this);
    }

    public void unmark() {
        isDone = false;
        System.out.println("  " + this);
    }

    @Override
    public String toString() {
        String s = isDone ? "X" : " ";
        return "[" + s + "] " + task;
    }
}
