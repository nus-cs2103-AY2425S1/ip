import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate byTime;

    public Deadline(String name, String byTime) {
        super(name);
        this.byTime = DateFormatters.getDateFromStr(byTime);
    }

    public LocalDate getByTime() {
        return this.byTime;
    }

    @Override
    public String type() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateFormatters.getStrFromDate(this.byTime) + ")";
    }
}
