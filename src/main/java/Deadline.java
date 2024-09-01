import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String byString;
    private LocalDate byDate;
    private boolean isDate;
    private static final String[] supportedDates = {
            "uuuu-MM-dd", "uu-MM-dd",
            "dd MMM, uuuu", "MMM dd, uuuu", "dd MMM uuuu", "MMM dd uuuu", "uuuu MMM dd", "uuuu dd MMM",
            "dd MMMM, uuuu", "MMMM dd, uuuu", "dd MMMM uuuu", "MMMM dd uuuu", "uuuu MMMM dd", "uuuu dd MMMM",
            "MM/dd/uuuu", "MM/dd/uu", "MM/dd",
            "MMM dd", "dd MMM",
            "MMMM dd", "dd MMMM"
    }; //TODO shorten this using []
    private static final String[] supportedTimes = {
            "HHmm", "HH:mm", "HH:mm:ss", "HH.mm", "HH.mm.ss",
            "hh:mm a", "hh:mm:ss a", "hh.mm a", "hh.mm.ss a"
    };

    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
        isDate = false;
        for (int i = 0; i < supportedDates.length; ++i) {
            try {
                byDate = LocalDate.parse(by,
                        DateTimeFormatter.ofPattern(supportedDates[i]));
                isDate = true;
                return;
            } catch (DateTimeParseException ignored) {
            }
            for (int j = 0; j < supportedTimes.length; ++j) {
                try {
                    byDate = LocalDate.parse(by,
                            DateTimeFormatter.ofPattern(supportedDates[i] + " " + supportedTimes[j]));
                    isDate = true;
                    return;
                } catch (DateTimeParseException ignored) {
                }
                if (j != 0) {
                    try {
                        byDate = LocalDate.parse(by,
                                DateTimeFormatter.ofPattern(supportedTimes[j] + " " + supportedDates[i]));
                        isDate = true;
                        return;
                    } catch (DateTimeParseException ignored) {
                    }
                }
            }
        }
    }

    /**
     * Returns the due date/time/etc of the deadline.
     * @return The due of the deadline
     */
    public String getBy() {
        if (isDate) {
            return byDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy[ HH:mm[:ss]]"));
        } else {
            return byString;
        }
    }

    @Override
    public String toString() {
        String prev = super.toString();
        return "[D" + prev.substring(2) + " (by: " + getBy() + ")";
    }
}
