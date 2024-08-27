import java.time.LocalDate;
import java.time.Month;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = toLocalDate(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getLocalDate(by) + ")";
    }

    private LocalDate toLocalDate(String by) {
        //by is YYYY-MM-DD
        int year = Integer.parseInt(by.substring(0, 4));
        int month = Integer.parseInt(by.substring(5, 7));
        int day = Integer.parseInt(by.substring(8, 10));
        return LocalDate.of(year, month, day);
    }

    private String getLocalDate(LocalDate date) {
        Month month = date.getMonth();
        return Month.of(date.getMonthValue()).toString() + " " + date.getDayOfMonth() + " " + date.getYear();
    }
}
