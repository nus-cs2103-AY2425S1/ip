import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends TaskWithDate {
    protected LocalDate localDate;

    public Deadline(String name, LocalDate localDate) {
        super(name, "D");
        this.localDate = localDate;
    }

    @Override
    public boolean overlapsWith(LocalDate localDate) {
        return localDate.equals(this.localDate);
    }

    @Override
    public String formatDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[" + this.symbol + "]"
                + super.toString()
                + " (by: "
                + formatDate(this.localDate) + ")";
    }
}
